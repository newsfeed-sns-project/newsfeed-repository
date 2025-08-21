package org.example.newspeedproject.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProfileChangeRequestDto {
    @NotBlank
    @Size(min = 1, max = 20, message = "이름은 최소 1자 최대 20자로 설정해야됩니다.")
    private String username;

    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    @NotBlank
    @Pattern(regexp = EMAIL_REGEX, message = "올바른 이메일 형식이 아닙니다.")
    @Email(message = "이메일 형식이 아닙니다.")
    @Size(min = 1, max = 30, message = "이메일은 최소 1자 최대 30자로 설정해야됩니다.")
    private String email;
}
