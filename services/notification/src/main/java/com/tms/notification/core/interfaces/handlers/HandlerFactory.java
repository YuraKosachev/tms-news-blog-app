package com.tms.notification.core.interfaces.handlers;

import com.tms.notification.core.enums.NotificationType;

public interface HandlerFactory {
    Handler getHandler(NotificationType type);
}
