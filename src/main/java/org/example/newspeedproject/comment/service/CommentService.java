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

@Service
@RequiredArgsConstructor
public class CommentService {
    private CommentRepository commentRepository;

    @Transactional
    public CommentResponseDto save (Long useId, Long postId, CommentRequestDto commentRequestDto) {

        User user = new User(); //팀원 지원 필요
        Post post = new Post(); //팀원 지원 필요

        Comment comment = new Comment(commentRequestDto.getComment(), user, post);
        commentRepository.save(comment);

        CommentResponseDto commentResponseDto = new CommentResponseDto(comment.getComment());
        return commentResponseDto;
    }
}
