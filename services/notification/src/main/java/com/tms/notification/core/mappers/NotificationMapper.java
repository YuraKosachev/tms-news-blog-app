package com.tms.notification.core.mappers;

import com.tms.notification.core.enums.Status;
import com.tms.notification.core.models.dtos.NotificationDto;
import com.tms.notification.core.models.entities.Notification;
import com.tms.notification.core.models.kafka.NotificationMessage;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public NotificationDto entityToDto(Notification entity){
        if(entity == null) return null;
        return new NotificationDto(
                entity.getId(),
                entity.isArray(),
                entity.getStatus().getStatus(),
                entity.getType().getType(),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getSentAt()
        );
    }
    public Notification messageToEntity(NotificationMessage message){
        if(message == null) return null;
        return Notification.builder()
                .status(Status.NEW)
                .type(message.type())
                .content(message.content())
                .isArray(message.isArray())
                .issuer(message.issuer())
                .build();
    }
}
