package com.github.riannegreiros.ExpressCleaning.api.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(SnakeCaseStrategy.class)
public class PaymentRequest {
    @NotNull
    @NotEmpty
    private String cardHash;

    public PaymentRequest() {
    }

    public PaymentRequest(String cardHash) {
        this.cardHash = cardHash;
    }

    public String getCardHash() {
        return cardHash;
    }

    public void setCardHash(String cardHash) {
        this.cardHash = cardHash;
    }
}
