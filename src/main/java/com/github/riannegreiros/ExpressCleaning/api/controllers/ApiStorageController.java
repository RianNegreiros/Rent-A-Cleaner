package com.github.riannegreiros.ExpressCleaning.api.controllers;

import com.github.riannegreiros.ExpressCleaning.core.repositories.PhotoRepository;
import com.github.riannegreiros.ExpressCleaning.core.services.storage.LocalStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/uploads")
@Profile({"dev", "docker"})
public class ApiStorageController {

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private LocalStorageService localStorageService;

    @GetMapping
    public ResponseEntity<Object> getPhoto(@RequestParam String filename) throws IOException {
        var photo = photoRepository.findByFilename(filename).get();
        var file = localStorageService.getPhoto(filename);

        return ResponseEntity.ok()
                .header("Content-Type", photo.getContentType())
                .header("Content-Length", photo.getContentLength().toString())
                .body(file.getInputStream().readAllBytes());
    }

}