package com.tms.news.core.interfaces.services;

import com.tms.news.core.models.dtos.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ArticleService {
    Page<ArticleShortDto> getAllShortArticles(Pageable pageable);
    Optional<ArticleDescriptionDto> getArticleById(UUID id);
    ArticleDto createArticle(ArticleCreateDto articleDto);
    ArticleDto updateArticle(ArticleUpdateDto articleDto);
    void deleteArticle(UUID id);
}
