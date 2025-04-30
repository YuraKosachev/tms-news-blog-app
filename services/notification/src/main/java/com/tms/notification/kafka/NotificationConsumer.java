package com.tms.notification.kafka;

import com.tms.notification.core.mappers.NotificationMapper;
import com.tms.notification.core.models.kafka.NotificationMessage;
import com.tms.notification.repositories.NotificationRepository;
import com.tms.notification.repositories.implementation.NotificationInsertUpdateRepository;
import lombok.RequiredArgsConstructor;
import com.tms.notification.core.constants.KafkaConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationConsumer {

    private final NotificationInsertUpdateRepository notificationInsertRepository;
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    @KafkaListener(topics = KafkaConstants.NOTIFICATION_TOPIC )
    public void consumerNews(NotificationMessage newsMessage){
        log.info("Notification service consumed message");
        try {
            var entity = notificationMapper.messageToEntity(newsMessage);
            notificationInsertRepository.insertWithQuery(entity);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        log.info("Notification service completed handling message");
    }
}
