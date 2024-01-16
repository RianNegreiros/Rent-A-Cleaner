package com.github.riannegreiros.ExpressCleaning.api.dtos.responses;

public class CityServedResponse {
    private Long id;
    private String city;
    private String state;
    private String ibgeCode;

    public CityServedResponse() {
    }

    public CityServedResponse(Long id, String city, String state, String ibgeCode) {
        this.id = id;
        this.city = city;
        this.state = state;
        this.ibgeCode = ibgeCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIbgeCode() {
        return ibgeCode;
    }

    public void setIbgeCode(String ibgeCode) {
        this.ibgeCode = ibgeCode;
    }
}
