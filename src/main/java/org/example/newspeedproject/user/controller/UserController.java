package org.example.newspeedproject.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.newspeedproject.user.dto.LoginRequestDto;
import org.example.newspeedproject.user.dto.UserRequestDto;
import org.example.newspeedproject.user.dto.UserResponseDto;
import org.example.newspeedproject.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    //회원가입
    @PostMapping("/signup")
    public String signup(@RequestBody @Valid UserRequestDto request) {
        userService.signup(request);
        return "회원가입 성공";
    }

    //로그인
    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginRequestDto userRequest, HttpServletRequest request){
        UserResponseDto id = userService.login(userRequest);

        HttpSession session = request.getSession();
        session.setAttribute("LOGIN_USER_ID", id.getId());
        return "로그인 성공";
    }

    //로그아웃
    @PostMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return "로그아웃 성공";
    }

    //비밀번호 수정 @PatchMapping("/me/password")

    //프로필 수정
    @PatchMapping("/me/profile")
    public String updateProfile(@RequestBody @Valid UserRequestDto userRequest, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("LOGIN_USER_ID") == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "로그인 필요.");
        }
        Long id = (Long) session.getAttribute("LOGIN_USER_ID");
        userService.updateUserProfile(id,userRequest);
        return "프로필 수정 성공";
    }

    //프로필 조회
    @GetMapping("/me/profile")
    public UserResponseDto getProfile(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("LOGIN_USER_ID") == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "로그인 필요.");
        }
        Long id = (Long) session.getAttribute("LOGIN_USER_ID");
        return userService.findUserId(id);
    }

    //회원 탈퇴
    @DeleteMapping("/me")
    public String deleteUser(@RequestBody @Valid LoginRequestDto userRequest,HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("LOGIN_USER_ID") == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "로그인 필요.");
        }
        Long id = (Long) session.getAttribute("LOGIN_USER_ID");
        userService.deleteUser(id,userRequest);
        session.invalidate();
        return "회원 탈퇴 성공";
    }
}
