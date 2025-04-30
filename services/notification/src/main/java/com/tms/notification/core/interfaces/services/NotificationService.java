package com.tms.notification.core.interfaces.services;

import com.tms.notification.core.enums.NotificationType;
import com.tms.notification.core.enums.Status;
import com.tms.notification.core.models.dtos.NotificationDto;
import com.tms.notification.core.models.entities.Notification;

import java.time.LocalDate;
import java.util.List;

public interface NotificationService {

    List<NotificationDto> getNotificationsByDate(LocalDate date);
    List<NotificationDto> getNotificationsByStatusAndType(List<NotificationType> types, List<Status> statuses);
}
