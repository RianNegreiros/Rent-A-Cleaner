package com.github.riannegreiros.ExpressCleaning.core.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "service")
public class ServiceModel {
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
  private BigDecimal percentageCommission;

  @Column(name = "bedroom_hours", nullable = false)
  private Integer bedroomHours;

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

  public ServiceModel() {
  }

  public ServiceModel(Long id, String name, BigDecimal minValue, Integer numHours, BigDecimal percentageCommission,
      Integer bedroomHours, BigDecimal bedroomValue, Integer livingRoomHours, BigDecimal livingRoomValue,
      Integer bathroomHours, BigDecimal bathroomValue, Integer kitchenHours, BigDecimal kitchenValue,
      Integer backyardHours, BigDecimal backyardValue, Integer othersHours, BigDecimal othersValue, Integer position) {
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
    ServiceModel other = (ServiceModel) obj;
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
