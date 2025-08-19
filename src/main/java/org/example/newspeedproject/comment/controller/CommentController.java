package org.example.newspeedproject.comment.controller;

import lombok.RequiredArgsConstructor;
import org.example.newspeedproject.comment.dto.CommentRequestDto;
import org.example.newspeedproject.comment.dto.CommentResponseDto;
import org.example.newspeedproject.comment.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/posts/{postsid}/comments")
    public ResponseEntity<CommentResponseDto> save(
            @SessionAttribute(name = "LOGIN_USER") Long userId,
            @PathVariable Long postsid,
            @RequestBody CommentRequestDto dto
    ) {
        return ResponseEntity.ok(commentService.save(userId, postsid, dto));
    }


}
