package com.github.riannegreiros.ExpressCleaning.api.services;

import com.github.riannegreiros.ExpressCleaning.api.dtos.requests.DailyCancellationRequest;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.MessageResponse;
import com.github.riannegreiros.ExpressCleaning.core.enums.DailyStatus;
import com.github.riannegreiros.ExpressCleaning.core.exceptions.DailyNotFoundException;
import com.github.riannegreiros.ExpressCleaning.core.models.Daily;
import com.github.riannegreiros.ExpressCleaning.core.models.Rating;
import com.github.riannegreiros.ExpressCleaning.core.repositories.DailyRepository;
import com.github.riannegreiros.ExpressCleaning.core.repositories.RatingRepository;
import com.github.riannegreiros.ExpressCleaning.core.utils.SecurityUtils;
import com.github.riannegreiros.ExpressCleaning.core.validators.DailyCancellationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class DailyCancellationApiService {
    @Autowired
    private DailyRepository dailyRepository;

    @Autowired
    private DailyCancellationValidator validator;

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private RatingRepository ratingRepository;

    @Transactional(readOnly = false)
    public MessageResponse cancel(Long dailyId, DailyCancellationRequest request) {
        var daily = getDailyById(dailyId);
        validator.validate(daily);

        if (hasPenalization(daily)) {
            applyPenalty(daily);
        }
        // make total refund

        daily.setStatus(DailyStatus.CANCELLED);
        daily.setCancellationReason(request.getCancellationReason());
        dailyRepository.save(daily);

        return new MessageResponse("The daily has been successfully canceled!");
    }

    private void applyPenalty(Daily daily) {
        var loggerUser = securityUtils.getLoggedUser();
        if (loggerUser.isCleaner()) {
            penalizeCleaner(daily);
            // make total refund
        }
        // else make partial refund
    }

    private void penalizeCleaner(Daily daily) {
        var rating = new Rating.Builder()
                .nota(1.0)
                .description("Daily penalty canceled")
                .rated(daily.getCleaner())
                .visibility(false)
                .daily(daily)
                .build();
        ratingRepository.save(rating);
    }

    private Daily getDailyById(Long dailyId) {
        var message = String.format("Daily with id %d not found", dailyId);
        return dailyRepository.findById(dailyId)
                .orElseThrow(() -> new DailyNotFoundException(message));
    }

    private boolean hasPenalization(Daily daily) {
        var today = LocalDateTime.now();
        return ChronoUnit.HOURS.between(today, daily.getAttendanceDate()) < 24;
    }
}
