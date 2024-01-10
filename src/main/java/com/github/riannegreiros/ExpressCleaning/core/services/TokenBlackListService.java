package com.github.riannegreiros.ExpressCleaning.core.services;

import com.github.riannegreiros.ExpressCleaning.core.exceptions.TokenInTheBlackListException;
import com.github.riannegreiros.ExpressCleaning.core.models.TokenBlackList;
import com.github.riannegreiros.ExpressCleaning.core.repositories.TokenBlackListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenBlackListService {

    @Autowired
    private TokenBlackListRepository repository;

    public void verifyToken(String token) {
        if (repository.existsByToken(token)) {
            throw new TokenInTheBlackListException();
        }
    }

    public void putTokenOnBlacklist(String token) {
        if (!repository.existsByToken(token)) {
            var tokenBlackList = new TokenBlackList();
            tokenBlackList.setToken(token);
            repository.save(tokenBlackList);
        }
    }
}