package com.github.riannegreiros.ExpressCleaning.web.services;

import com.github.riannegreiros.ExpressCleaning.core.enums.DailyStatus;
import com.github.riannegreiros.ExpressCleaning.core.exceptions.DailyNotFoundException;
import com.github.riannegreiros.ExpressCleaning.core.models.Daily;
import com.github.riannegreiros.ExpressCleaning.core.repositories.DailyRepository;
import com.github.riannegreiros.ExpressCleaning.core.validators.TransferValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebDailyService {

    @Autowired
    private DailyRepository dailyRepository;

    @Autowired
    private TransferValidator validator;

    public List<Daily> getDaily(String client, List<DailyStatus> status) {
        var dailySort = Sort.sort(Daily.class);
        var sort = dailySort.by(Daily::getAttendanceDate).descending();
        return dailyRepository.findFiltered(client, status, sort);
    }


    public void pay(Long id) {
        var daily = getDailyById(id);
        validator.validate(daily);
        daily.setStatus(DailyStatus.TRANSFERRED);
        dailyRepository.save(daily);
    }

    private Daily getDailyById(Long id) {
        var message = String.format("Daily with id %d not found!", id);
        return dailyRepository.findById(id)
                .orElseThrow(() -> new DailyNotFoundException(message));
    }
}
