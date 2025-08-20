package org.example.newspeedproject.follow.service;

import lombok.RequiredArgsConstructor;
import org.example.newspeedproject.follow.entity.Follow;
import org.example.newspeedproject.follow.repository.FollowRepository;
import org.example.newspeedproject.user.entity.User;
import org.example.newspeedproject.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;

// 이웃 추가
    @Transactional
    public void addFollow(Long myUserId, Long userTargetId) {
        if (myUserId.equals(userTargetId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "자기 자신을 팔로우할 수 없습니다.");
        }
        //현재 로그인한 나를 찾는 것
        User myUser = userRepository.findById(myUserId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다.")
        );
        // 내가 팔로워 하려는 대상을 찾는 것
        User userToFollow = userRepository.findById(userTargetId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "팔로우할 유저를 찾을 수 없습니다.")
        );

        if (followRepository.existsByFollowerAndFollowing(myUser, userToFollow)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 팔로우한 유저입니다.");
        }

        Follow follow = new Follow(myUser, userToFollow);
        followRepository.save(follow);
    }
}
