package org.example.newspeedproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.newspeedproject.dto.LikeRequest;
import org.example.newspeedproject.dto.LikeResponse;
import org.example.newspeedproject.service.LikeService;
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
