package org.example.newspeedproject.post.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PostPageResponseDto {

    private final List<PostResponseDto> posts;
    private final int pageNumber;
    private final int totalPages;
    private final long totalElements;

    public PostPageResponseDto(List<PostResponseDto> posts, int pageNumber, int totalPages, long totalElements) {
        this.posts = posts;
        this.pageNumber = pageNumber;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }
}
