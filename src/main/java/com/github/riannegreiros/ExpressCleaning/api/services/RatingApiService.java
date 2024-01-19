package com.github.riannegreiros.ExpressCleaning.api.services;

import com.github.riannegreiros.ExpressCleaning.api.dtos.requests.RatingRequest;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.MessageResponse;
import com.github.riannegreiros.ExpressCleaning.api.mappers.RatingApiMapper;
import com.github.riannegreiros.ExpressCleaning.core.exceptions.DailyNotFoundException;
import com.github.riannegreiros.ExpressCleaning.core.models.Daily;
import com.github.riannegreiros.ExpressCleaning.core.models.Rating;
import com.github.riannegreiros.ExpressCleaning.core.models.User;
import com.github.riannegreiros.ExpressCleaning.core.publishers.NewRatingPublisher;
import com.github.riannegreiros.ExpressCleaning.core.repositories.DailyRepository;
import com.github.riannegreiros.ExpressCleaning.core.repositories.RatingRepository;
import com.github.riannegreiros.ExpressCleaning.core.utils.SecurityUtils;
import com.github.riannegreiros.ExpressCleaning.core.validators.RatingValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingApiService {
    @Autowired
    private RatingRepository repository;

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private DailyRepository dailyRepository;

    @Autowired
    private RatingApiMapper mapper;

    @Autowired
    private RatingValidator validator;

    @Autowired
    private NewRatingPublisher newRatingPublisher;

    public MessageResponse rateDaily(RatingRequest request, Long id) {
        var daily = searchDailyById(id);
        var reviewer = securityUtils.getLoggedUser();
        var model = mapper.toModel(request);
        model.setReviewer(reviewer);
        model.setDaily(daily);
        model.setVisibility(true);
        model.setRated(getRated(model));

        validator.validate(model);

        model = repository.save(model);
        newRatingPublisher.publish(model);

        return new MessageResponse("Successful rated!");
    }

    private User getRated(Rating model) {
        if (model.getReviewer().isClient()) {
            return model.getDaily().getHousekeeper();
        }
        return model.getDaily().getClient();
    }

    private Daily searchDailyById(Long id) {
        var message = String.format("Daily with id %d not found", id);
        return dailyRepository.findById(id)
                .orElseThrow(() -> new DailyNotFoundException(message));
    }
}
