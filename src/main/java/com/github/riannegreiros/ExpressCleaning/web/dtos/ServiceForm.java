package com.github.riannegreiros.ExpressCleaning.web.dtos;

import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class ServiceForm {

  @NotNull
  @Size(min = 3, max = 50)
  private String name;

  @NotNull
  @PositiveOrZero
  @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
  private BigDecimal minValue;

  @NotNull
  @PositiveOrZero
  private Integer numHours;

  @NotNull
  @PositiveOrZero
  @Max(100)
  @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
  private BigDecimal percentageCommission;

  @NotNull
  @PositiveOrZero
  private Integer bedroomHours;

  @NotNull
  @PositiveOrZero
  @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
  private BigDecimal bedroomValue;

  @NotNull
  @PositiveOrZero
  private Integer livingRoomHours;

  @NotNull
  @PositiveOrZero
  @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
  private BigDecimal livingRoomValue;

  @NotNull
  @PositiveOrZero
  private Integer bathroomHours;

  @NotNull
  @PositiveOrZero
  @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
  private BigDecimal bathroomValue;

  @NotNull
  @PositiveOrZero
  private Integer kitchenHours;

  @NotNull
  @PositiveOrZero
  @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
  private BigDecimal kitchenValue;

  @NotNull
  @PositiveOrZero
  private Integer backyardHours;

  @NotNull
  @PositiveOrZero
  @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
  private BigDecimal backyardValue;

  @NotNull
  @PositiveOrZero
  private Integer othersHours;

  @NotNull
  @PositiveOrZero
  @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
  private BigDecimal othersValue;

  @NotNull
  @Positive
  private Integer position;

  public ServiceForm() {
  }

  public ServiceForm(@NotNull @Size(min = 3, max = 50) String name, @NotNull @PositiveOrZero BigDecimal minValue,
      @NotNull @PositiveOrZero Integer numHours, @NotNull @PositiveOrZero @Max(100) BigDecimal percentageCommission,
      @NotNull @PositiveOrZero Integer bedroomHours, @NotNull @PositiveOrZero BigDecimal bedroomValue,
      @NotNull @PositiveOrZero Integer livingRoomHours, @NotNull @PositiveOrZero BigDecimal livingRoomValue,
      @NotNull @PositiveOrZero Integer bathroomHours, @NotNull @PositiveOrZero BigDecimal bathroomValue,
      @NotNull @PositiveOrZero Integer kitchenHours, @NotNull @PositiveOrZero BigDecimal kitchenValue,
      @NotNull @PositiveOrZero Integer backyardHours, @NotNull @PositiveOrZero BigDecimal backyardValue,
      @NotNull @PositiveOrZero Integer othersHours, @NotNull @PositiveOrZero BigDecimal othersValue,
      @NotNull @Positive Integer position) {
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

  public BigDecimal getPercentageCommission() {
    return percentageCommission;
  }

  public void setPercentageCommission(BigDecimal percentageCommission) {
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
