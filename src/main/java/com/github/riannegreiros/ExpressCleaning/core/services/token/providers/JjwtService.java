package com.github.riannegreiros.ExpressCleaning.core.services.token.providers;

import com.github.riannegreiros.ExpressCleaning.core.services.token.adapters.TokenService;
import com.github.riannegreiros.ExpressCleaning.core.services.token.exceptions.TokenServiceException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

@Service
public class JjwtService implements TokenService {

    @Value("${access.key}")
    private String accessKey;

    @Value("${access.expiration}")
    private int accessExpiration;

    @Value("${refresh.key}")
    private String refreshKey;

    @Value("${refresh.expiration}")
    private int refreshExpiration;

    @Override
    public String generateAccessToken(String subject) {
        return generateToken(accessKey, accessExpiration, subject);
    }

    @Override
    public String getSubjectFromAccessToken(String accessToken) {
        var claims = getClaims(accessToken, accessKey);

        return claims.getSubject();
    }

    @Override
    public String generateRefreshToken(String subject) {
        return generateToken(refreshKey, refreshExpiration, subject);
    }

    @Override
    public String getSubjectFromRefreshToken(String refreshToken) {
        var claims = getClaims(refreshToken, refreshKey);

        return claims.getSubject();
    }

    private String generateToken(String signKey, int expiration, String subject) {
        var claims = new HashMap<String, Object>();

        var currentHour = Instant.now();
        var expirationHour = currentHour.plusSeconds(expiration);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(currentHour.toEpochMilli()))
                .setExpiration(new Date(expirationHour.toEpochMilli()))
                .signWith(Keys.hmacShaKeyFor(signKey.getBytes()))
                .compact();
    }

    private Claims getClaims(String token, String signKey) {
        try {
            return tryGetClaims(token, signKey);
        } catch (JwtException exception) {
            throw new TokenServiceException(exception.getLocalizedMessage());
        }
    }

    private Claims tryGetClaims(String token, String signKey) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(signKey.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}