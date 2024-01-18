package com.github.riannegreiros.ExpressCleaning.core.services.storage.providers;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.github.riannegreiros.ExpressCleaning.core.models.Photo;
import com.github.riannegreiros.ExpressCleaning.core.repositories.PhotoRepository;
import com.github.riannegreiros.ExpressCleaning.core.services.storage.adapters.StorageService;
import com.github.riannegreiros.ExpressCleaning.core.services.storage.exceptions.StorageServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
@Profile("prod")
public class S3StorageService implements StorageService {

    @Value("${s3.accessKey}")
    private String accessKey;

    @Value("${s3.secretKey}")
    private String secretKey;

    @Value("${s3.bucket}")
    private String bucket;

    @Value("${s3.region}")
    private Regions region;

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public Photo save(MultipartFile file) throws StorageServiceException {
        try {
            return trySave(file);
        } catch (IOException e) {
            throw new StorageServiceException(e.getLocalizedMessage());
        }
    }

    @Override
    public void delete(String filename) throws StorageServiceException {
        var s3client = getS3Client();
        s3client.deleteObject(bucket, filename);
    }

    private Photo trySave(MultipartFile file) throws IOException {
        var s3client = getS3Client();
        createBucketIfDoNotExist(s3client);
        var putObjectRequest = getPutObjectRequest(file);
        s3client.putObject(putObjectRequest);
        var photo = createPhoto(file, s3client, putObjectRequest);
        return photoRepository.save(photo);
    }

    private Photo createPhoto(MultipartFile file, AmazonS3 s3client, PutObjectRequest putObjectRequest) {
        return new Photo.Builder()
                .filename(putObjectRequest.getKey())
                .contentLength(file.getSize())
                .contentType(file.getContentType())
                .url(s3client.getUrl(bucket, putObjectRequest.getKey()).toString())
                .build();
    }

    private PutObjectRequest getPutObjectRequest(MultipartFile file) throws IOException {
        return new PutObjectRequest(
                bucket,
                generateFilename(file),
                file.getInputStream(),
                getObjectMetadata(file)
        ).withCannedAcl(CannedAccessControlList.PublicRead);
    }

    private ObjectMetadata getObjectMetadata(MultipartFile file) {
        var objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());
        return objectMetadata;
    }

    private String generateFilename(MultipartFile file) {
        var originalFilename = file.getOriginalFilename();
        var ext = Objects.requireNonNull(originalFilename).split("\\.")[1];
        return UUID.randomUUID() + "." + ext;
    }

    private void createBucketIfDoNotExist(AmazonS3 s3client) {
        if (!s3client.doesBucketExistV2(bucket)) {
            s3client.createBucket(bucket);
        }
    }

    private AmazonS3 getS3Client() {
        return AmazonS3ClientBuilder.standard()
                .withCredentials(getS3CredentialsProvider())
                .withRegion(region)
                .build();
    }

    private AWSCredentialsProvider getS3CredentialsProvider() {
        var s3Credentials = new BasicAWSCredentials(accessKey, secretKey);
        return new AWSStaticCredentialsProvider(s3Credentials);
    }
}
