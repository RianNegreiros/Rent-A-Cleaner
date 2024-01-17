package com.github.riannegreiros.ExpressCleaning.core.services.housekeeperIndex.adapters;

import com.github.riannegreiros.ExpressCleaning.core.models.Daily;
import com.github.riannegreiros.ExpressCleaning.core.models.User;

public interface HousekeeperIndexService {
    public User selectBestHousekeeper(Daily daily);
}
