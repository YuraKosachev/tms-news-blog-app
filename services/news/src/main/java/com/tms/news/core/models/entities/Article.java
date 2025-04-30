package com.tms.news.core.models.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, columnDefinition = "VARCHAR(2000)")
    private String title;

    @Column(nullable = true, columnDefinition = "VARCHAR(2000)")
    private String description;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = true, name = "author_id")
    private UUID authorId;

    @Column(nullable = true, name = "author_name",columnDefinition = "VARCHAR(400)")
    private String authorName;

    @Column(nullable = true, name="origin_link",columnDefinition = "VARCHAR(2000)")
    private String originLink;

    @Column(name = "sent_at", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime sentAt;

    @ManyToMany
    @JoinTable(
            name = "category_article",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    List<Category> categories;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article", cascade = CascadeType.DETACH)
    private List<Comment> comments;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "portal", nullable = true )
    private Portal portal;

    @Column(name = "published_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime publishedAt;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;
}
