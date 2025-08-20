package org.example.newspeedproject.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.newspeedproject.user.dto.*;
import org.example.newspeedproject.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@RequestBody @Valid UserRequestDto request) {
        UserResponseDto response =  userService.signup(request);
        return ResponseEntity.ok(response);
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody @Valid LoginRequestDto userRequest, HttpServletRequest request){
        UserResponseDto id = userService.login(userRequest);

        HttpSession session = request.getSession();
        session.setAttribute("LOGIN_USER_ID", id.getId());
        return ResponseEntity.ok(id);
    }

    //로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }

    //비밀번호 수정
    @PatchMapping("/me/password")
    public ResponseEntity<String> passwordChange(@RequestBody @Valid PasswordChangeRequestDto userRequest, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("LOGIN_USER_ID") == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "로그인 필요.");
        }

        Long id = (Long) session.getAttribute("LOGIN_USER_ID");
        userService.passwordChange(id,userRequest);
        return ResponseEntity.ok("비밀번호를 수정하였습니다.");
    }

    //프로필 수정
    @PatchMapping("/me/profile")
    public ResponseEntity<UserResponseDto> updateProfile(@RequestBody @Valid ProfileChangeRequestDto userRequest, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("LOGIN_USER_ID") == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "로그인 필요.");
        }
        Long id = (Long) session.getAttribute("LOGIN_USER_ID");
        UserResponseDto response = userService.updateUserProfile(id,userRequest);
        return ResponseEntity.ok(response);
    }

    //프로필 조회
    @GetMapping("/me/profile")
    public ResponseEntity<UserResponseDto> getProfile(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("LOGIN_USER_ID") == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "로그인 필요.");
        }
        Long id = (Long) session.getAttribute("LOGIN_USER_ID");
        UserResponseDto response = userService.findUserId(id);
        return ResponseEntity.ok(response);
    }

    //회원 탈퇴
    @DeleteMapping("/me")
    public ResponseEntity<String> deleteUser(@RequestBody @Valid LoginRequestDto userRequest,HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("LOGIN_USER_ID") == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "로그인 필요.");
        }
        Long id = (Long) session.getAttribute("LOGIN_USER_ID");
        userService.deleteUser(id,userRequest);
        session.invalidate();
        return ResponseEntity.ok("회원 탈퇴 성공");
    }
}
