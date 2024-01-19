package com.github.riannegreiros.ExpressCleaning.core.services.cleanerIndex.adapters;

import com.github.riannegreiros.ExpressCleaning.core.models.Daily;
import com.github.riannegreiros.ExpressCleaning.core.models.User;

public interface CleanerIndexService {
    public User selectBestCleaner(Daily daily);
}
