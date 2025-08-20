package org.example.newspeedproject.follow.repository;

import org.example.newspeedproject.follow.entity.Follow;
import org.example.newspeedproject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface FollowRepository extends JpaRepository<Follow, Long> {
    boolean existsByFollowerAndFollowing(User myUser, User userToFollow);
}
