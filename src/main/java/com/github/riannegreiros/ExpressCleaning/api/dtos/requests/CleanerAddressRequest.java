package com.github.riannegreiros.ExpressCleaning.api.dtos.requests;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CleanerAddressRequest {

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 60)
    private String street;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 10)
    private String number;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 30)
    private String neighborhood;

    @Size(max = 255)
    private String complement;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 8)
    private String zipCode;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 30)
    private String city;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 2)
    private String state;

    public CleanerAddressRequest() {
    }

    public CleanerAddressRequest(String street, String number, String neighborhood, String complement, String zipCode, String city, String state) {
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.complement = complement;
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
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