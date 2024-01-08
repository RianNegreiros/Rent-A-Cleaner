package com.github.riannegreiros.ExpressCleaning.core.services.token.adapters;

public interface TokenService {
  String generateAccessToken(String subject);

  String getSubjcetFromAccessToken(String accessToken);

  String generateRefreshToken(String subject);

  String getSubjectFromRefreshToken(String refreshToken);
}
