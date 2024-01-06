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
}
