package com.tms.news.core.mappers;

import com.tms.news.core.models.dtos.*;
import com.tms.news.core.models.kafka.NewsPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.tms.news.core.models.entities.Article;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ArticleMapper {

    private final CategoryMapper categoryMapper;
    private final CommentMapper commentMapper;

    public ArticleShortDto toShortDto(Article article) {
        if (article == null) return null;
        return new ArticleShortDto(
                article.getId(),
                article.getTitle(),
                article.getDescription(),
                article.getAuthorName(),
                article.getPortal() != null ? article.getPortal().getName() : null,
                article.getOriginLink(),
                article.getPublishedAt());
    }
    public ArticleDto toArticleDto(Article article) {
        if(article == null) return null;
        return new ArticleDto(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getPublishedAt(),
                article.getCategories() != null
                        ? article.getCategories().stream().map(x->categoryMapper.entityToDto(x)).toList()
                        :null
        );
    }
    public Article createDtoToentity(ArticleCreateDto createDto){
        if(createDto == null) return null;
        return Article.builder()
                .authorId(createDto.authorId())
                .title(createDto.title())
                .content(createDto.content())
                .description(createDto.description())
                .authorName(createDto.authorName())
                .publishedAt(LocalDateTime.now())
                .build();
    }

    public void dtoToEntity(ArticleUpdateDto articleDto, Article article){
        article.setAuthorId(articleDto.authorId());
        article.setTitle(articleDto.title());
        article.setContent(articleDto.content());
        article.setDescription(articleDto.description());
        article.setAuthorName(articleDto.authorName());
    }

    public Article kafkaMessageToEntity(NewsPublisher message){
        if(message == null){
            return null;
        }

        return Article.builder()
                .content(message.content())
                .title(message.title())
                .authorName(message.authors())
                .originLink(message.link())
                .description(message.description())
                .publishedAt(message.publishedAt())
                .build();
    }

    public ArticleDescriptionDto entityToDescriptionDto(Article article){
        if(article == null){
            return null;
        }
        return new ArticleDescriptionDto(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getPublishedAt(),
                article.getCategories() != null
                        ? article.getCategories().stream()
                                .map(x->categoryMapper.entityToDto(x)).toList()
                        : null,
                article.getComments() != null
                        ? article.getComments().stream()
                                .map(x->commentMapper.entityToDto(x)).toList()
                        : null);
    }
}
