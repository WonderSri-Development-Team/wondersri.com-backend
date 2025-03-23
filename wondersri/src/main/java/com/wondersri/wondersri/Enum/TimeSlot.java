package com.wondersri.wondersri.Enum;

public enum TimeSlot {
    SLOT_1("08:00-01:00"),
    SLOT_2("01:00-05:00");
    private final String displayName;
    TimeSlot(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }
}