package com.tms.notification.services;

import com.tms.notification.core.enums.NotificationType;
import com.tms.notification.core.enums.Status;
import com.tms.notification.core.interfaces.services.NotificationService;
import com.tms.notification.core.mappers.NotificationMapper;
import com.tms.notification.core.models.dtos.NotificationDto;
import com.tms.notification.repositories.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    @Override
    public List<NotificationDto> getNotificationsByDate(LocalDate date) {
        return notificationRepository.getNotificationByDate(date)
                .stream()
                .map(n->notificationMapper.entityToDto(n))
                .toList();
    }

    @Override
    public List<NotificationDto> getNotificationsByStatusAndType(List<NotificationType> types, List<Status> statuses) {
        return notificationRepository.getNotificationByType(types, statuses)
                .stream()
                .map(n->notificationMapper.entityToDto(n))
                .toList();
    }
}
