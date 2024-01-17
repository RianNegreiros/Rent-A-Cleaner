package com.github.riannegreiros.ExpressCleaning.api.dtos.requests;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.riannegreiros.ExpressCleaning.core.validators.Age;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@JsonNaming(SnakeCaseStrategy.class)
public class UpdateUserRequest {
    @Size(min = 3, max = 255)
    private String fullName;

    @Email
    @Size(max = 255)
    private String email;

    @CPF
    @Size(min = 11, max = 11)
    private String cpf;

    @Past
    @Age(min = 18, max = 100)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birth;

    @Size(min = 11, max = 11)
    private String telephone;

    @Size(min = 11, max = 255)
    private String pixKey;

    @Size(max = 255)
    private String password;

    @Size(max = 255)
    private String newPassword;

    @Size(max = 255)
    private String passwordConfirmation;

    public UpdateUserRequest() {
    }

    public UpdateUserRequest(String fullName, String email, String cpf, LocalDate birth, String telephone, String pixKey, String password, String newPassword, String passwordConfirmation) {
        this.fullName = fullName;
        this.email = email;
        this.cpf = cpf;
        this.birth = birth;
        this.telephone = telephone;
        this.pixKey = pixKey;
        this.password = password;
        this.newPassword = newPassword;
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPixKey() {
        return pixKey;
    }

    public void setPixKey(String pixKey) {
        this.pixKey = pixKey;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }
}
