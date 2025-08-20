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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public LikeResponse addLike(LikeRequest request) {
        Post post = postRepository.findById(request.getPostId()).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다.")
        );

        User user = userRepository.findById(request.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 없습니다.")
        );

        if (likeRepository.findByPostAndUser(post, user).isPresent()) {
            throw new IllegalArgumentException("이미 좋아요를 눌렀습니다.");
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
                () -> new IllegalArgumentException("좋아요가 없습니다."));

        likeRepository.delete(like);

        return new LikeResponse(
                like.getId(),
                like.getPost().getId(),
                like.getUser().getId(),
                "좋아요가 취소되었습니다.");
    }
}
