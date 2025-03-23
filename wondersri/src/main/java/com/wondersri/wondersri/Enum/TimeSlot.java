package com.wondersri.wondersri.Enum;

public enum TimeSlot {
    SLOT_1("08:00 am -01:00 pm"),
    SLOT_2("01:00 pm -05:00 pm");
    private final String displayName;
    TimeSlot(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }
}