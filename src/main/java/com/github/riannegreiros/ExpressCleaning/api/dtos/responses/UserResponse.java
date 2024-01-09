package com.github.riannegreiros.ExpressCleaning.api.dtos.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.riannegreiros.ExpressCleaning.core.enums.UserType;

import java.time.LocalDate;
import java.util.List;

@JsonNaming(SnakeCaseStrategy.class)
public class UserResponse extends HateoasResponse {

    private Long id;
    private String fullName;
    private String email;
    private Integer userType;
    private String cpf;
    private LocalDate birth;
    private String telephone;
    private String pixKey;
    private String userPhoto;

    public UserResponse(List<LinkResponse> links) {
        super(links);
    }

    public UserResponse() {
    }

    public UserResponse(List<LinkResponse> links, Long id, String fullName, String email, Integer userType, String cpf, LocalDate birth, String telephone, String pixKey, String userPhoto) {
        super(links);
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.userType = userType;
        this.cpf = cpf;
        this.birth = birth;
        this.telephone = telephone;
        this.pixKey = pixKey;
        this.userPhoto = userPhoto;
    }

    public UserResponse(Long id, String fullName, String email, Integer userType, String cpf, LocalDate birth, String telephone, String pixKey, String userPhoto) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.userType = userType;
        this.cpf = cpf;
        this.birth = birth;
        this.telephone = telephone;
        this.pixKey = pixKey;
        this.userPhoto = userPhoto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    @JsonIgnore
    public Boolean isClient() {
        return userType.equals(UserType.CLIENT.getId());
    }

}