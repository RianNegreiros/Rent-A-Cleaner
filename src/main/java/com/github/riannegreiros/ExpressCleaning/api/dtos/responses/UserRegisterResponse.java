package com.github.riannegreiros.ExpressCleaning.api.dtos.responses;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDate;
import java.util.List;

@JsonNaming(SnakeCaseStrategy.class)
public class UserRegisterResponse extends UserResponse {

    private TokenResponse token;

    public UserRegisterResponse(List<LinkResponse> links) {
        super(links);
    }

    public UserRegisterResponse() {
    }

    public UserRegisterResponse(List<LinkResponse> links, Long id, String fullName, String email, Integer userType, String cpf, LocalDate birth, String telephone, String pixKey, String userPhoto) {
        super(links, id, fullName, email, userType, cpf, birth, telephone, pixKey, userPhoto);
    }

    public UserRegisterResponse(Long id, String fullName, String email, Integer userType, String cpf, LocalDate birth, String telephone, String pixKey, String userPhoto) {
        super(id, fullName, email, userType, cpf, birth, telephone, pixKey, userPhoto);
    }

    public UserRegisterResponse(List<LinkResponse> links, TokenResponse token) {
        super(links);
        this.token = token;
    }

    public UserRegisterResponse(TokenResponse token) {
        this.token = token;
    }

    public UserRegisterResponse(List<LinkResponse> links, Long id, String fullName, String email, Integer userType, String cpf, LocalDate birth, String telephone, String pixKey, String userPhoto, TokenResponse token) {
        super(links, id, fullName, email, userType, cpf, birth, telephone, pixKey, userPhoto);
        this.token = token;
    }

    public UserRegisterResponse(Long id, String fullName, String email, Integer userType, String cpf, LocalDate birth, String telephone, String pixKey, String userPhoto, TokenResponse token) {
        super(id, fullName, email, userType, cpf, birth, telephone, pixKey, userPhoto);
        this.token = token;
    }

    public TokenResponse getToken() {
        return token;
    }

    public void setToken(TokenResponse token) {
        this.token = token;
    }
}
