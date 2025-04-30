package com.tms.news.repositories;

import com.tms.news.core.models.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {

    @Transactional(readOnly = true)
    @Query("""
            select c from Comment c 
            where c.article.id = :articleId
            """)
    List<Comment> getAllByArticleId(UUID articleId);
}
