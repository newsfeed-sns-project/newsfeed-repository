package org.example.newspeedproject.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class AuthResponseDto {
    private Long id;
    private String username;
    private String email;
    private LocalDateTime createdDateAt;
    private LocalDateTime modifiedDateAt;

    public AuthResponseDto(Long id, String username, String email, LocalDateTime createdDateAt, LocalDateTime modifiedDateAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.createdDateAt = createdDateAt;
        this.modifiedDateAt = modifiedDateAt;
    }
}
