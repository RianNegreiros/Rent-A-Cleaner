package com.github.riannegreiros.ExpressCleaning.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.HousekeeperLocalityResponse;
import com.github.riannegreiros.ExpressCleaning.core.models.User;

@Mapper(componentModel = "spring")
public interface HousekeeperApiMapper {
  HousekeeperApiMapper INSTANCE = Mappers.getMapper(HousekeeperApiMapper.class);

  @Mapping(target = "userPhoto", source = "userPhoto.url")
  HousekeeperLocalityResponse toHousekeeperLocalityResponse(User model);
}
