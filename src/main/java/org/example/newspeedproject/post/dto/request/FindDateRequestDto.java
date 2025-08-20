package org.example.newspeedproject.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class FindDateRequestDto {

    private final LocalDate start;
    private final LocalDate end;

    public FindDateRequestDto(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }
}
