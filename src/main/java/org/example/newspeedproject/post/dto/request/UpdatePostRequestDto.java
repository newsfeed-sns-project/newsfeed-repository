package org.example.newspeedproject.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdatePostRequestDto {

    @NotBlank
    @Size(min = 1, max = 50)
    private final String title;

    @NotBlank
    @Size(max = 100)
    private final String contents;

    public UpdatePostRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
