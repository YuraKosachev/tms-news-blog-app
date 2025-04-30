package com.tms.news.controllers;

import com.tms.news.core.constants.ApiConstants;
import com.tms.news.core.exceptions.ItemNotFoundException;
import com.tms.news.core.interfaces.services.ArticleService;
import com.tms.news.core.interfaces.services.CategoryService;
import com.tms.news.core.models.dtos.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.API_PREFIX_V1)
@Tag(name = "Articles")
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping(ApiConstants.Article.API_ARTICLE_CREATE_UPDATE)
    @Operation(description = "Article endpoint to create article",
            summary = "This is a summary for article post endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ArticleDto.class))})
    @ResponseBody
    public ResponseEntity<ArticleDto> createCategory(@RequestBody @Valid ArticleCreateDto dto){
        return ResponseEntity.ok(articleService.createArticle(dto));
    }

    @DeleteMapping(ApiConstants.Article.API_ARTICLE_DETAIL_DELETE)
    @Operation(description = "Article endpoint to create article",
            summary = "This is a summary for article delete endpoint")
    @ApiResponse(description = "Success", responseCode = "200")
    public ResponseEntity<?> deleteById(@PathVariable("id") UUID id){
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(ApiConstants.Article.API_ARTICLE_DETAIL_DELETE)
    @Operation(description = "Article endpoint to get item",
            summary = "This is a summary for article get endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = ArticleDescriptionDto.class))})
    @ResponseBody
    public ResponseEntity<ArticleDescriptionDto> getById(@PathVariable("id") UUID id){
        return ResponseEntity.ok(articleService.getArticleById(id).orElseThrow(()-> new ItemNotFoundException("article not found")));//("category not found")));
    }

    @GetMapping(ApiConstants.Article.API_SHORT_ARTICLE_LIST_PAGE)
    @Operation(description = "Article endpoint to get short articles",
            summary = "This is a summary for article get all short description endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Page.class))})
    @ResponseBody
    public ResponseEntity<Page<ArticleShortDto>> findShortAll(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam(name = "direction", required = false) String direction,
            @RequestParam(name = "field", required = false) String field
            ){

        Pageable pageable = PageRequest.of(page, size);
        if(!(direction == null || field == null)){
            Sort sort = Sort.by(Sort.Direction.fromString(direction), field);
            pageable = PageRequest.of(page, size).withSort(sort);
        }
        return ResponseEntity.ok(articleService.getAllShortArticles(pageable));
    }

    @PutMapping(ApiConstants.Article.API_ARTICLE_CREATE_UPDATE)
    @Operation(description = "Article endpoint to update article",
            summary = "This is a summary for article put endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ArticleDto.class))})
    @ResponseBody
    public ResponseEntity<ArticleDto> update(@RequestBody @Valid ArticleUpdateDto dto){
        return ResponseEntity.ok(articleService.updateArticle(dto));
    }
}
