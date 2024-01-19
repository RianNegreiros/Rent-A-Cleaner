package com.github.riannegreiros.ExpressCleaning.api.services;

import com.github.riannegreiros.ExpressCleaning.api.dtos.requests.UpdateUserRequest;
import com.github.riannegreiros.ExpressCleaning.api.dtos.requests.UserRequest;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.MessageResponse;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.TokenResponse;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.UserRegisterResponse;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.UserResponse;
import com.github.riannegreiros.ExpressCleaning.api.mappers.UserApiMapper;
import com.github.riannegreiros.ExpressCleaning.core.exceptions.WrongPasswordException;
import com.github.riannegreiros.ExpressCleaning.core.models.User;
import com.github.riannegreiros.ExpressCleaning.core.publishers.NewUserPublisher;
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

import static org.apache.commons.lang3.ObjectUtils.firstNonNull;

@Service
public class UserApiService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserApiMapper mapper;

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

    @Autowired
    private NewUserPublisher newUserPublisher;

    public UserResponse register(UserRequest request) {
        validatePasswordConfirmation(request);

        var userToRegister = mapper.toModel(request);

        validator.validate(userToRegister);

        var hashPassword = passwordEncoder.encode(userToRegister.getPassword());
        userToRegister.setPassword(hashPassword);

        var documentPhoto = storageService.save(request.getDocumentPhoto());
        userToRegister.setDocumentPhoto(documentPhoto);

        if (userToRegister.isCleaner()) {
            var averageReputation = calculateCleanersAverageReputation();
            userToRegister.setReputation(averageReputation);
        }

        var registeredUser = repository.save(userToRegister);
        newUserPublisher.publish(registeredUser);

        var response = mapper.toRegisterResponse(registeredUser);
        var tokenResponse = generateTokenResponse(response);
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

    public MessageResponse update(UpdateUserRequest request) {
        var userLogged = securityUtils.getLoggedUser();

        updateLoggedUserInformation(request, userLogged);

        validator.validate(userLogged);

        changePassword(request, userLogged);

        repository.save(userLogged);

        return new MessageResponse("User successfully updated");
    }

    private void changePassword(UpdateUserRequest request, User userLogged) {
        var hasPasswords = request.getPassword() != null
                && request.getNewPassword() != null
                && request.getPasswordConfirmation() != null;

        if (hasPasswords) {
            verifyPassword(request, userLogged);
            validatePasswordConfirmation(request);
            var newPassword = request.getNewPassword();
            var newPasswordHash = passwordEncoder.encode(newPassword);
            userLogged.setPassword(newPasswordHash);
        }
    }

    private void verifyPassword(UpdateUserRequest request, User userLogged) {
        var passwordRequest = request.getPassword();
        var passwordDB = userLogged.getPassword();

        if (!passwordEncoder.matches(passwordRequest, passwordDB)) {
            var message = "The password entered is incorrect";
            var fieldError = new FieldError(request.getClass().getName(), "password", passwordRequest, false, null, null, message);
            throw new WrongPasswordException(message, fieldError);
        }
    }

    private void updateLoggedUserInformation(UpdateUserRequest request, User userLogged) {
        userLogged.setFullName(
                firstNonNull(request.getFullName(), userLogged.getFullName())
        );
        userLogged.setEmail(
                firstNonNull(request.getEmail(), userLogged.getEmail())
        );
        userLogged.setCpf(
                firstNonNull(request.getCpf(), userLogged.getCpf())
        );
        userLogged.setBirth(
                firstNonNull(request.getBirth(), userLogged.getBirth())
        );
        userLogged.setTelephone(
                firstNonNull(request.getTelephone(), userLogged.getTelephone())
        );
        userLogged.setPixKey(
                firstNonNull(request.getPixKey(), userLogged.getPixKey())
        );
    }

    private TokenResponse generateTokenResponse(UserRegisterResponse response) {
        var token = tokenService.generateAccessToken(response.getEmail());
        var refresh = tokenService.generateRefreshToken(response.getEmail());
        return new TokenResponse(token, refresh);
    }

    private Double calculateCleanersAverageReputation() {
        var averageReputation = repository.getAverageReputationCleaner();
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

    private void validatePasswordConfirmation(UpdateUserRequest request) {
        var password = request.getNewPassword();
        var passwordConfirmation = request.getPasswordConfirmation();

        if (!password.equals(passwordConfirmation)) {
            var message = "the two password fields don't match";
            var fieldError = new FieldError(request.getClass().getName(), "passwordConfirmation", request.getPasswordConfirmation(), false, null, null, message);

            throw new PasswordsDoNotMatchException(message, fieldError);
        }
    }
}