package com.github.riannegreiros.ExpressCleaning.api.dtos.responses;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(SnakeCaseStrategy.class)
public class HousekeeperLocalityPagedResponse {

  private List<HousekeeperLocalityResponse> housekeepers;

  private Long numHousekeepers;

  public HousekeeperLocalityPagedResponse() {
  }

  public HousekeeperLocalityPagedResponse(
      List<HousekeeperLocalityResponse> housekeepers, Integer pageSize, Long totalElement) {
    this.housekeepers = housekeepers;
    this.numHousekeepers = totalElement > pageSize ? totalElement - pageSize : 0;
  }

  public List<HousekeeperLocalityResponse> getHousekeepers() {
    return housekeepers;
  }

  public void setHousekeepers(List<HousekeeperLocalityResponse> housekeepers) {
    this.housekeepers = housekeepers;
  }

  public Long getNumHousekeepers() {
    return numHousekeepers;
  }

  public void setNumHousekeepers(Long numHousekeepers) {
    this.numHousekeepers = numHousekeepers;
  }
}
