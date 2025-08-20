package org.example.newspeedproject.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequestDto {
    @NotBlank
    @Email(message = "이메일 형식이 아닙니다.")
    @Size(min = 1, max = 30, message = "이메일은 최소 1자 최대 30자로 설정해야됩니다.")
    private String email;

    @NotBlank
    @Size(min = 1, max = 100, message = "비밀번호는 최소 1자 최대 100자로 설정해야됩니다.")
    private String password;
}
