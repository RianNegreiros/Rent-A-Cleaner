package com.github.riannegreiros.ExpressCleaning.core.services;

import com.github.riannegreiros.ExpressCleaning.core.exceptions.PasswordResetNotFoundException;
import com.github.riannegreiros.ExpressCleaning.core.models.PasswordReset;
import com.github.riannegreiros.ExpressCleaning.core.repositories.PasswordResetRepository;
import com.github.riannegreiros.ExpressCleaning.core.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PasswordResetService {
    @Autowired
    private PasswordResetRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public PasswordReset createPasswordReset(String email) {
        if (userRepository.existsByEmail(email)) {
            var passwordReset = new PasswordReset.Builder()
                    .email(email)
                    .token(UUID.randomUUID().toString())
                    .build();
            return repository.save(passwordReset);
        }
        return null;
    }

    public void resetPassword(String passwordResetToken, String newPassword) {
        var passwordReset = getPasswordResetByToken(passwordResetToken);
        var user = userRepository.findByEmail(passwordReset.getEmail()).get();
        var newPasswordHash = passwordEncoder.encode(newPassword);
        user.setPassword(newPasswordHash);
        userRepository.save(user);
        repository.delete(passwordReset);
    }

    private PasswordReset getPasswordResetByToken(String passwordResetToken) {
        return repository.findByToken(passwordResetToken)
                .orElseThrow(PasswordResetNotFoundException::new);
    }
}
