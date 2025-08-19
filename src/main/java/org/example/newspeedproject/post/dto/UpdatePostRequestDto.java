package org.example.newspeedproject.dto;

import lombok.Getter;

@Getter
public class UpdatePostRequestDto {

    private final String title;
    private final String contents;

    public UpdatePostRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
