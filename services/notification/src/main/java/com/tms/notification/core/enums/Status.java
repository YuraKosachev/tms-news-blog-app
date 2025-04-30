package com.tms.notification.core.enums;


public enum Status {
    NEW("new",0),
    PROCESSING("processing", 1),
    SENT("sent", 2),
    ERROR("error", 3);

    private String status;
    private int code;

    Status(String str, int ind) {
        status = str;
        code = ind;
    }

    public String getStatus() {
        return status;
    }
    public int getCode() {
        return code;
    }

    public static Status getStatusByCode(int code) {
        for (Status status : Status.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new EnumConstantNotPresentException(Status.class, "%d not found".formatted(code));
    }

    public static Status getStatusByString(String value) {
        for (Status status : Status.values()) {
            if (status.getStatus().equals(value)) {
                return status;
            }
        }
        throw new EnumConstantNotPresentException(Status.class, "%s not found".formatted(value));
    }
}
