package com.tms.news.services;

import com.tms.news.core.exceptions.ItemNotFoundException;
import com.tms.news.core.interfaces.services.CommentService;
import com.tms.news.core.mappers.CommentMapper;
import com.tms.news.core.models.dtos.CommentCreateDto;
import com.tms.news.core.models.dtos.CommentDto;
import com.tms.news.core.models.dtos.CommentUpdateDto;
import com.tms.news.core.models.entities.Article;
import com.tms.news.core.models.entities.Comment;
import com.tms.news.repositories.ArticleRepository;
import com.tms.news.repositories.CommentRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final CommentMapper commentMapper;
    @Override
    public List<CommentDto> getByArticleId(UUID articleId) {
        return commentRepository.getAllByArticleId(articleId)
                .stream()
                .map(c->commentMapper.entityToDto(c))
                .toList();
    }

    @Override
    public Optional<CommentDto> getById(UUID id) {
        return commentRepository.findById(id).map(commentMapper::entityToDto);
    }

    @Override
    public CommentDto create(CommentCreateDto commentDto) {
        Article article = articleRepository.getById(commentDto.articleId());
        Comment entity = commentMapper.dtoToEntity(commentDto);
        entity.setArticle(article);
        return commentMapper.entityToDto(commentRepository.save(entity));
    }

    @Override
    public void deleteById(UUID id) {
        commentRepository.deleteById(id);
    }

    @Override
    public CommentDto update(CommentUpdateDto commentUpdateDto) {
        Comment comment = commentRepository.findById(commentUpdateDto.id()).orElseThrow(()-> new ItemNotFoundException("comment not found"));
        commentMapper.dtoToEntity(commentUpdateDto, comment);
        return commentMapper.entityToDto(commentRepository.save(comment));
    }
}
