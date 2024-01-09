package com.github.riannegreiros.ExpressCleaning.core.enums;

public enum DailyRateStatus {

    NO_PAYMENT(1, "Awaiting payment"),
    PAID(2, "Paid"),
    CONFIRMED(3, "Housekeeper select"),
    COMPLETED(4, "Attendance Confirmed"),
    CANCELLED(5, "Canceled"),
    REVIEWED(6, "Reviewed"),
    TRANSFERRED(7, "Transferred to Housekeeper");

    private Integer id;

    private String description;

    DailyRateStatus(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
