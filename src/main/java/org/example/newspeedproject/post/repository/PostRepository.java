package org.example.newspeedproject.post.repository;

import org.example.newspeedproject.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    Page<Post> findByCreatedDateBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);

}
