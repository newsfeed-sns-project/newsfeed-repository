package org.example.newspeedproject.like.service;

import lombok.RequiredArgsConstructor;
import org.example.newspeedproject.like.dto.LikeResponse;
import org.example.newspeedproject.like.dto.LikeRequest;
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

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public LikeResponse addLike(LikeRequest request) {
        Post post = postRepository.findById(request.getPostId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다.")
        );

        User user = userRepository.findById(request.getUserId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다.")
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
                "좋아요가 완료되었습니다."
        );
    }

    @Transactional
    public LikeResponse deleteLike(Long likeId) {
        Like like = likeRepository.findById(likeId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "좋아요가 없습니다."));

        likeRepository.delete(like);

        return new LikeResponse(
                like.getId(),
                like.getPost().getId(),
                like.getUser().getId(),
                "좋아요가 취소되었습니다.");
    }
}
