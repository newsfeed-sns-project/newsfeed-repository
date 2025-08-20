package org.example.newspeedproject.like.controller;

import lombok.RequiredArgsConstructor;
import org.example.newspeedproject.like.dto.LikeRequest;
import org.example.newspeedproject.like.dto.LikeResponse;
import org.example.newspeedproject.like.service.LikeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{postId}/likes")
    public LikeResponse addLike(
            @RequestBody LikeRequest request) {
        return likeService.addLike(request);
    }

    @DeleteMapping("/likes/{likeId}")
    public LikeResponse deleteLike(
            @PathVariable Long likeId) {
        return likeService.deleteLike(likeId);
    }
}
