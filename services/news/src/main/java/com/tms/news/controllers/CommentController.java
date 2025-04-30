package com.tms.news.controllers;

import com.tms.news.core.constants.ApiConstants;
import com.tms.news.core.exceptions.ItemNotFoundException;
import com.tms.news.core.interfaces.services.CommentService;
import com.tms.news.core.models.dtos.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ApiConstants.API_PREFIX_V1)
@Tag(name="Comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping(ApiConstants.Comment.API_COMMENT_GET_DELETE)
    @Operation(description = "Comment endpoint to get item",
            summary = "This is a summary for comment get item endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CommentDto.class))})
    @ResponseBody
    public ResponseEntity<CommentDto> getById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(commentService.getById(id).orElseThrow(()-> new ItemNotFoundException("comment not found")));
    }

    @GetMapping(ApiConstants.Comment.API_ARTICLE_COMMET_LIST)
    @Operation(description = "Comment endpoint to get all by article id",
            summary = "This is a summary for comment get all endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CommentDto[].class))})
    @ResponseBody
    public ResponseEntity<List<CommentDto>> getByArticleId(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(commentService.getByArticleId(id));
    }

    @DeleteMapping(ApiConstants.Comment.API_COMMENT_GET_DELETE)
    @Operation(description = "Comment endpoint to delete",
            summary = "This is a summary for comment delete endpoint")
    @ApiResponse(description = "Success", responseCode = "200")
    public ResponseEntity<?> deleteById(@PathVariable("id") UUID id) {
        commentService.deleteById(id);
        return ResponseEntity.ok().build();
    }


    @PostMapping(ApiConstants.Comment.API_COMMENT_CREATE_UPDATE)
    @Operation(description = "Comment endpoint to create portal",
            summary = "This is a summary for comment create endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CommentDto.class))})
    @ResponseBody
    public ResponseEntity<CommentDto> create(@RequestBody @Valid CommentCreateDto commentCreateDto) {
        return ResponseEntity.ok(commentService.create(commentCreateDto));
    }

    @PutMapping(ApiConstants.Comment.API_COMMENT_CREATE_UPDATE)
    @Operation(description = "Comment endpoint to update portal",
            summary = "This is a summary for Comment update endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CommentDto.class))})
    @ResponseBody
    public ResponseEntity<CommentDto> update(@RequestBody @Valid CommentUpdateDto commentUpdateDto) {
        return ResponseEntity.ok(commentService.update(commentUpdateDto));
    }
}
