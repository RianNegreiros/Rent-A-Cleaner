package com.github.riannegreiros.ExpressCleaning.api.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class RefreshRequest {
    @NotNull
    @NotEmpty
    private String refresh;

    public RefreshRequest() {
    }

    public RefreshRequest(String refresh) {
        this.refresh = refresh;
    }

    public String getRefresh() {
        return refresh;
    }

    public void setRefresh(String refresh) {
        this.refresh = refresh;
    }
}
