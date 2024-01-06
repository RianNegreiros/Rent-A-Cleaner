package com.github.riannegreiros.ExpressCleaning.core.services.checkaddress.dto;

public class AddressResponse {
  private String zipCode;

  private String street;

  private String complement;

  private String neighborhood;

  private String location;

  private String uf;

  private String ibge;

  public AddressResponse() {
  }

  public AddressResponse(String zipCode, String street, String complement, String neighborhood, String location,
      String uf, String ibge) {
    this.zipCode = zipCode;
    this.street = street;
    this.complement = complement;
    this.neighborhood = neighborhood;
    this.location = location;
    this.uf = uf;
    this.ibge = ibge;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getComplement() {
    return complement;
  }

  public void setComplement(String complement) {
    this.complement = complement;
  }

  public String getNeighborhood() {
    return neighborhood;
  }

  public void setNeighborhood(String neighborhood) {
    this.neighborhood = neighborhood;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getUf() {
    return uf;
  }

  public void setUf(String uf) {
    this.uf = uf;
  }

  public String getIbge() {
    return ibge;
  }

  public void setIbge(String ibge) {
    this.ibge = ibge;
  }
}
