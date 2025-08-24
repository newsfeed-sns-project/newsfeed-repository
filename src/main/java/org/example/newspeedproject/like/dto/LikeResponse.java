package org.example.newspeedproject.like.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LikeResponse {
    private Long likeId;
    private Long postId;
    private Long userId;
    private String message;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public LikeResponse(Long likeId, Long postId, Long userId, String message, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.likeId = likeId;
        this.postId = postId;
        this.userId = userId;
        this.message = message;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
