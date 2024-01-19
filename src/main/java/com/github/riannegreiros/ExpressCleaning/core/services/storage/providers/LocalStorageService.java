package com.github.riannegreiros.ExpressCleaning.core.services.storage.providers;

import com.github.riannegreiros.ExpressCleaning.api.controllers.StorageApiController;
import com.github.riannegreiros.ExpressCleaning.core.models.Photo;
import com.github.riannegreiros.ExpressCleaning.core.repositories.PhotoRepository;
import com.github.riannegreiros.ExpressCleaning.core.services.storage.adapters.StorageService;
import com.github.riannegreiros.ExpressCleaning.core.services.storage.exceptions.StorageServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@Profile({"dev", "docker"})
public class LocalStorageService implements StorageService {

    private final Path pastaUpload = Paths.get("uploads");

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public Photo save(MultipartFile file) throws StorageServiceException {
        try {
            return trySave(file);
        } catch (IOException exception) {
            throw new StorageServiceException(exception.getLocalizedMessage());
        }
    }

    @Override
    public void delete(String filename) throws StorageServiceException {
        var archive = pastaUpload.resolve(filename);

        try {
            Files.deleteIfExists(archive);
        } catch (IOException exception) {
            throw new StorageServiceException(exception.getLocalizedMessage());
        }
    }

    public Resource getPhoto(String filename) {
        var archive = pastaUpload.resolve(filename);

        try {
            return new UrlResource(archive.toUri());
        } catch (MalformedURLException e) {
            throw new StorageServiceException(e.getLocalizedMessage());
        }
    }

    private Photo trySave(MultipartFile file) throws IOException {
        if (!Files.exists(pastaUpload)) {
            Files.createDirectories(pastaUpload);
        }

        var photo = genarateModelPhoto(file);
        Files.copy(file.getInputStream(), pastaUpload.resolve(photo.getFilename()));
        return photoRepository.save(photo);
    }

    private Photo genarateModelPhoto(MultipartFile file) throws IOException {
        var photo = new Photo();
        var filename = generateNewFilename(Objects.requireNonNull(file.getOriginalFilename()));
        var url = linkTo(methodOn(StorageApiController.class).getPhoto(filename)).toString();
        photo.setFilename(filename);
        photo.setContentLength(file.getSize());
        photo.setContentType(file.getContentType());
        photo.setUrl(url);
        return photo;
    }

    private String generateNewFilename(String filenameOriginal) {
        var ext = filenameOriginal.split("\\.")[1];
        return UUID.randomUUID().toString() + "." + ext;
    }
}