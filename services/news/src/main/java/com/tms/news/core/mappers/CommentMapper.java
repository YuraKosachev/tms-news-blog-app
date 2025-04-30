package com.tms.news.core.mappers;

import com.tms.news.core.models.dtos.CommentCreateDto;
import com.tms.news.core.models.dtos.CommentDto;
import com.tms.news.core.models.dtos.CommentUpdateDto;
import com.tms.news.core.models.entities.Comment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CommentMapper {
    public Comment dtoToEntity(CommentCreateDto dto) {
        if(dto == null) return null;
        return Comment.builder()
                .content(dto.content())
                .author(dto.authorName())
                .authorId(dto.authorId())
                .publishDate(LocalDateTime.now())
                .build();
    }

    public void dtoToEntity(CommentUpdateDto dto, Comment entity ) {
        if(dto == null) return;
        entity.setContent(dto.content());
    }

    public CommentDto entityToDto(Comment entity) {
        if(entity == null) return null;
        return new CommentDto(
                entity.getId(),
                entity.getAuthor(),
                entity.getAuthorId(),
                entity.getContent(),
                entity.getCreatedAt());
    }
}
