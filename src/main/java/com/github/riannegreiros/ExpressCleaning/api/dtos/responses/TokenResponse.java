package com.github.riannegreiros.ExpressCleaning.api.dtos.responses;

public class TokenResponse {
    private String access;
    private String refresh;

    public TokenResponse() {
    }

    public TokenResponse(String access, String refresh) {
        this.access = access;
        this.refresh = refresh;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getRefresh() {
        return refresh;
    }

    public void setRefresh(String refresh) {
        this.refresh = refresh;
    }
}
