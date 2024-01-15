package com.github.riannegreiros.ExpressCleaning.api.dtos.responses;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDate;

@JsonNaming(SnakeCaseStrategy.class)
public class UserDailyResponse {
    private Long id;

    private String full_name;

    private LocalDate birth;

    private String userPhoto;

    private String telephone;

    private Integer userType;

    private Double reputation;

    public UserDailyResponse() {
    }

    public UserDailyResponse(Long id, String full_name, LocalDate birth, String userPhoto, String telephone, Integer userType, Double reputation) {
        this.id = id;
        this.full_name = full_name;
        this.birth = birth;
        this.userPhoto = userPhoto;
        this.telephone = telephone;
        this.userType = userType;
        this.reputation = reputation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Double getReputation() {
        return reputation;
    }

    public void setReputation(Double reputation) {
        this.reputation = reputation;
    }
}
