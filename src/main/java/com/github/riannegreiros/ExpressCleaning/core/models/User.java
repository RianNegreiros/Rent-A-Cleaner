package com.github.riannegreiros.ExpressCleaning.core.models;

import java.time.LocalDate;
import java.util.List;

import com.github.riannegreiros.ExpressCleaning.core.enums.UserType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String fullName;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(name = "user_type", length = 8, nullable = false)
  @Enumerated(EnumType.STRING)
  private UserType userType;

  @Column(nullable = true, unique = true, length = 11)
  private String cpf;

  @Column(nullable = true)
  private LocalDate birth;

  @Column(nullable = true, length = 11)
  private String telephone;

  @Column(nullable = true)
  private Double reputation;

  @Column(name = "pix_key", nullable = true, unique = true)
  private String pixKey;

  @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
  @JoinColumn(name = "document_photo", nullable = true)
  private Photo documentPhoto;

  @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
  @JoinColumn(name = "user_photo", nullable = true)
  private Photo userPhoto;

  @ManyToMany
  @JoinTable(name = "cities_served_users", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "city_served_id"))
  private List<CityServed> citiesServeds;

  public User() {
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
    User other = (User) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "User [id=" + id + "]";
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFullname() {
    return fullName;
  }

  public void setFullname(String fullName) {
    this.fullName = fullName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public UserType getUserType() {
    return userType;
  }

  public void setUserType(UserType userType) {
    this.userType = userType;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public LocalDate getBirth() {
    return birth;
  }

  public void setBirth(LocalDate birth) {
    this.birth = birth;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public Double getReputation() {
    return reputation;
  }

  public void setReputation(Double reputation) {
    this.reputation = reputation;
  }

  public String getPixKey() {
    return pixKey;
  }

  public void setPixKey(String pixKey) {
    this.pixKey = pixKey;
  }

  public Photo getDocumentPhoto() {
    return documentPhoto;
  }

  public void setDocumentPhoto(Photo documentPhoto) {
    this.documentPhoto = documentPhoto;
  }

  public Photo getUserPhoto() {
    return userPhoto;
  }

  public void setUserPhoto(Photo userPhoto) {
    this.userPhoto = userPhoto;
  }

  public List<CityServed> getCitiesServeds() {
    return citiesServeds;
  }

  public void setCitiesServeds(List<CityServed> citiesServeds) {
    this.citiesServeds = citiesServeds;
  }
}
