package com.github.riannegreiros.ExpressCleaning.core.services.checkcity.adapters;

import com.github.riannegreiros.ExpressCleaning.core.services.checkcity.dtos.CityResponse;
import com.github.riannegreiros.ExpressCleaning.core.services.checkcity.exceptions.CheckCityServiceException;

public interface CheckCityService {
    CityResponse findCityByIbgeCode(String ibgeCode) throws CheckCityServiceException;
}
