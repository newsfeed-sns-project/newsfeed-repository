package org.example.newspeedproject.like.dto;

import lombok.Getter;

@Getter
public class LikeResponse {
    private Long likeId;
    private Long postId;
    private Long userId;
    private String message;

    public LikeResponse(Long likeId, Long postId, Long userId, String message) {
        this.likeId = likeId;
        this.postId = postId;
        this.userId = userId;
        this.message = message;
    }
}
