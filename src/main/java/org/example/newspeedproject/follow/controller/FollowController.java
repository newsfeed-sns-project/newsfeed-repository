package org.example.newspeedproject.follow.controller;

import lombok.RequiredArgsConstructor;
import org.example.newspeedproject.follow.dto.FollowerResponse;
import org.example.newspeedproject.follow.dto.FollowingResponse;
import org.example.newspeedproject.follow.service.FollowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    // 이웃 추가 (POST)
    @PostMapping("/{userTargetId}/follows")
    public ResponseEntity<Void> addFollow(
            @PathVariable Long userTargetId,
            @SessionAttribute("LOGIN_USER_ID") Long myUserId
    ) {
        followService.addFollow(myUserId, userTargetId);
        return ResponseEntity.noContent().build();
    }

    //  팔로잉 조회 (내가 팔로우하는 사람들)
    @GetMapping("/me/followings")
    public ResponseEntity<List<FollowingResponse>> getFollowing(
            @SessionAttribute("LOGIN_USER_ID") Long myUserId
    ) {
        List<FollowingResponse> followings = followService.getFollowings(myUserId);
        return ResponseEntity.ok(followings);
    }

    //  팔로워 조회 (나를 팔로우하는 사람들)
    @GetMapping("/me/followers")
    public ResponseEntity<List<FollowerResponse>> getMyFollowers(
            @SessionAttribute("LOGIN_USER_ID") Long myUserId
    ) {
        List<FollowerResponse> followers = followService.getFollowers(myUserId);
        return ResponseEntity.ok(followers);
    }

    //  언팔로우 (내가 팔로우한 사람 해제)
    @DeleteMapping("/{userToUnfollowId}/follows")
    public ResponseEntity<Void> unfollow(
            @PathVariable Long userToUnfollowId,
            @SessionAttribute("LOGIN_USER_ID") Long myUserId
    ) {
        followService.unfollow(myUserId, userToUnfollowId);
        return ResponseEntity.noContent().build();
    }
}
