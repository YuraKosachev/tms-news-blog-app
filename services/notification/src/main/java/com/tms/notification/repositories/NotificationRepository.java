package com.tms.notification.repositories;

import com.tms.notification.core.enums.NotificationType;
import com.tms.notification.core.enums.Status;
import com.tms.notification.core.models.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID> {
    @Query("""
            select n from Notification n
            where n.status in :statuses and n.type in :types 
                        """)
    List<Notification> getNotificationByType(List<NotificationType> types, List<Status> statuses );

    @Query("""
            select n from Notification n
            where DATE(n.createdAt) = :date 
                        """)
    List<Notification> getNotificationByDate(LocalDate date);

//    @Query("insert ")
//    Notification insert(Notification notification);
}
