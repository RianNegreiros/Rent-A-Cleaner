package com.github.riannegreiros.ExpressCleaning.api.dtos.requests;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.riannegreiros.ExpressCleaning.core.validators.HourLaterThan;
import com.github.riannegreiros.ExpressCleaning.core.validators.ServiceExistsById;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonNaming(SnakeCaseStrategy.class)
public class DailyRequest {

    @NotNull
    @Future
    @HourLaterThan(startTime = 6)
    private LocalDateTime attendanceDate;

    @NotNull
    @Positive
    private Integer attendanceTime;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    @NotEmpty
    @Size(max = 60)
    private String street;

    @NotNull
    @NotEmpty
    @Size(max = 10)
    private String number;

    @NotNull
    @NotEmpty
    @Size(max = 30)
    private String neighborhood;

    @Size(max = 100)
    private String complement;

    @NotNull
    @NotEmpty
    @Size(max = 30)
    private String city;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 2)
    private String state;

    @NotNull
    @NotEmpty
    @Size(min = 8, max = 8)
    private String zipCode;

    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String ibgeCode;

    @NotNull
    @PositiveOrZero
    private Integer bedroomNum;

    @NotNull
    @PositiveOrZero
    private Integer livingRoomNum;

    @NotNull
    @PositiveOrZero
    private Integer kitchenNum;

    @NotNull
    @PositiveOrZero
    private Integer bathroomNum;

    @NotNull
    @PositiveOrZero
    private Integer backyardNum;

    @NotNull
    @PositiveOrZero
    private Integer otherNum;

    @Size(max = 255)
    private String observations;

    @NotNull
    @Positive
    @ServiceExistsById
    private Long service;

    public DailyRequest() {
    }

    public DailyRequest(LocalDateTime attendanceDate, Integer attendanceTime, BigDecimal price, String street, String number, String neighborhood, String complement, String city, String state, String zipCode, String ibgeCode, Integer bedroomNum, Integer livingRoomNum, Integer kitchenNum, Integer bathroomNum, Integer backyardNum, Integer otherNum, String observations, Long service) {
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
}
