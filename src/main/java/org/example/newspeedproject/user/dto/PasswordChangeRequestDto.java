package org.example.newspeedproject.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordChangeRequestDto {
    @NotBlank
    @Size(min = 1, max = 100, message = "비밀번호는 최소 1자 최대 100자입니다.")
    private String oldpassword;

    @NotBlank
    @Size(min = 1, max = 100, message = "비밀번호는 최소 1자 최대 100자입니다.")
    private String newpassword;
}
