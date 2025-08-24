package org.example.newspeedproject.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.newspeedproject.auth.dto.LoginRequestDto;
import org.example.newspeedproject.commo.consts.Const;
import org.example.newspeedproject.user.dto.*;
import org.example.newspeedproject.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    //비밀번호 수정
    @PatchMapping("/me/password")
    public ResponseEntity<String> passwordChange(@RequestBody @Valid PasswordChangeRequestDto userRequest, HttpServletRequest request, @SessionAttribute(name = Const.LOGIN_USER) Long id) {
        userService.passwordChange(id, userRequest);
        return ResponseEntity.ok("비밀번호를 수정하였습니다.");
    }

    //프로필 수정
    @PatchMapping("/me/profile")
    public ResponseEntity<UserResponseDto> updateProfile(@RequestBody @Valid ProfileChangeRequestDto userRequest, HttpServletRequest request, @SessionAttribute(name = Const.LOGIN_USER) Long id) {
        UserResponseDto response = userService.updateUserProfile(id, userRequest);
        return ResponseEntity.ok(response);
    }

    //프로필 조회
    @GetMapping("/me/profile")
    public ResponseEntity<UserResponseDto> getProfile(HttpServletRequest request, @SessionAttribute(name = Const.LOGIN_USER) Long id) {
        UserResponseDto response = userService.findUserId(id);
        return ResponseEntity.ok(response);
    }

    //회원 탈퇴
    @DeleteMapping("/me")
    public ResponseEntity<String> deleteUser(@RequestBody @Valid LoginRequestDto userRequest, HttpServletRequest request, @SessionAttribute(name = Const.LOGIN_USER) Long id) {
        userService.deleteUser(id, userRequest);
        request.getSession().invalidate();
        return ResponseEntity.ok("회원 탈퇴 성공");
    }
}
