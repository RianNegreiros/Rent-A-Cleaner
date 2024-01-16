package com.github.riannegreiros.ExpressCleaning.api.controllers;

import com.github.riannegreiros.ExpressCleaning.api.assemblers.UserAssembler;
import com.github.riannegreiros.ExpressCleaning.api.dtos.requests.UserRequest;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.MessageResponse;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.UserResponse;
import com.github.riannegreiros.ExpressCleaning.api.services.ApiUserService;
import com.github.riannegreiros.ExpressCleaning.core.permissions.ExpressCleaningPermissions;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {

    @Autowired
    private ApiUserService service;

    @Autowired
    private UserAssembler assembler;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserResponse register(@ModelAttribute @Valid UserRequest request) {
        var response =  service.register(request);

        assembler.addLinks(response);

        return response;
    }

    @PostMapping("/photo")
    @ExpressCleaningPermissions.isHousekeeperOrClient
    public MessageResponse updateUserPhoto(
            @RequestPart("user_photo") MultipartFile userPhoto
    ) {
        return service.updateUserPhoto(userPhoto);
    }
}
