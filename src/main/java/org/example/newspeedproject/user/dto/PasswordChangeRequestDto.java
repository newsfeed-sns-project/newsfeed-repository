package org.example.newspeedproject.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PasswordChangeRequestDto {
    private static final String PASSWORD_REGEX =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=]).{8,100}$";
    @NotBlank
    @Pattern(regexp = PASSWORD_REGEX, message = "비밀번호는 최소 8자 최대 100자로 설정해야됩니다.")
    private String oldpassword;


    @NotBlank
    @Pattern(regexp = PASSWORD_REGEX, message = "비밀번호는 최소 8자 최대 100자로 설정해야됩니다.")
    private String newpassword;
}
