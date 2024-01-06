package com.github.riannegreiros.ExpressCleaning.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.riannegreiros.ExpressCleaning.auth.models.AuthenticatedUser;
import com.github.riannegreiros.ExpressCleaning.core.repositories.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {

  @Autowired
  private UserRepository repository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    var message = String.format("User with email %s not found", email);

    return repository.findByEmail(email)
        .map(AuthenticatedUser::new)
        .orElseThrow(() -> new UsernameNotFoundException(message));
  }
}
