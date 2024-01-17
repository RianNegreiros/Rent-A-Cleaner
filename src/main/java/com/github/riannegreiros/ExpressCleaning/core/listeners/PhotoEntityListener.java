package com.github.riannegreiros.ExpressCleaning.core.listeners;

import com.github.riannegreiros.ExpressCleaning.core.models.Photo;
import com.github.riannegreiros.ExpressCleaning.core.services.storage.adapters.StorageService;
import jakarta.persistence.PreRemove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PhotoEntityListener {

    @Autowired
    private static StorageService storageService;

    @Autowired
    public void setStorageService(StorageService storageService) {
        PhotoEntityListener.storageService = storageService;
    }

    @PreRemove
    private void preRemove(Photo photo) {
        storageService.delete(photo.getFilename());
    }
}
