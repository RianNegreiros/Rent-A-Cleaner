package com.github.riannegreiros.ExpressCleaning.api.dtos.requests;

import com.github.riannegreiros.ExpressCleaning.core.validators.Age;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class UserRequest {

    @NotNull
    @Size(min = 3, max = 255)
    private String fullName;

    @NotNull
    @Size(max = 255)
    @Email
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String passwordConfirmation;

    @NotNull
    private Integer userType;

    @NotNull
    @Size(min = 11, max = 11)
    @CPF
    private String cpf;

    @NotNull
    @Past
    @Age(min = 18, max = 100)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birth;

    @NotNull
    @Size(min = 11, max = 11)
    private String telephone;

    @Size(min = 11, max = 255)
    private String pixKey;

    @NotNull
    private MultipartFile DocumentPhoto;

    public UserRequest() {
    }

    public UserRequest(String fullName, String email, String password, String passwordConfirmation, Integer userType, String cpf, LocalDate birth, String telephone, String pixKey, MultipartFile documentPhoto) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
        this.userType = userType;
        this.cpf = cpf;
        this.birth = birth;
        this.telephone = telephone;
        this.pixKey = pixKey;
        DocumentPhoto = documentPhoto;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getbirth() {
        return birth;
    }

    public void setbirth(LocalDate birth) {
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

    public MultipartFile getDocumentPhoto() {
        return DocumentPhoto;
    }

    public void setDocumentPhoto(MultipartFile documentPhoto) {
        DocumentPhoto = documentPhoto;
    }
}
