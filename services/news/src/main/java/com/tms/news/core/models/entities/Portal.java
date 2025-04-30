package com.tms.news.core.models.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "portals")
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Portal {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(unique = true, nullable = false)
    String name;

    @Column(name = "main_link", nullable=false)
    String mainLink;

    @Column( nullable = true)
    String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "portal", cascade = CascadeType.DETACH)
    List<Article> articles;
}
