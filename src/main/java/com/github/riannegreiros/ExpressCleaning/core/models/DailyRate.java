package com.github.riannegreiros.ExpressCleaning.core.models;

import com.github.riannegreiros.ExpressCleaning.core.enums.DailyRateStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class DailyRate extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "attendance_date", nullable = false)
    private LocalDateTime attendanceDate;

    @Column(name = "attendance_time", nullable = false)
    private Integer attendanceTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DailyRateStatus status;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "commission_value", nullable = false)
    private BigDecimal commissionValue;

    @Column(nullable = false, length = 60)
    private String street;

    @Column(nullable = false, length = 10)
    private String number;

    @Column(nullable = false, length = 30)
    private String neighborhood;

    @Column(nullable = true, length = 100)
    private String complement;

    @Column(nullable = false, length = 30)
    private String city;

    @Column(nullable = false, length = 2)
    private String state;

    @Column(name = "zip_code", nullable = false, length = 8)
    private String zipCode;

    @Column(name = "ibge_code", nullable = false)
    private String ibgeCode;

    @Column(name = "bedroom_num", nullable = false)
    private Integer bedroomNum;

    @Column(name = "living_room_num", nullable = false)
    private Integer livingRoomNum;

    @Column(name = "kitchens_num", nullable = false)
    private Integer kitchensNum;

    @Column(name = "bathroom_num", nullable = false)
    private Integer bathroomNum;

    @Column(name = "backyard_num", nullable = false)
    private Integer backyardNum;

    @Column(name = "other_num", nullable = false)
    private Integer otherNum;

    @Column(nullable = true)
    private String observations;

    @Column(name = "cancellation_reason" ,nullable = true)
    private String cancellationReason;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private User client;

    @ManyToOne
    @JoinColumn(name = "housekeeper_id", nullable = true)
    private User housekeeper;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private ServiceModel service;

    @ManyToMany
    @JoinTable(
            name = "daily_rate_candidate",
            joinColumns = @JoinColumn(name = "daily_rate_id"),
            inverseJoinColumns = @JoinColumn(name = "candidate_id")
    )
    private List<User> candidates;

    @OneToMany(mappedBy = "dailyRate")
    private List<Payment> payments;

    public DailyRate() {
    }

    public DailyRate(Long id, LocalDateTime attendanceDate, Integer attendanceTime, DailyRateStatus status, BigDecimal price, BigDecimal commissionValue, String street, String number, String neighborhood, String complement, String city, String state, String zipCode, String ibgeCode, Integer bedroomNum, Integer livingRoomNum, Integer kitchensNum, Integer bathroomNum, Integer backyardNum, Integer otherNum, String observations, String cancellationReason, User client, User housekeeper, ServiceModel service, List<User> candidates, List<Payment> payments) {
        this.id = id;
        this.attendanceDate = attendanceDate;
        this.attendanceTime = attendanceTime;
        this.status = status;
        this.price = price;
        this.commissionValue = commissionValue;
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
        this.kitchensNum = kitchensNum;
        this.bathroomNum = bathroomNum;
        this.backyardNum = backyardNum;
        this.otherNum = otherNum;
        this.observations = observations;
        this.cancellationReason = cancellationReason;
        this.client = client;
        this.housekeeper = housekeeper;
        this.service = service;
        this.candidates = candidates;
        this.payments = payments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public DailyRateStatus getStatus() {
        return status;
    }

    public void setStatus(DailyRateStatus status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getCommissionValue() {
        return commissionValue;
    }

    public void setCommissionValue(BigDecimal commissionValue) {
        this.commissionValue = commissionValue;
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

    public Integer getKitchensNum() {
        return kitchensNum;
    }

    public void setKitchensNum(Integer kitchensNum) {
        this.kitchensNum = kitchensNum;
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

    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public User getHousekeeper() {
        return housekeeper;
    }

    public void setHousekeeper(User housekeeper) {
        this.housekeeper = housekeeper;
    }

    public ServiceModel getService() {
        return service;
    }

    public void setService(ServiceModel service) {
        this.service = service;
    }

    public List<User> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<User> candidates) {
        this.candidates = candidates;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyRate dailyRate = (DailyRate) o;
        return Objects.equals(id, dailyRate.id);
    }

    @Override
    public String toString() {
        return "DailyRate{" +
                "id=" + id +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Boolean isWithoutPayment() {
        return status.equals(DailyRateStatus.NO_PAYMENT);
    }

    public Boolean isPaid() {
        return status.equals(DailyRateStatus.PAID);
    }

    public Boolean isConfirmed() {
        return status.equals(DailyRateStatus.CONFIRMED);
    }

    public Boolean isCompleted() {
        return status.equals(DailyRateStatus.COMPLETED);
    }
}
