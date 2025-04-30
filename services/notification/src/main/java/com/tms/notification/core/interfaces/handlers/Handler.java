package com.tms.notification.core.interfaces.handlers;

import com.tms.notification.core.models.entities.Notification;

public interface Handler {
    void handle(Notification notification);
}
