package org.example.newspeedproject.follow.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.newspeedproject.user.entity.User;

@Entity
@Getter
@NoArgsConstructor
public class Follow {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "followingId")
    private User following; //팔로우 당하는 사람

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "followerId")
    private User follower; //팔로우 하는 사람



    public Follow(User following, User follower) {
        this.following = following;
        this.follower = follower;
    }



}
