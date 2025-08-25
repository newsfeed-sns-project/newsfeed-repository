package org.example.newspeedproject.like.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.newspeedproject.common.exception.ForbiddenException;
import org.example.newspeedproject.like.dto.LikeResponse;
import org.example.newspeedproject.like.entity.Like;
import org.example.newspeedproject.like.repository.LikeRepository;
import org.example.newspeedproject.post.entity.Post;
import org.example.newspeedproject.post.repository.PostRepository;
import org.example.newspeedproject.user.entity.User;
import org.example.newspeedproject.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public LikeResponse addLike(Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new EntityNotFoundException("게시글을 찾을 수 없습니다.")
        );

        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("유저를 찾을 수 없습니다.")
        );

        if (likeRepository.findByPostAndUser(post, user).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 좋아요를 눌렀습니다.");
        }

        Like like = new Like(post, user);
        Like savedLike = likeRepository.save(like);

        return new LikeResponse(
                savedLike.getId(),
                post.getId(),
                user.getId(),
                "좋아요가 완료되었습니다.",
                savedLike.getCreatedDate(),
                savedLike.getModifiedDate()
        );
    }

    @Transactional
    public LikeResponse deleteLike(Long likeId, Long userId) {
        Like like = likeRepository.findById(likeId).orElseThrow(
                () -> new EntityNotFoundException("좋아요가 없습니다."));

        if (!like.getUser().getId().equals(userId)) {
            throw new ForbiddenException("권한이 없습니다.");
        }

        LocalDateTime modifiedDate = LocalDateTime.now();

        likeRepository.delete(like);

        return new LikeResponse(
                like.getId(),
                like.getPost().getId(),
                like.getUser().getId(),
                "좋아요가 취소되었습니다.",
                null,
                modifiedDate
        );
    }
}
