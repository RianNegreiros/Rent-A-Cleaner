package com.github.riannegreiros.ExpressCleaning.api.services;

import com.github.riannegreiros.ExpressCleaning.api.dtos.requests.RefreshRequest;
import com.github.riannegreiros.ExpressCleaning.api.dtos.requests.TokenRequest;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.TokenResponse;
import com.github.riannegreiros.ExpressCleaning.core.services.TokenBlackListService;
import com.github.riannegreiros.ExpressCleaning.core.services.token.adapters.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthApiService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenBlackListService tokenBlackListService;

    public TokenResponse authenticate(TokenRequest tokenRequest) {
        var email = tokenRequest.getEmail();
        var password = tokenRequest.getPassword();

        var authentication = new UsernamePasswordAuthenticationToken(email, password);
        authenticationManager.authenticate(authentication);

        var access = tokenService.generateAccessToken(email);
        var refresh = tokenService.generateRefreshToken(email);

        return new TokenResponse(access, refresh);
    }

    public TokenResponse reAuthenticate(RefreshRequest refreshRequest) {
        var token = refreshRequest.getRefresh();
        tokenBlackListService.verifyToken(token);

        var email = tokenService.getSubjectFromRefreshToken(token);

        userDetailsService.loadUserByUsername(email);

        var access = tokenService.generateAccessToken(email);
        var refresh = tokenService.generateRefreshToken(email);

        tokenBlackListService.putTokenOnBlacklist(token);

        return new TokenResponse(access, refresh);
    }

    public void logout(RefreshRequest refreshRequest) {
        var token = refreshRequest.getRefresh();
        tokenBlackListService.putTokenOnBlacklist(token);
    }
}
