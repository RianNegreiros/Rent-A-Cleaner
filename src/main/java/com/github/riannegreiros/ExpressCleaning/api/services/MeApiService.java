package com.github.riannegreiros.ExpressCleaning.api.services;

import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.UserResponse;
import com.github.riannegreiros.ExpressCleaning.api.mappers.UserApiMapper;
import com.github.riannegreiros.ExpressCleaning.core.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeApiService {

    @Autowired
    private UserApiMapper userMapper;

    @Autowired
    private SecurityUtils securityUtils;

    public UserResponse getLoggedUser() {
        var userLogged = securityUtils.getLoggedUser();
        return userMapper.toResponse(userLogged);
    }
}
