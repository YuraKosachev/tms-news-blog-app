package com.tms.notification.core.models.kafka;

import com.tms.notification.core.enums.NotificationType;
import com.tms.notification.core.enums.Status;

public record NotificationMessage(
        boolean isArray,
        String issuer,
        NotificationType type,
        String content
) {
}
