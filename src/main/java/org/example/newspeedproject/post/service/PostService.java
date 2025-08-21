package org.example.newspeedproject.post.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.newspeedproject.follow.service.FollowService;
import org.example.newspeedproject.commo.exception.UnauthorizedException;
import org.example.newspeedproject.post.dto.reponse.PostPageResponseDto;
import org.example.newspeedproject.post.dto.reponse.PostResponseDto;
import org.example.newspeedproject.post.entity.Post;
import org.example.newspeedproject.post.repository.PostRepository;
import org.example.newspeedproject.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final FollowService followService; //팔로우한 사람들의 게시물을 받아오기 위해서 선언


    //게시글 생성 서비스
    @Transactional
    public PostResponseDto save(String title, String contents, Long userId) {
        User user = User.fromUserId(userId);
        Post post = new Post(title, contents, user);
        Post savedPost = postRepository.save(post);

        return new PostResponseDto(savedPost.getId(), savedPost.getTitle(), savedPost.getContents(), savedPost.getCreatedDate(), savedPost.getModifiedDate(), user.getId());
    }

    //게시글 전체 검색 서비스(페이징, 내림차순)
    public PostPageResponseDto findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Post> posts =  postRepository.findAll(pageable);

        List<PostResponseDto> dtos = new ArrayList<>();
        for(Post post : posts) {
            PostResponseDto dto = new PostResponseDto(post.getId(), post.getTitle(), post.getContents(), post.getCreatedDate(), post.getModifiedDate(), post.getUser().getId());
            dtos.add(dto);
        }
        return new PostPageResponseDto(dtos, posts.getNumber(), posts.getTotalPages(), posts.getTotalElements());
    }

    //게시글 전체 검색 서비스(수정일 기준 내림차순)
    public PostPageResponseDto findAllByModi(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("modifiedDate").descending());
        Page<Post> posts =  postRepository.findAll(pageable);

        List<PostResponseDto> dtos = new ArrayList<>();
        for(Post post : posts) {
            PostResponseDto dto = new PostResponseDto(post.getId(), post.getTitle(), post.getContents(), post.getCreatedDate(), post.getModifiedDate(), post.getUser().getId());
            dtos.add(dto);
        }
        return new PostPageResponseDto(dtos, posts.getNumber(), posts.getTotalPages(), posts.getTotalElements());
    }

    //게시글 기간별 검색 서비스(생성일 기준 내림차순)
    public PostPageResponseDto findByDate(LocalDate startDate, LocalDate endDate, int page, int size) {

        //하루 기준을 명확하게 설정 (00시 ~ 23시 59분 59초)
        LocalDateTime start = startDate.atStartOfDay();
        LocalDateTime end = endDate.plusDays(1).atStartOfDay().minusSeconds(1);

        if (start.isAfter(end)) {
            throw new IllegalArgumentException("날짜를 잘 못 입력하셨습니다.");
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Post> posts = postRepository.findByCreatedDateBetween(start, end, pageable);

        List<PostResponseDto> dtos = new ArrayList<>();
        for(Post post : posts) {
            PostResponseDto dto = new PostResponseDto(post.getId(), post.getTitle(), post.getContents(), post.getCreatedDate(), post.getModifiedDate(), post.getUser().getId());
            dtos.add(dto);
        }

        return new PostPageResponseDto(dtos, posts.getNumber(), posts.getTotalPages(), posts.getTotalElements());
    }

    //게시글 상세 검색 서비스
    public PostResponseDto findById(Long id) {
        Post posted = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 스케줄이 존재하지 않습니다."));

        return new PostResponseDto(posted.getId(), posted.getTitle(), posted.getContents(), posted.getCreatedDate(), posted.getModifiedDate(), posted.getUser().getId());
    }

    // 연관관계 설정 후 업데이트, 삭제 시 사용자 검증 구문
    //게시글 수정 서비스
    @Transactional
    public void updatePost(Long id, String title, String contents, Long userId) {
        Post posted = postRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("게시글이 없습니다."));

        //작성자가 아닌 경우 예외처리 발생
        if (!userId.equals(posted.getUser().getId())) {
            throw new UnauthorizedException("작성자만 수정할 수 있습니다.");
        }

        posted.updatePost(title, contents);
    }

    //게시글 삭제 서비스
    @Transactional
    public void deletePost(Long id, Long userId) {
        Post posted = postRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("삭제 할 게시글이 없습니다."));

        //작성자가 아닌 경우 예외처리 발생
        if (!userId.equals(posted.getUser().getId())) {
            throw new UnauthorizedException("작성자만 수정할 수 있습니다.");
        }
        postRepository.delete(posted);
    }

    // 내가 팔로우한 사람들의 게시물 최신순으로 조회
    @Transactional
    public List<PostResponseDto> getNewsfeedPosts(Long userId) {
        // 팔로우 서비스에서  내가 팔로우 하는 user 목록 가져오기
        List<User> followedUsers = followService.getFollowingsUsers(userId);
        //  팔로우하는 사람들의 게시물만 최신순으로 조회
        List<Post> newsfeedPosts = postRepository.findAllByUserInOrderByCreatedDateDesc(followedUsers);

        return newsfeedPosts.stream()
                .map(post -> new PostResponseDto(
                        post.getId(),
                        post.getTitle(),
                        post.getContents(),
                        post.getCreatedDate(),
                        post.getModifiedDate(),
                        post.getUser().getId()))
                .collect(Collectors.toList());
    }
}