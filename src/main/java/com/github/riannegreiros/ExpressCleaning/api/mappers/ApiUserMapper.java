package com.github.riannegreiros.ExpressCleaning.api.mappers;

import com.github.riannegreiros.ExpressCleaning.api.dtos.requests.UserRequest;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.UserResponse;
import com.github.riannegreiros.ExpressCleaning.core.enums.UserType;
import com.github.riannegreiros.ExpressCleaning.core.models.User;
import com.github.riannegreiros.ExpressCleaning.web.dtos.UserRegisterForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.stream.Stream;

@Mapper(componentModel = "spring")
public interface ApiUserMapper {

    ApiUserMapper INSTANCE = Mappers.getMapper(ApiUserMapper.class);

    @Mapping(target = "password", source = "password")
    @Mapping(target = "documentPhoto", ignore = true)
    User toModel(UserRequest request);

    @Mapping(target = "userType", source = "userType.id")
    @Mapping(target = "userPhoto", source = "userPhoto.url")
    UserResponse toResponse(User model);

    @Mapping(target = "userType", source = "userType.id")
    @Mapping(target = "userPhoto", source = "userPhoto.url")
    UserRegisterForm toRegisterResponse(User model);

    default UserType integerToUserType(Integer value) {
        return Stream.of(UserType.values())
                .filter(userType -> userType.getId().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Type Invalid user"));
    }
}