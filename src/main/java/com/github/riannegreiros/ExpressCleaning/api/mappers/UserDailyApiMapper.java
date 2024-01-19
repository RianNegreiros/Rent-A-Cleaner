package com.github.riannegreiros.ExpressCleaning.api.mappers;

import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.UserDailyResponse;
import com.github.riannegreiros.ExpressCleaning.core.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserDailyApiMapper {

    UserDailyApiMapper INSTANCE = Mappers.getMapper(UserDailyApiMapper.class);

    @Mapping(target = "userType", source = "userType.id")
    @Mapping(target = "userPhoto", source = "userPhoto.url")
    UserDailyResponse toResponse(User model);
}
