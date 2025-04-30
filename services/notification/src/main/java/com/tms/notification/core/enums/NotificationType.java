package com.tms.notification.core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public enum NotificationType {
    ARTICLE("article",0),
    APPLICATION("application",1);

    private String type;
    private int code;

    NotificationType(String str, int ind) {
        type = str;
        code = ind;
    }

    public String getType() {
        return type;
    }
    public int getCode() {
        return code;
    }

    public static NotificationType getStatusByCode(int code) {
        for (NotificationType status : NotificationType.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new EnumConstantNotPresentException(NotificationType.class, "%d not found".formatted(code));
    }

    public static NotificationType getStatusByString(String value) {
        for (NotificationType status : NotificationType.values()) {
            if (status.getType().equals(value)) {
                return status;
            }
        }
        throw new EnumConstantNotPresentException(NotificationType.class, "%s not found".formatted(value));
    }
}
