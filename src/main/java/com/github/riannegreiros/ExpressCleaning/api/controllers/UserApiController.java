package com.github.riannegreiros.ExpressCleaning.api.controllers;

import com.github.riannegreiros.ExpressCleaning.api.assemblers.UserAssembler;
import com.github.riannegreiros.ExpressCleaning.api.dtos.requests.UpdateUserRequest;
import com.github.riannegreiros.ExpressCleaning.api.dtos.requests.UserRequest;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.MessageResponse;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.UserResponse;
import com.github.riannegreiros.ExpressCleaning.api.services.UserApiService;
import com.github.riannegreiros.ExpressCleaning.core.permissions.RentACleanerPermissions;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/users")
public class UserApiController {

    @Autowired
    private UserApiService service;

    @Autowired
    private UserAssembler assembler;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserResponse register(@ModelAttribute @Valid UserRequest request) {
        var response =  service.register(request);

        assembler.addLinks(response);

        return response;
    }

    @PutMapping
    @RentACleanerPermissions.isCleanerOrClient
    public MessageResponse update(@RequestBody @Valid UpdateUserRequest request) {
        return service.update(request);
    }

    @PostMapping("/photo")
    @RentACleanerPermissions.isCleanerOrClient
    public MessageResponse updateUserPhoto(
            @RequestPart("user_photo") MultipartFile userPhoto
    ) {
        return service.updateUserPhoto(userPhoto);
    }
}
