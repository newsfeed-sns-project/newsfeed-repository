package org.example.newspeedproject.follow.controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.newspeedproject.follow.service.FollowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;

    // 이웃 추가(POST)
    @PostMapping("/{userTargetId}/follows")
    public ResponseEntity<Void> addFollow(@PathVariable Long userTargetId, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("LOGIN_USER_ID") == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "로그인 필요이 필요합니다.");
        }
        Long myUserId = (Long) session.getAttribute("LOGIN_USER_ID");
        followService.addFollow(myUserId, userTargetId);
        return ResponseEntity.noContent().build();
    }

    //
}
