package com.github.riannegreiros.ExpressCleaning.core.services.token.adapters;

public interface TokenService {

    String generateAccessToken(String subject);

    String getSubjectFromAccessToken(String accessToken);

    String generateRefreshToken(String subject);

    String getSubjectFromRefreshToken(String refreshToken);
}
