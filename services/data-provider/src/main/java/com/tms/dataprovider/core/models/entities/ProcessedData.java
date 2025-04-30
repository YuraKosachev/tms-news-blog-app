package com.tms.dataprovider.core.models.entities;

import com.tms.dataprovider.core.constants.TableConstants;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TableConstants.PROCESSED_DATA_TABLE)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProcessedData {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(nullable = false, columnDefinition = "VARCHAR(2000)")
    String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    String content;

    @Column(nullable = true, columnDefinition = "VARCHAR(2000)")
    String description;

    @Column(nullable = true)
    String author;

    @Column(nullable = true)
    String category;

    @CreationTimestamp
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime createdAt;

    @Column(name="published_at" ,nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime publishedAt;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "source_id", nullable = false)
    Source source;

    @Column(name = "sent_at", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime sentAt;
}
