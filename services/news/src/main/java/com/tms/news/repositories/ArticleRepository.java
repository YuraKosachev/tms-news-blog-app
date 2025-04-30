package com.tms.news.repositories;

import com.tms.news.core.models.dtos.ArticleDescriptionDto;
import com.tms.news.core.models.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ArticleRepository extends JpaRepository<Article, UUID> {
    @Query("select a from Article a where a.sentAt is null")
    List<Article> getNotSent();
}
