package com.github.riannegreiros.ExpressCleaning.core.services.checkdistance.adapters;

import com.github.riannegreiros.ExpressCleaning.core.services.checkdistance.dtos.DistanceResponse;

public interface CheckDistanceService {
    DistanceResponse calculateDistanceBetweenTwoZipCodes(String origin, String destination);
}
