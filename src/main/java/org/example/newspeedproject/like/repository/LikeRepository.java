package org.example.newspeedproject.like.repository;

import org.example.newspeedproject.like.entity.Like;
import org.example.newspeedproject.post.entity.Post;
import org.example.newspeedproject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByPostAndUser(Post post, User user);
}
