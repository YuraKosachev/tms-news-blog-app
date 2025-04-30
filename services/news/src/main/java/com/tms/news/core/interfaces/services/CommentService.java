package com.tms.news.core.interfaces.services;

import com.tms.news.core.models.dtos.CommentCreateDto;
import com.tms.news.core.models.dtos.CommentDto;
import com.tms.news.core.models.dtos.CommentUpdateDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentService {

    List<CommentDto> getByArticleId(UUID articleId);
    Optional<CommentDto> getById(UUID id);
    CommentDto create(CommentCreateDto commentDto);
    void deleteById(UUID id);
    CommentDto update(CommentUpdateDto commentUpdateDto);
}
