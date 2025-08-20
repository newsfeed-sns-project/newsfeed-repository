package org.example.newspeedproject.like.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LikeResponse {
    private Long likeId;
    private Long postId;
    private Long userId;
    private String message;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public LikeResponse(Long likeId, Long postId, Long userId, String message, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.likeId = likeId;
        this.postId = postId;
        this.userId = userId;
        this.message = message;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
