package com.github.riannegreiros.ExpressCleaning.core.services.cleanerIndex.providers;

import com.github.riannegreiros.ExpressCleaning.core.models.Daily;
import com.github.riannegreiros.ExpressCleaning.core.models.User;
import com.github.riannegreiros.ExpressCleaning.core.services.checkdistance.adapters.CheckDistanceService;
import com.github.riannegreiros.ExpressCleaning.core.services.checkdistance.exceptions.CheckDistanceServiceException;
import com.github.riannegreiros.ExpressCleaning.core.services.cleanerIndex.adapters.CleanerIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class CleanerIndexServiceImpl implements CleanerIndexService {

    @Autowired
    private CheckDistanceService checkDistanceService;

    @Override
    public User selectBestCleaner(Daily daily) {
        validateDaily(daily);

        var destination = daily.getZipCode();

        return daily.getCandidates()
                .stream()
                .max(Comparator.comparingDouble(candidate -> calculateIndex(candidate, destination)))
                .get();
    }

    private void validateDaily(Daily daily) {
        if (daily.getCandidates().isEmpty()) {
            throw new IllegalArgumentException("The list of per diem candidates must not be empty");
        }
    }

    private String getCandidateZipCode(User candidate) {
        validateCandidate(candidate);
        return candidate.getAddress().getZipCode();
    }

    private void validateCandidate(User candidate) {
        if (candidate.getAddress() == null) {
            throw new IllegalArgumentException("All daily candidates must have an address");
        }
    }

    private Double getDistance(String destination, String origin) {
        try {
            return checkDistanceService.calculateDistanceBetweenTwoZipCodes(origin, destination).getKmDistance();
        } catch (CheckDistanceServiceException exception) {
            return Double.MAX_VALUE;
        }
    }

    private Double calculateIndex(User candidate, String destination) {
        var origin = getCandidateZipCode(candidate);
        var distance = getDistance(destination, origin);
        var reputation = candidate.getReputation();

        return (reputation - (distance / 10.0)) / 2.0;
    }
}
