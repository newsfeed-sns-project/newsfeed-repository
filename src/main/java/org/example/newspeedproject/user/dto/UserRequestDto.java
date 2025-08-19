package org.example.newspeedproject.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserRequestDto {
    @Size(min = 1, max = 4, message = "이름은 최소 1자 최대 4자로 설정해야됩니다.")
    private String username;

    @NotBlank(message = "필수 입력값입니다.")
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "필수 입력값입니다.")
    @Size(min = 1, max = 100, message = "비밀번호는 최소 1자 최대 100자로 설정해야됩니다.")
    private String password;
}
