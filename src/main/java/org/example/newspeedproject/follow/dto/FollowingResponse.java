package org.example.newspeedproject.follow.dto;

import lombok.Getter;
import org.example.newspeedproject.user.entity.User;

@Getter
public class FollowingResponse {
    private final Long followingId;
    private final String followingUsername;
    private final String followingEmail;

    public FollowingResponse(User following) {
        this.followingId = following.getId();
        this.followingUsername = following.getUsername();
        this.followingEmail = following.getEmail();
    }
}
