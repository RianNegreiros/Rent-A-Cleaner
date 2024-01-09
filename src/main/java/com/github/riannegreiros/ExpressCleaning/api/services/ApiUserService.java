package com.github.riannegreiros.ExpressCleaning.api.services;

import com.github.riannegreiros.ExpressCleaning.api.dtos.requests.UserRequest;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.MessageResponse;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.TokenResponse;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.UserRegisterResponse;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.UserResponse;
import com.github.riannegreiros.ExpressCleaning.api.mappers.ApiUserMapper;
import com.github.riannegreiros.ExpressCleaning.core.utils.SecurityUtils;
import com.github.riannegreiros.ExpressCleaning.core.exceptions.PasswordsDoNotMatchException;
import com.github.riannegreiros.ExpressCleaning.core.repositories.UserRepository;
import com.github.riannegreiros.ExpressCleaning.core.services.storage.adapters.StorageService;
import com.github.riannegreiros.ExpressCleaning.core.services.token.adapters.TokenService;
import com.github.riannegreiros.ExpressCleaning.core.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ApiUserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ApiUserMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserValidator validator;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private SecurityUtils securityUtils;

    public UserResponse register(UserRequest request) {
        validatePasswordConfirmation(request);

        var userToRegister = mapper.toModel(request);

        validator.validate(userToRegister);

        var hashPassword = passwordEncoder.encode(userToRegister.getPassword());
        userToRegister.setPassword(hashPassword);

        var documentPhoto = storageService.save(request.getDocumentPhoto());
        userToRegister.setDocumentPhoto(documentPhoto);

        if (userToRegister.isHousekeeper()) {
            var averageReputation = calculateAverageReputationHousekeepers();
            userToRegister.setReputation(averageReputation);
        }

        var registeredUser = repository.save(userToRegister);

        var response = mapper.toRegisterResponse(registeredUser);
        var tokenResponse = gerarTokenResponse(response);
        response.setToken(tokenResponse);
        return response;
    }

    public MessageResponse updateUserPhoto(MultipartFile userPhoto) {
        var loggedUser = securityUtils.getLoggedUser();
        var photo = storageService.save(userPhoto);
        loggedUser.setUserPhoto(photo);
        repository.save(loggedUser);
        return new MessageResponse("Photo saved successfully!");
    }

    private TokenResponse gerarTokenResponse(UserRegisterResponse response) {
        var token = tokenService.generateAccessToken(response.getEmail());
        var refresh = tokenService.generateRefreshToken(response.getEmail());
        return new TokenResponse(token, refresh);
    }

    private Double calculateAverageReputationHousekeepers() {
        var averageReputation = repository.getAverageReputationHousekeeper();
        if (averageReputation == null || averageReputation == 0.0) {
            averageReputation = 5.0;
        }
        return averageReputation;
    }

    private void validatePasswordConfirmation(UserRequest request) {
        var password = request.getPassword();
        var passwordConfirmation = request.getPasswordConfirmation();

        if (!password.equals(passwordConfirmation)) {
            var message = "the two password fields don't match";
            var fieldError = new FieldError(request.getClass().getName(), "passwordConfirmation", request.getPasswordConfirmation(), false, null, null, message);

            throw new PasswordsDoNotMatchException(message, fieldError);
        }
    }
}