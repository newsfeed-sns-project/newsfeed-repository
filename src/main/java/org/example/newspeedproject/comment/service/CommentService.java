package org.example.newspeedproject.comment.service;


import lombok.RequiredArgsConstructor;
import org.example.newspeedproject.comment.dto.CommentRequestDto;
import org.example.newspeedproject.comment.dto.CommentResponseDto;
import org.example.newspeedproject.comment.entity.Comment;
import org.example.newspeedproject.comment.repository.CommentRepository;

import org.example.newspeedproject.post.entity.Post;
import org.example.newspeedproject.post.repository.PostRepository;
import org.example.newspeedproject.user.entity.User;
import org.example.newspeedproject.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private PostRepository postRepository;

    @Transactional
    public CommentResponseDto save(Long userId, Long postId, CommentRequestDto commentRequestDto) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("user not found")); //팀원 지원 필요
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("post not found"));
        Comment comment = new Comment(commentRequestDto.getComment(), user, post);
        commentRepository.save(comment);

        CommentResponseDto commentResponseDto = new CommentResponseDto(comment.getComment());
        return commentResponseDto;
    }

    @Transactional(readOnly = true)
    public CommentResponseDto findOne(Long id) {
        Comment comment = commentRepository
                .findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("comment not found"));
        return new CommentResponseDto(comment.getComment());
    }

    @Transactional(readOnly = true)
    public List<CommentResponseDto> findByPost(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream()
                .map(comment -> new CommentResponseDto(
                        comment.getComment()))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentResponseDto update(Long id, CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("comment not found"));
        comment.update(commentRequestDto.getComment());
        commentRepository.save(comment);
        return new CommentResponseDto(comment.getComment());
    }

    @Transactional
    public void delete(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("comment not found"));
        commentRepository.delete(comment);
    }
}
