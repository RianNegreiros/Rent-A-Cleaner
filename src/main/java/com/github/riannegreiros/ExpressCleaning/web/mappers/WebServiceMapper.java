package com.github.riannegreiros.ExpressCleaning.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.github.riannegreiros.ExpressCleaning.core.models.ServiceModel;
import com.github.riannegreiros.ExpressCleaning.web.dtos.ServiceForm;

@Mapper(componentModel = "spring")
public interface WebServiceMapper {

  WebServiceMapper INSTANCE = Mappers.getMapper(WebServiceMapper.class);

  ServiceModel toModel(ServiceForm form);

  ServiceForm toForm(ServiceModel model);
}