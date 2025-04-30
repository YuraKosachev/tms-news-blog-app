package com.tms.gateway.controllers;

import com.tms.gateway.core.clients.ArticleClient;
import com.tms.gateway.core.constants.ApiConstants;
import com.tms.gateway.core.models.articles.CommentCreateDto;
import com.tms.gateway.core.models.articles.CommentDto;
import com.tms.gateway.core.models.articles.CommentUpdateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ApiConstants.API_PREFIX_NEWS_GATEWAY)
@Tag(name="Comments")
@RequiredArgsConstructor
public class CommentController {
    private final ArticleClient articleClient;

    @GetMapping(ApiConstants.Comment.API_COMMENT_GET_DELETE)
    @Operation(description = "Comment endpoint to get item",
            summary = "This is a summary for comment get item endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CommentDto.class))})
    @ResponseBody
    public ResponseEntity<CommentDto> getById(@PathVariable("id") UUID id) {
        return articleClient.getCommentById(id);
    }

    @GetMapping(ApiConstants.Comment.API_ARTICLE_COMMET_LIST)
    @Operation(description = "Comment endpoint to get all by article id",
            summary = "This is a summary for comment get all endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CommentDto[].class))})
    @ResponseBody
    public ResponseEntity<List<CommentDto>> getByArticleId(@PathVariable("id") UUID id) {
        return articleClient.getByArticleId(id);
    }

    @DeleteMapping(ApiConstants.Comment.API_COMMENT_GET_DELETE)
    @Operation(description = "Comment endpoint to delete",
            summary = "This is a summary for comment delete endpoint")
    @ApiResponse(description = "Success", responseCode = "200")
    public ResponseEntity<?> deleteById(@PathVariable("id") UUID id) {
        articleClient.deleteCommentById(id);
        return ResponseEntity.ok().build();
    }


    @PostMapping(ApiConstants.Comment.API_COMMENT_CREATE_UPDATE)
    @Operation(description = "Comment endpoint to create portal",
            summary = "This is a summary for comment create endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CommentDto.class))})
    @ResponseBody
    public ResponseEntity<CommentDto> create(@RequestBody CommentCreateDto commentCreateDto) {
        return articleClient.createComment(commentCreateDto);
    }

    @PutMapping(ApiConstants.Comment.API_COMMENT_CREATE_UPDATE)
    @Operation(description = "Comment endpoint to update portal",
            summary = "This is a summary for Comment update endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CommentDto.class))})
    @ResponseBody
    public ResponseEntity<CommentDto> update(@RequestBody CommentUpdateDto commentUpdateDto) {
        return articleClient.updateComment(commentUpdateDto);
    }
}
