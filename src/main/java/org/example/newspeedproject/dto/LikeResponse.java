package org.example.newspeedproject.dto;

import lombok.Getter;
import org.example.newspeedproject.entity.Like;

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
