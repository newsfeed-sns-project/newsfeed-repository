package org.example.newspeedproject.like.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LikeRequest {
    private Long postId;
    private Long userId;

    public LikeRequest(Long postId, Long userId) {
        this.postId = postId;
        this.userId = userId;
    }
}
