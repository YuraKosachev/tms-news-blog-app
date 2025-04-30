package com.tms.news.core.models.kafka;

import com.tms.news.core.enums.NotificationType;

public record NotificationMessage(
        boolean isArray,
        String issuer,
        NotificationType type,
        String content
) {
}