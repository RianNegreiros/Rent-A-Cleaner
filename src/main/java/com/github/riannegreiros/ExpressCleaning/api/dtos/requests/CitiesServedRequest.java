package com.github.riannegreiros.ExpressCleaning.api.dtos.requests;

import java.util.List;

public class CitiesServedRequest {
    private List<CityServedRequest> cities;

    public CitiesServedRequest() {
    }

    public CitiesServedRequest(List<CityServedRequest> cities) {
        this.cities = cities;
    }

    public List<CityServedRequest> getCities() {
        return cities;
    }

    public void setCities(List<CityServedRequest> cities) {
        this.cities = cities;
    }
}
