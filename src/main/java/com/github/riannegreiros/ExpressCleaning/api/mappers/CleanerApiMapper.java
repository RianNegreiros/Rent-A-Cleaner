package com.github.riannegreiros.ExpressCleaning.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.CleanerLocalityResponse;
import com.github.riannegreiros.ExpressCleaning.core.models.User;

@Mapper(componentModel = "spring")
public interface CleanerApiMapper {
  CleanerApiMapper INSTANCE = Mappers.getMapper(CleanerApiMapper.class);

  @Mapping(target = "userPhoto", source = "userPhoto.url")
  CleanerLocalityResponse toCleanerLocalityResponse(User model);
}
