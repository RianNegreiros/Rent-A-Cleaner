package com.github.riannegreiros.ExpressCleaning.api.mappers;

import com.github.riannegreiros.ExpressCleaning.api.dtos.requests.RatingRequest;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.RatingResponse;
import com.github.riannegreiros.ExpressCleaning.core.models.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ApiRatingMapper {
    ApiRatingMapper INSTANCE = Mappers.getMapper(ApiRatingMapper.class);

    Rating toModel(RatingRequest request);

    @Mapping(target = "reviewerName", source = "reviewer.fullName")
    @Mapping(target = "reviewerPhoto", source = "reviewer.userPhoto.url")
    RatingResponse toResponse(Rating model);
}
