package com.tms.notification.tasks;

import com.google.gson.Gson;
import com.tms.notification.core.enums.NotificationType;
import com.tms.notification.core.enums.Status;
import com.tms.notification.core.interfaces.handlers.HandlerFactory;
import com.tms.notification.repositories.NotificationRepository;
import com.tms.notification.repositories.implementation.NotificationInsertUpdateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SendNotificationTask {

    private final NotificationRepository notificationRepository;
    private final NotificationInsertUpdateRepository notificationInsertUpdateRepository;
    private final Gson gson;
    private final HandlerFactory handlerFactory;
    @Scheduled(cron = "${task.notification.cron}")
    public void sendNotification() {

        var notifications = notificationRepository
                .getNotificationByType(List.of(NotificationType.ARTICLE), List.of(Status.ERROR, Status.NEW));

        if(notifications.isEmpty()) return;

        try {
            notifications.forEach(n -> {
                n.setStatus(Status.PROCESSING);
            });
            notificationRepository.saveAll(notifications);

            notifications.forEach(n->handlerFactory.getHandler(n.getType()).handle(n));

            notifications.forEach(n -> {
                n.setStatus(Status.SENT);
                n.setSentAt(LocalDateTime.now());
                //notificationInsertUpdateRepository.updateStatusAndTimeQuery(n);
            });
            notificationRepository.saveAll(notifications);
        } catch (Exception e) {
            notifications.forEach(n -> {
                n.setStatus(Status.ERROR);
                //notificationInsertUpdateRepository.updateStatusAndTimeQuery(n);
            });
            notificationRepository.saveAll(notifications);
        }
    }

}