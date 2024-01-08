package com.github.riannegreiros.ExpressCleaning.api.dtos.responses;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ServiceResponse {
    private Long id;
    private String name;
    private BigDecimal minValue;
    private Integer numHours;
    private Integer percentageCommission;
    private Integer bedroomHours;
    private BigDecimal bedroomValue;
    private Integer livingRoomHours;
    private BigDecimal livingRoomValue;
    private Integer bathroomHours;
    private BigDecimal bathroomValue;
    private Integer kitchenHours;
    private BigDecimal kitchenValue;
    private Integer backyardHours;
    private BigDecimal backyardValue;
    private Integer othersHours;
    private BigDecimal othersValue;
    private Integer position;

    public ServiceResponse() {
    }

    public ServiceResponse(Long id, String name, BigDecimal minValue, Integer numHours, Integer percentageCommission, Integer bedroomHours, BigDecimal bedroomValue, Integer livingRoomHours, BigDecimal livingRoomValue, Integer bathroomHours, BigDecimal bathroomValue, Integer kitchenHours, BigDecimal kitchenValue, Integer backyardHours, BigDecimal backyardValue, Integer othersHours, BigDecimal othersValue, Integer position) {
        this.id = id;
        this.name = name;
        this.minValue = minValue;
        this.numHours = numHours;
        this.percentageCommission = percentageCommission;
        this.bedroomHours = bedroomHours;
        this.bedroomValue = bedroomValue;
        this.livingRoomHours = livingRoomHours;
        this.livingRoomValue = livingRoomValue;
        this.bathroomHours = bathroomHours;
        this.bathroomValue = bathroomValue;
        this.kitchenHours = kitchenHours;
        this.kitchenValue = kitchenValue;
        this.backyardHours = backyardHours;
        this.backyardValue = backyardValue;
        this.othersHours = othersHours;
        this.othersValue = othersValue;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMinValue() {
        return minValue;
    }

    public void setMinValue(BigDecimal minValue) {
        this.minValue = minValue;
    }

    public Integer getNumHours() {
        return numHours;
    }

    public void setNumHours(Integer numHours) {
        this.numHours = numHours;
    }

    public Integer getPercentageCommission() {
        return percentageCommission;
    }

    public void setPercentageCommission(Integer percentageCommission) {
        this.percentageCommission = percentageCommission;
    }

    public Integer getBedroomHours() {
        return bedroomHours;
    }

    public void setBedroomHours(Integer bedroomHours) {
        this.bedroomHours = bedroomHours;
    }

    public BigDecimal getBedroomValue() {
        return bedroomValue;
    }

    public void setBedroomValue(BigDecimal bedroomValue) {
        this.bedroomValue = bedroomValue;
    }

    public Integer getLivingRoomHours() {
        return livingRoomHours;
    }

    public void setLivingRoomHours(Integer livingRoomHours) {
        this.livingRoomHours = livingRoomHours;
    }

    public BigDecimal getLivingRoomValue() {
        return livingRoomValue;
    }

    public void setLivingRoomValue(BigDecimal livingRoomValue) {
        this.livingRoomValue = livingRoomValue;
    }

    public Integer getBathroomHours() {
        return bathroomHours;
    }

    public void setBathroomHours(Integer bathroomHours) {
        this.bathroomHours = bathroomHours;
    }

    public BigDecimal getBathroomValue() {
        return bathroomValue;
    }

    public void setBathroomValue(BigDecimal bathroomValue) {
        this.bathroomValue = bathroomValue;
    }

    public Integer getKitchenHours() {
        return kitchenHours;
    }

    public void setKitchenHours(Integer kitchenHours) {
        this.kitchenHours = kitchenHours;
    }

    public BigDecimal getKitchenValue() {
        return kitchenValue;
    }

    public void setKitchenValue(BigDecimal kitchenValue) {
        this.kitchenValue = kitchenValue;
    }

    public Integer getBackyardHours() {
        return backyardHours;
    }

    public void setBackyardHours(Integer backyardHours) {
        this.backyardHours = backyardHours;
    }

    public BigDecimal getBackyardValue() {
        return backyardValue;
    }

    public void setBackyardValue(BigDecimal backyardValue) {
        this.backyardValue = backyardValue;
    }

    public Integer getOthersHours() {
        return othersHours;
    }

    public void setOthersHours(Integer othersHours) {
        this.othersHours = othersHours;
    }

    public BigDecimal getOthersValue() {
        return othersValue;
    }

    public void setOthersValue(BigDecimal othersValue) {
        this.othersValue = othersValue;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
