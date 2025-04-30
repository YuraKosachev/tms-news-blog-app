package com.tms.gateway.controllers;
//import com.tms.gateway.core.clients.ArticleClient;
import com.tms.gateway.core.clients.ArticleClient;
import com.tms.gateway.core.constants.ApiConstants;
import com.tms.gateway.core.models.articles.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.API_PREFIX_NEWS_GATEWAY)
@Tag(name = "Articles")
public class ArticleController {

    private final ArticleClient articleClient;

    @PostMapping(ApiConstants.Article.API_ARTICLE_CREATE_UPDATE)
    @Operation(description = "Article endpoint to create article",
            summary = "This is a summary for article post endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ArticleDto.class))})
    @ResponseBody
    public ResponseEntity<ArticleDto> createCategory(@RequestBody ArticleCreateDto dto){
        return articleClient.createArticle(dto);
    }

    @DeleteMapping(ApiConstants.Article.API_ARTICLE_DETAIL_DELETE)
    @Operation(description = "Article endpoint to create article",
            summary = "This is a summary for article delete endpoint")
    @ApiResponse(description = "Success", responseCode = "200")
    public ResponseEntity<?> deleteById(@PathVariable("id") UUID id){
        articleClient.deleteArticleById(id);
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
        return articleClient.getArticleDetail(id);
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
        return articleClient.findShortAll(page, size, direction, field);
    }

    @PutMapping(ApiConstants.Article.API_ARTICLE_CREATE_UPDATE)
    @Operation(description = "Article endpoint to update article",
            summary = "This is a summary for article put endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ArticleDto.class))})
    @ResponseBody
    public ResponseEntity<ArticleDto> update(@RequestBody ArticleUpdateDto dto){
        return articleClient.updateArticle(dto);
    }

}
