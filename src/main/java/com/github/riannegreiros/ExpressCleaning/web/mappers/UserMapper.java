package com.github.riannegreiros.ExpressCleaning.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.github.riannegreiros.ExpressCleaning.core.models.User;
import com.github.riannegreiros.ExpressCleaning.web.dtos.UserEditForm;
import com.github.riannegreiros.ExpressCleaning.web.dtos.UserRegisterForm;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  User toModel(UserRegisterForm form);

  User toModel(UserEditForm form);

  UserEditForm toForm(User model);
}
