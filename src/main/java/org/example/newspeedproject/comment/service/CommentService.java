package org.example.newspeedproject.comment.service;


import lombok.RequiredArgsConstructor;
import org.example.newspeedproject.comment.dto.CommentRequestDto;
import org.example.newspeedproject.comment.dto.CommentResponseDto;
import org.example.newspeedproject.comment.entity.Comment;
import org.example.newspeedproject.comment.repository.CommentRepository;
import org.example.newspeedproject.post.Post;
import org.example.newspeedproject.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private CommentRepository commentRepository;

    @Transactional
    public CommentResponseDto save(Long userId, Long postId, CommentRequestDto commentRequestDto) {

        User user = new User(); //팀원 지원 필요
        Post post = new Post(); //팀원 지원 필요

        Comment comment = new Comment(commentRequestDto.getComment(), user, post);
        commentRepository.save(comment);

        CommentResponseDto commentResponseDto = new CommentResponseDto(comment.getComment());
        return commentResponseDto;
    }

    @Transactional(readOnly = true)
    public CommentResponseDto findone(Long id) {
        Comment comment = commentRepository
                .findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("comment not found"));
        return new CommentResponseDto(comment.getComment());
    }

    @Transactional(readOnly = true)
    public List<CommentResponseDto> findByPost(Long postId) {
        List<Comment> comments = commentRepository.findByPost(postId);
        return comments.stream()
                .map(comment -> new CommentResponseDto(
                        comment.getComment()))
                .collect(Collectors.toList());
    }


}
