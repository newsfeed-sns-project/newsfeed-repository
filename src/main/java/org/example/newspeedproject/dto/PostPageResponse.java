package org.example.newspeedproject.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PostPageResponse {

    private final List<PostResponseDto> posts;
    private final int number;
    private final int totalPages;
    private final long totalElements;

    public PostPageResponse(List<PostResponseDto> posts, int number, int totalPages, long totalElements) {
        this.posts = posts;
        this.number = number;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }
}
