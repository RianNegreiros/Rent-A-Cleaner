package com.github.riannegreiros.ExpressCleaning.core.models;

import jakarta.persistence.*;

@Entity
@Table(name = "token_black_list")
public class TokenBlackList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String token;

    public TokenBlackList() {
    }

    public TokenBlackList(Long id, String token) {
        this.id = id;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
