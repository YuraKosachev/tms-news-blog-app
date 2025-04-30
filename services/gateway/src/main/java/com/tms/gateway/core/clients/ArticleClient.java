package com.tms.gateway.core.clients;

import com.tms.gateway.core.constants.ApiConstants;
import com.tms.gateway.core.models.articles.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@FeignClient("news-service")
public interface ArticleClient {
    @GetMapping(ApiConstants.API_PREFIX_NEWS_V1 + ApiConstants.Article.API_ARTICLE_DETAIL_DELETE)
    ResponseEntity<ArticleDescriptionDto> getArticleDetail(@PathVariable(value = "id", required = true) UUID id);

    @PostMapping(ApiConstants.API_PREFIX_NEWS_V1 + ApiConstants.Article.API_ARTICLE_CREATE_UPDATE)
    ResponseEntity<ArticleDto> createArticle(@RequestBody ArticleCreateDto dto);

    @DeleteMapping(ApiConstants.API_PREFIX_NEWS_V1 + ApiConstants.Article.API_ARTICLE_DETAIL_DELETE)
    ResponseEntity<?> deleteArticleById(@PathVariable("id") UUID id);

    @GetMapping(ApiConstants.API_PREFIX_NEWS_V1 + ApiConstants.Article.API_ARTICLE_DETAIL_DELETE)
    ResponseEntity<ArticleDescriptionDto> getArticleById(@PathVariable("id") UUID id);

    @GetMapping(ApiConstants.API_PREFIX_NEWS_V1 + ApiConstants.Article.API_SHORT_ARTICLE_LIST_PAGE)
    ResponseEntity<Page<ArticleShortDto>> findShortAll(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam(name = "direction", required = false) String direction,
            @RequestParam(name = "field", required = false) String field
    );

    @PutMapping(ApiConstants.API_PREFIX_NEWS_V1 + ApiConstants.Article.API_ARTICLE_CREATE_UPDATE)
    ResponseEntity<ArticleDto> updateArticle(@RequestBody ArticleUpdateDto dto);


    @PostMapping(ApiConstants.API_PREFIX_NEWS_V1 + ApiConstants.Category.API_CATEGORY_CREATE_UPDATE)
    ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryCreateDto dto);

    @DeleteMapping(ApiConstants.API_PREFIX_NEWS_V1 + ApiConstants.Category.API_CATEGORY_DETAIL_DELETE)
    ResponseEntity<?> deleteCategoryById(@PathVariable("id") UUID id);

    @GetMapping(ApiConstants.API_PREFIX_NEWS_V1 + ApiConstants.Category.API_CATEGORY_DETAIL_DELETE)
    ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") UUID id);

    @GetMapping(ApiConstants.API_PREFIX_NEWS_V1 + ApiConstants.Category.API_CATEGORY_LIST)
    ResponseEntity<List<CategoryDto>> findAll();

    @PutMapping(ApiConstants.API_PREFIX_NEWS_V1 + ApiConstants.Category.API_CATEGORY_CREATE_UPDATE)
    ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryUpdateDto dto);

    @GetMapping(ApiConstants.API_PREFIX_NEWS_V1 + ApiConstants.Comment.API_COMMENT_GET_DELETE)
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("id") UUID id);

    @GetMapping(ApiConstants.API_PREFIX_NEWS_V1 +ApiConstants.Comment.API_ARTICLE_COMMET_LIST)
    ResponseEntity<List<CommentDto>> getByArticleId(@PathVariable("id") UUID id);

    @DeleteMapping(ApiConstants.API_PREFIX_NEWS_V1 +ApiConstants.Comment.API_COMMENT_GET_DELETE)
    ResponseEntity<?> deleteCommentById(@PathVariable("id") UUID id);

    @PostMapping(ApiConstants.API_PREFIX_NEWS_V1 + ApiConstants.Comment.API_COMMENT_CREATE_UPDATE)
    ResponseEntity<CommentDto> createComment(@RequestBody CommentCreateDto commentCreateDto);

    @PutMapping(ApiConstants.API_PREFIX_NEWS_V1 + ApiConstants.Comment.API_COMMENT_CREATE_UPDATE)
    ResponseEntity<CommentDto> updateComment(@RequestBody CommentUpdateDto commentUpdateDto);

    @GetMapping(ApiConstants.API_PREFIX_NEWS_V1 + ApiConstants.Portal.API_PORTAL_LIST)
    ResponseEntity<List<PortalDto>> getAll();

    @GetMapping(ApiConstants.API_PREFIX_NEWS_V1 + ApiConstants.Portal.API_PORTAL_GET_AND_DELETE)
    ResponseEntity<PortalDto> getPortalById(@PathVariable("id") UUID id);

    @DeleteMapping(ApiConstants.API_PREFIX_NEWS_V1 + ApiConstants.Portal.API_PORTAL_GET_AND_DELETE)
    ResponseEntity<?> deletePortalById(@PathVariable("id") UUID id);

    @PostMapping(ApiConstants.API_PREFIX_NEWS_V1 + ApiConstants.Portal.API_PORTAL_CREATE_UPDATE)
    public ResponseEntity<PortalDto> createPortal(@RequestBody PortalCreateDto portalDto);

    @PutMapping(ApiConstants.API_PREFIX_NEWS_V1 + ApiConstants.Portal.API_PORTAL_CREATE_UPDATE)
    ResponseEntity<PortalDto> updatePortal(@RequestBody PortalUpdateDto portalDto);
}
