package com.github.riannegreiros.ExpressCleaning.api.dtos.responses;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonNaming(SnakeCaseStrategy.class)
public class DailyResponse {
    private LocalDateTime attendanceDate;
    private Integer attendanceTime;
    private BigDecimal price;
    private String street;
    private String number;
    private String neighborhood;
    private String complement;
    private String city;
    private String state;
    private String zipCode;
    private String ibgeCode;
    private Integer bedroomNum;
    private Integer livingRoomNum;
    private Integer kitchenNum;
    private Integer bathroomNum;
    private Integer backyardNum;
    private Integer otherNum;
    private String observations;
    private Long service;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserDailyResponse client;
    private UserDailyResponse housekeeper;

    public DailyResponse() {
    }

    public DailyResponse(LocalDateTime attendanceDate, Integer attendanceTime, BigDecimal price, String street, String number, String neighborhood, String complement, String city, String state, String zipCode, String ibgeCode, Integer bedroomNum, Integer livingRoomNum, Integer kitchenNum, Integer bathroomNum, Integer backyardNum, Integer otherNum, String observations, Long service, LocalDateTime createdAt, LocalDateTime updatedAt, UserDailyResponse client, UserDailyResponse housekeeper) {
        this.attendanceDate = attendanceDate;
        this.attendanceTime = attendanceTime;
        this.price = price;
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.complement = complement;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.ibgeCode = ibgeCode;
        this.bedroomNum = bedroomNum;
        this.livingRoomNum = livingRoomNum;
        this.kitchenNum = kitchenNum;
        this.bathroomNum = bathroomNum;
        this.backyardNum = backyardNum;
        this.otherNum = otherNum;
        this.observations = observations;
        this.service = service;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.client = client;
        this.housekeeper = housekeeper;
    }

    public LocalDateTime getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(LocalDateTime attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public Integer getAttendanceTime() {
        return attendanceTime;
    }

    public void setAttendanceTime(Integer attendanceTime) {
        this.attendanceTime = attendanceTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getIbgeCode() {
        return ibgeCode;
    }

    public void setIbgeCode(String ibgeCode) {
        this.ibgeCode = ibgeCode;
    }

    public Integer getBedroomNum() {
        return bedroomNum;
    }

    public void setBedroomNum(Integer bedroomNum) {
        this.bedroomNum = bedroomNum;
    }

    public Integer getLivingRoomNum() {
        return livingRoomNum;
    }

    public void setLivingRoomNum(Integer livingRoomNum) {
        this.livingRoomNum = livingRoomNum;
    }

    public Integer getKitchenNum() {
        return kitchenNum;
    }

    public void setKitchenNum(Integer kitchenNum) {
        this.kitchenNum = kitchenNum;
    }

    public Integer getBathroomNum() {
        return bathroomNum;
    }

    public void setBathroomNum(Integer bathroomNum) {
        this.bathroomNum = bathroomNum;
    }

    public Integer getBackyardNum() {
        return backyardNum;
    }

    public void setBackyardNum(Integer backyardNum) {
        this.backyardNum = backyardNum;
    }

    public Integer getOtherNum() {
        return otherNum;
    }

    public void setOtherNum(Integer otherNum) {
        this.otherNum = otherNum;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Long getService() {
        return service;
    }

    public void setService(Long service) {
        this.service = service;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UserDailyResponse getClient() {
        return client;
    }

    public void setClient(UserDailyResponse client) {
        this.client = client;
    }

    public UserDailyResponse getHousekeeper() {
        return housekeeper;
    }

    public void setHousekeeper(UserDailyResponse housekeeper) {
        this.housekeeper = housekeeper;
    }
}
