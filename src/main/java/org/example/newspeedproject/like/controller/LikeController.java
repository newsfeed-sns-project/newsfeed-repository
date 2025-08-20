package org.example.newspeedproject.like.controller;

import lombok.RequiredArgsConstructor;
import org.example.newspeedproject.like.dto.LikeResponse;
import org.example.newspeedproject.like.service.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.example.newspeedproject.post.consts.Const.LOGIN_USER;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{postId}/likes")
    public ResponseEntity<LikeResponse> addLike(
            @PathVariable Long postId,
            @SessionAttribute(name = LOGIN_USER) Long userId) {

        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 해주세요.");
        }
        LikeResponse response = likeService.addLike(postId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/likes/{likeId}")
    public ResponseEntity<LikeResponse> deleteLike(
            @PathVariable Long likeId,
            @SessionAttribute(name = LOGIN_USER) Long userId) {

        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 해주세요.");
        }

        LikeResponse response = likeService.deleteLike(likeId, userId);
        return ResponseEntity.ok(response);
    }
}
