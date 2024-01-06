package com.github.riannegreiros.ExpressCleaning.core.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Service {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 50, nullable = false)
  private String name;

  @Column(name = "min_value", nullable = false)
  private BigDecimal minValue;

  @Column(name = "num_hours", nullable = false)
  private Integer numHours;

  @Column(name = "percentage_commission", nullable = false)
  private Integer percentageCommission;

  @Column(name = "bedroom_hours", nullable = false)
  private Integer BedroomHours;

  @Column(name = "bedroom_value", nullable = false)
  private BigDecimal bedroomValue;

  @Column(name = "living_room_hours", nullable = false)
  private Integer livingRoomHours;

  @Column(name = "living_room_value", nullable = false)
  private BigDecimal livingRoomValue;

  @Column(name = "bathroom_hours", nullable = false)
  private Integer bathroomHours;

  @Column(name = "bathroom_value", nullable = false)
  private BigDecimal bathroomValue;

  @Column(name = "kitchen_hours", nullable = false)
  private Integer kitchenHours;

  @Column(name = "kitchen_value", nullable = false)
  private BigDecimal kitchenValue;

  @Column(name = "backyard_hours", nullable = false)
  private Integer backyardHours;

  @Column(name = "backyard_value", nullable = false)
  private BigDecimal backyardValue;

  @Column(name = "others_hours", nullable = false)
  private Integer othersHours;

  @Column(name = "others_value", nullable = false)
  private BigDecimal othersValue;

  @Column(nullable = false)
  private Integer position;

  public Service() {
  }

  public Service(Long id, String name, BigDecimal minValue, Integer numHours, Integer percentageCommission,
      Integer bedroomHours, BigDecimal bedroomValue, Integer livingRoomHours, BigDecimal livingRoomValue,
      Integer bathroomHours, BigDecimal bathroomValue, Integer kitchenHours, BigDecimal kitchenValue,
      Integer backyardHours, BigDecimal backyardValue, Integer othersHours, BigDecimal othersValue, Integer position) {
    this.id = id;
    this.name = name;
    this.minValue = minValue;
    this.numHours = numHours;
    this.percentageCommission = percentageCommission;
    BedroomHours = bedroomHours;
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Service other = (Service) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Service [id=" + id + "]";
  }
}
