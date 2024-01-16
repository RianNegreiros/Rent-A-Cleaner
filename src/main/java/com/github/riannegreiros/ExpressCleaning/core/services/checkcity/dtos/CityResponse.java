package com.github.riannegreiros.ExpressCleaning.core.services.checkcity.dtos;

public class CityResponse {

    private String ibge;

    private String city;

    private String state;

    public CityResponse() {
    }

    public CityResponse(String ibge, String city, String state) {
        this.ibge = ibge;
        this.city = city;
        this.state = state;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
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
}
