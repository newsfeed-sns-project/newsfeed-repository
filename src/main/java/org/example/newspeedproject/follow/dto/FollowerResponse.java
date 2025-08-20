package org.example.newspeedproject.follow.dto;

import lombok.Getter;
import org.example.newspeedproject.user.entity.User;

@Getter
public class FollowerResponse {
    private final Long followerId;
    private final String followerUsername;
    private final String followerEmail;

    public FollowerResponse(User follower) {
        this.followerId = follower.getId();
        this.followerUsername = follower.getUsername();
        this.followerEmail = follower.getEmail();
    }
}
