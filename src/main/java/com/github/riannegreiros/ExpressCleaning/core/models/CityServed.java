package com.github.riannegreiros.ExpressCleaning.core.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class CityServed {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "codigo_ibge", nullable = false)
  private String ibgeCode;

  @Column(nullable = false)
  private String city;

  @Column(nullable = false)
  private String state;

  @ManyToMany(mappedBy = "citiesServed")
  private List<User> users;

  public CityServed() {
  }

  public CityServed(Long id, String ibgeCode, String city, String state, List<User> users) {
    this.id = id;
    this.ibgeCode = ibgeCode;
    this.city = city;
    this.state = state;
    this.users = users;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getIbgeCode() {
    return ibgeCode;
  }

  public void setIbgeCode(String ibgeCode) {
    this.ibgeCode = ibgeCode;
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

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
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
    CityServed other = (CityServed) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "CityServed [id=" + id + "]";
  }
}
