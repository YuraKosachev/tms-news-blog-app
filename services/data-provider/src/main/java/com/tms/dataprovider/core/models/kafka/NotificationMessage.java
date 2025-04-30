package com.tms.dataprovider.core.models.kafka;

import com.tms.dataprovider.core.enums.NotificationType;

public record NotificationMessage(
        boolean isArray,
        String issuer,
        NotificationType type,
        String content
) {
}