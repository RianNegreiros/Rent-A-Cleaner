package com.github.riannegreiros.ExpressCleaning.api.mappers;

import com.github.riannegreiros.ExpressCleaning.api.dtos.requests.UserRequest;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.UserRegisterResponse;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.UserResponse;
import com.github.riannegreiros.ExpressCleaning.core.enums.UserType;
import com.github.riannegreiros.ExpressCleaning.core.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.stream.Stream;

@Mapper(componentModel = "spring")
public interface UserApiMapper {

    UserApiMapper INSTANCE = Mappers.getMapper(UserApiMapper.class);

    @Mapping(target = "password", source = "password")
    @Mapping(target = "documentPhoto", ignore = true)
    User toModel(UserRequest request);

    @Mapping(target = "userType", source = "userType.id")
    @Mapping(target = "userPhoto", source = "userPhoto.url")
    UserResponse toResponse(User model);

    @Mapping(target = "userType", source = "userType.id")
    @Mapping(target = "userPhoto", source = "userPhoto.url")
    UserRegisterResponse toRegisterResponse(User model);

    default UserType integerToUserType(Integer value) {
        return Stream.of(UserType.values())
                .filter(userType -> userType.getId().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Type Invalid user"));
    }
}