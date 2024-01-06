package com.github.riannegreiros.ExpressCleaning.api.dtos.responses;

public class AvailabilityResponse {
  private Boolean availabilities;

  public AvailabilityResponse() {
  }

  public AvailabilityResponse(Boolean availabilities) {
    this.availabilities = availabilities;
  }

  public Boolean getAvailabilities() {
    return availabilities;
  }

  public void setAvailabilities(Boolean availabilities) {
    this.availabilities = availabilities;
  }
}
