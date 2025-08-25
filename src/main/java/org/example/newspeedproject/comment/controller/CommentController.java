package org.example.newspeedproject.comment.controller;

import lombok.RequiredArgsConstructor;
import org.example.newspeedproject.comment.dto.CommentRequestDto;
import org.example.newspeedproject.comment.dto.CommentResponseDto;
import org.example.newspeedproject.comment.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/posts/{postsId}/comments")
    public ResponseEntity<CommentResponseDto> save(
            @SessionAttribute(name = "LOGIN_USER_ID") Long userId,
            @PathVariable Long postsId,
            @RequestBody CommentRequestDto dto
    ) {
        return ResponseEntity.ok(commentService.save(userId, postsId, dto));
    }

    @GetMapping("/posts/comments/{id}")
    public ResponseEntity<CommentResponseDto> findOne(
            @PathVariable Long id,
            @SessionAttribute(name = "LOGIN_USER_ID") Long userId) throws AccessDeniedException {
        return ResponseEntity.ok(commentService.findOne(id, userId));
    }

    @GetMapping("/posts/{postsId}/comments")
    public ResponseEntity<List<CommentResponseDto>> findByPost(@PathVariable Long postsId) {
        return ResponseEntity.ok(commentService.findByPost(postsId));
    }

    @PutMapping("/posts/comments/{id}")
    public ResponseEntity<CommentResponseDto> update(
            @SessionAttribute(name = "LOGIN_USER_ID") Long userId,
            @PathVariable Long id,
            @RequestBody CommentRequestDto dto) throws AccessDeniedException {
        return ResponseEntity.ok(commentService.update(id, userId, dto));
    }

    @DeleteMapping("/posts/comments/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            @SessionAttribute(name = "LOGIN_USER_ID") Long userId) throws AccessDeniedException {
        commentService.delete(id, userId);
        return ResponseEntity.noContent().build();
    }
}