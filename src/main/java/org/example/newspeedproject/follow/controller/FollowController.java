package org.example.newspeedproject.follow.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
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
    // t 팔로잉 조회(userTargetId = 123번 유저가 팔로우 하고 있는 사람들 목록)
   /* @GetMapping("/{userTargetId}/followings")
    public ResponseEntity<List<FollowingResponse>>  getFollowings(@PathVariable Long userTargetId,HttpServletRequest request){
        //T 팔로잉 조회를 할때 로그인을 한 세션이 필요할까?
        /*T 나(로그인한)를 팔로워하는 사람을 찾는게 맞는걸까?
          T 데이터 베이스에 포함된 유저들을 모두 조회할수 있는 기능을 만들어야하는걸까
    */



    // t 팔로워 조회(userTargetId = 123번 유저를 팔로우 하고 있는 사람들 목록)



    //팔로우 삭제


}
