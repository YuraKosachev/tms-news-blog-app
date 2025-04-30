package com.tms.dataprovider.core.models.entities;

import com.tms.dataprovider.core.constants.TableConstants;
import com.tms.dataprovider.core.enums.ProcessingStatus;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.ws.rs.DefaultValue;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = TableConstants.TASK_TABLE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(nullable = false)
    String source;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_at", nullable = false)
    LocalDateTime startAt;

    @Column(name = "end_at", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime endAt;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    ProcessingStatus status;

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<Source> sources;
}

