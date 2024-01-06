package com.github.riannegreiros.ExpressCleaning.api.dtos.responses;

public class HousekeeperLocalityResponse {
  private String fullname;

  private Double reputation;

  private String userPhoto;

  private String city;

  public HousekeeperLocalityResponse() {
  }

  public HousekeeperLocalityResponse(String fullname, Double reputation, String userPhoto, String city) {
    this.fullname = fullname;
    this.reputation = reputation;
    this.userPhoto = userPhoto;
    this.city = city;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
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
