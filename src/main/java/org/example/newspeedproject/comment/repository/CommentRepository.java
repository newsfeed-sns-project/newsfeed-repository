package org.example.newspeedproject.comment.repository;

import org.example.newspeedproject.comment.entity.Comment;
import org.example.newspeedproject.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    public List<Comment> findByPost(Long postsId);
}
