package org.example.newspeedproject.follow.dto;

import lombok.Getter;
import org.example.newspeedproject.user.entity.User;

@Getter
public class FollowingResponse {
    private Long followingId;
    private String followingUsername;
    private String followingEmail;

    public FollowingResponse(User following) {
        this.followingId = following.getId();
        this.followingUsername = following.getUsername();
        this.followingEmail = following.getEmail();
    }
}
