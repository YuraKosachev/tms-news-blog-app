package com.tms.news.services;

import com.tms.news.core.interfaces.services.ArticleService;
import com.tms.news.core.mappers.ArticleMapper;
import com.tms.news.core.models.dtos.*;
import com.tms.news.core.models.entities.Article;
import com.tms.news.repositories.ArticleRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import com.tms.news.core.exceptions.ItemNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    @Override
    public Page<ArticleShortDto> getAllShortArticles(Pageable pageable) {

        return articleRepository.
                findAll(pageable)
                .map(x->articleMapper.toShortDto(x));
    }

    @Override
    public Optional<ArticleDescriptionDto> getArticleById(UUID id) {
        return articleRepository.findById(id)
                .map(x->articleMapper.entityToDescriptionDto(x));
    }

    @Override
    public ArticleDto createArticle(ArticleCreateDto articleDto) {
        Article article = articleRepository.save(articleMapper.createDtoToentity(articleDto));
        return articleMapper.toArticleDto(article);
    }

    @Override
    public ArticleDto updateArticle(ArticleUpdateDto articleDto) {
        Article artical = articleRepository.findById(articleDto.id()).orElseThrow(()-> new ItemNotFoundException("article not found"));
        articleMapper.dtoToEntity(articleDto, artical);
        return articleMapper.toArticleDto(articleRepository.save(artical));
    }

    @Override
    public void deleteArticle(UUID id) {
        articleRepository.deleteById(id);
    }
}
