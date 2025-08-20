package org.example.newspeedproject.post.dto.reponse;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {

    private final Long id;
    private final String title;
    private final String contents;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;
    private final Long userId;

    public PostResponseDto(Long id, String title, String contents, LocalDateTime createdDate, LocalDateTime modifiedDate, Long userId) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.userId = userId;
    }

}
