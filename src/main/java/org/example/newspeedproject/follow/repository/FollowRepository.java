package org.example.newspeedproject.follow.repository;

import org.example.newspeedproject.follow.entity.Follow;
import org.example.newspeedproject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FollowRepository extends JpaRepository<Follow, Long> {
    boolean existsByFollowerAndFollowing(User myUser, User userToFollow);
    List<Follow> findAllByFollower(User myUser);
    List<Follow> findAllByFollowing(User user);
}
