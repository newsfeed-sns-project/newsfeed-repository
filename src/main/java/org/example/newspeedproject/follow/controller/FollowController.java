package org.example.newspeedproject.follow.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.newspeedproject.follow.dto.FollowerResponse;
import org.example.newspeedproject.follow.dto.FollowingResponse;
import org.example.newspeedproject.follow.service.FollowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;

    // 이웃 추가(POST)
    @PostMapping("/{userTargetId}/follows")
    public ResponseEntity<Void> addFollow(@PathVariable Long userTargetId, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("LOGIN_USER_ID") == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "로그인 필요이 필요합니다.");
        }
        Long myUserId = (Long) session.getAttribute("LOGIN_USER_ID");
        followService.addFollow(myUserId, userTargetId);
        return ResponseEntity.noContent().build();
    }
// t 팔로잉 조회(로그인한 유저의 팔로잉 목록을 조회하는 기능)
    @GetMapping("/me/followings")
    public ResponseEntity<List<FollowingResponse>> getFollowing(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("LOGIN_USER_ID") == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "로그인 필요이 필요합니다.");
        }
        Long myUserId = (Long) session.getAttribute("LOGIN_USER_ID");
        List<FollowingResponse> followings = followService.getFollowings(myUserId);
        return ResponseEntity.ok().body(followings);
    }

    // t 팔로워 조회(로그인한 유저를 팔로우 하고 있는 사람들 목록)
    @GetMapping("/me/followers")
    public ResponseEntity<List<FollowerResponse>> getMyFollowers(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("LOGIN_USER_ID") == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다.");
        }
        Long myUserId = (Long) session.getAttribute("LOGIN_USER_ID");
        List<FollowerResponse> followers = followService.getFollowers(myUserId);
        return ResponseEntity.ok(followers);
    }


    // 언팔로우 (내가 팔로우하고 있는 사람 삭제)
    @DeleteMapping("/{userToUnfollowId}/follows")
    public ResponseEntity<Void> unfollow(@PathVariable Long userToUnfollowId, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("LOGIN_USER_ID") == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다.");
        }
        Long myUserId = (Long) session.getAttribute("LOGIN_USER_ID");
        followService.unfollow(myUserId, userToUnfollowId);
        return ResponseEntity.noContent().build();
    }
}
