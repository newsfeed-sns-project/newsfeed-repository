package org.example.newspeedproject.comment.repository;

import org.example.newspeedproject.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
