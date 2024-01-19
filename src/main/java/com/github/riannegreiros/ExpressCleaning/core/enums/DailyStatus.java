package com.github.riannegreiros.ExpressCleaning.core.enums;

public enum DailyStatus {

    NO_PAYMENT(1, "Awaiting payment"),
    PAID(2, "Paid"),
    CONFIRMED(3, "Cleaner select"),
    COMPLETED(4, "Attendance Confirmed"),
    CANCELLED(5, "Canceled"),
    REVIEWED(6, "Reviewed"),
    TRANSFERRED(7, "Transferred to Cleaner");

    private Integer id;

    private String description;

    DailyStatus(Integer id, String description) {
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
