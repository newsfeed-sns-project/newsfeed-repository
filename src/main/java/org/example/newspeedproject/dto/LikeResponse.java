package org.example.newspeedproject.dto;

import lombok.Getter;
import org.example.newspeedproject.entity.Like;

@Getter
public class LikeResponse {
    private Like likeId;
    private Long postId;
    private Long userId;
    private String message;
}
