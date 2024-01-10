package com.github.riannegreiros.ExpressCleaning.core.services.storage.adapters;

import com.github.riannegreiros.ExpressCleaning.core.models.Photo;
import com.github.riannegreiros.ExpressCleaning.core.services.storage.exceptions.StorageServiceException;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    Photo save(MultipartFile file) throws StorageServiceException;

    void delete(String filename) throws StorageServiceException;
}