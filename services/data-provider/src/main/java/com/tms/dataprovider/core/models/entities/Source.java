package com.tms.dataprovider.core.models.entities;

import com.tms.dataprovider.core.constants.TableConstants;
import com.tms.dataprovider.core.enums.ProcessingStatus;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = TableConstants.SOURCE_TABLE)
public class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(nullable = false)
    String link;

    @Column(name = "absolute_link", nullable = false)
    String absoluteLink;

    @Column(nullable = false)
    String portal;

    @Column(name = "main_link", nullable = false)
    String mainLink;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id", nullable = false )
    Task task;

    @Column(name = "status_code", nullable = false)
    int statusCode;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    ProcessingStatus status;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "processed_data_id", nullable = true)
    ProcessedData processedData;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at",nullable = false)
    LocalDateTime createdAt;


}

