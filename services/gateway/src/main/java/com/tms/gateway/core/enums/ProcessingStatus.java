package com.tms.gateway.core.enums;

public enum ProcessingStatus {
    PROCESSING("processing", 0),
    OK("ok", 1),
    ERROR("error", 2),
    PARTIAL_ERROR("partial error", 3);

    private String status;
    private int code;

    ProcessingStatus(String str, int ind) {
        status = str;
        code = ind;
    }

    public String getStatus() {
        return status;
    }
    public int getCode() {
        return code;
    }

    public static ProcessingStatus getStatusByCode(int code) {
        for (ProcessingStatus status : ProcessingStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new EnumConstantNotPresentException(ProcessingStatus.class, "%d not found".formatted(code));
    }

    public static ProcessingStatus getStatusByString(String value) {
        for (ProcessingStatus status : ProcessingStatus.values()) {
            if (status.getStatus().equals(value)) {
                return status;
            }
        }
        throw new EnumConstantNotPresentException(ProcessingStatus.class, "%s not found".formatted(value));
    }
}
