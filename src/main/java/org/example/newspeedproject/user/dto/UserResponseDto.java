package org.example.newspeedproject.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.newspeedproject.user.entity.User;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
    private LocalDateTime createdDateAt;
    private LocalDateTime modifiedDateAt;

    public UserResponseDto(Long id, String username, String email, LocalDateTime createdDateAt, LocalDateTime modifiedDateAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.createdDateAt = createdDateAt;
        this.modifiedDateAt = modifiedDateAt;
    }

    public UserResponseDto(Long id) {
        this.id = id;
    }
}
