package org.example.newspeedproject.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.newspeedproject.auth.dto.AuthRequestDto;
import org.example.newspeedproject.auth.dto.AuthResponseDto;
import org.example.newspeedproject.auth.service.AuthService;
import org.example.newspeedproject.auth.dto.LoginRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users") //auth인데 users로 하는게 맞을까...
public class AuthController {
    private final AuthService authService;

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDto> signup(@RequestBody @Valid AuthRequestDto request) {
        AuthResponseDto response = authService.signup(request);
        return ResponseEntity.ok(response);
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody @Valid LoginRequestDto authRequest, HttpServletRequest request) {
        AuthResponseDto id = authService.login(authRequest);

        HttpSession session = request.getSession();
        session.setAttribute("LOGIN_USER_ID", id.getId());
        return ResponseEntity.ok(id);
    }

    //로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }
}
