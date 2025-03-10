package com.wondersri.wondersri.Enum;

public enum TimeSlot {
    SLOT_1("08:00-10:00"),
    SLOT_2("10:00-12:00"),
    SLOT_3("13:00-14:00");

    private final String displayName;

    TimeSlot(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}