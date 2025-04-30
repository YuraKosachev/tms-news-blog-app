package com.tms.notification.repositories.implementation;

import com.tms.notification.core.models.entities.Notification;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public class NotificationInsertUpdateRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void insertWithQuery(Notification notification) {
        entityManager.createNativeQuery("insert into public.notifications (content,created_at,is_array,issuer,sent_at,status,type,id) values ((to_jsonb(?)),?,?,?,?,?,?,?)")
                .setParameter(1, notification.getContent())
                .setParameter(2, LocalDateTime.now())
                .setParameter(3, notification.isArray())
                .setParameter(4, notification.getIssuer())
                .setParameter(5, notification.getSentAt())
                .setParameter(6, notification.getStatus())
                .setParameter(7, notification.getType())
                .setParameter(8, UUID.randomUUID())
                .executeUpdate();
    }

    @Transactional
    public void updateStatusAndTimeQuery(Notification notification) {
        entityManager.createNativeQuery("""
                        update notifications set sent_at=?,status=?
                                             where id=?                                                     
                        """)
                .setParameter(1, notification.getSentAt())
                .setParameter(2, notification.getStatus())
                .setParameter(3, notification.getId())
                .executeUpdate();
    }

}
