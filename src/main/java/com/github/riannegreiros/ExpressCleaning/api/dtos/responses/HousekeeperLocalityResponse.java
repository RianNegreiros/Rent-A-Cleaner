package com.github.riannegreiros.ExpressCleaning.api.dtos.responses;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class HousekeeperLocalityResponse {
  private String fullName;

  private Double reputation;

  private String userPhoto;

  private String city;

  public HousekeeperLocalityResponse() {
  }

  public HousekeeperLocalityResponse(String fullName, Double reputation, String userPhoto, String city) {
    this.fullName = fullName;
    this.reputation = reputation;
    this.userPhoto = userPhoto;
    this.city = city;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public Double getReputation() {
    return reputation;
  }

  public void setReputation(Double reputation) {
    this.reputation = reputation;
  }

  public String getUserPhoto() {
    return userPhoto;
  }

  public void setUserPhoto(String userPhoto) {
    this.userPhoto = userPhoto;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }
}
