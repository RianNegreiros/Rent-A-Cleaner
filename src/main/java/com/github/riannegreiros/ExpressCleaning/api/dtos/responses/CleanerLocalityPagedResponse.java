package com.github.riannegreiros.ExpressCleaning.api.dtos.responses;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(SnakeCaseStrategy.class)
public class CleanerLocalityPagedResponse {

  private List<CleanerLocalityResponse> cleaners;

  private Long numCleaners;

  public CleanerLocalityPagedResponse() {
  }

  public CleanerLocalityPagedResponse(
          List<CleanerLocalityResponse> cleaners, Integer pageSize, Long totalElement) {
    this.cleaners = cleaners;
    this.numCleaners = totalElement > pageSize ? totalElement - pageSize : 0;
  }

  public List<CleanerLocalityResponse> getCleaners() {
    return cleaners;
  }

  public void setCleaners(List<CleanerLocalityResponse> cleaners) {
    this.cleaners = cleaners;
  }

  public Long getNumCleaners() {
    return numCleaners;
  }

  public void setNumCleaners(Long numCleaners) {
    this.numCleaners = numCleaners;
  }
}
