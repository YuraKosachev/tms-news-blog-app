package com.tms.notification.core.models.entities;

import com.tms.notification.core.enums.NotificationType;
import com.tms.notification.core.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "notifications")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    Status status;

    @Column(nullable = false)
    String issuer;

    @Column(nullable = false, name = "is_array")
    boolean isArray;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    NotificationType type;

    @Column(nullable = false, columnDefinition = "TEXT")
    String content;

    @CreationTimestamp
    @Column(name="created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime createdAt;

    @Column(name = "sent_at", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime sentAt;
}
