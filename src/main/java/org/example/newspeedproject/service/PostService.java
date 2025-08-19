package org.example.newspeedproject.service;

import lombok.RequiredArgsConstructor;
import org.example.newspeedproject.dto.PostPageResponse;
import org.example.newspeedproject.dto.PostResponseDto;
import org.example.newspeedproject.entity.Post;
import org.example.newspeedproject.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    //게시글 생성 서비스
    @Transactional
    public PostResponseDto save(String title, String contents) {
        Post post = new Post(title, contents);
        Post savedPost = postRepository.save(post);

        return new PostResponseDto(savedPost.getId(), savedPost.getTitle(), savedPost.getContents(), savedPost.getCreatedDate(), savedPost.getModifiedDate());
    }
//    //게시글 전체 검색 서비스
//    public List<PostResponseDto> findAll() {
//        List<Post> posts = postRepository.findAll();
//        List<PostResponseDto> dtos = new ArrayList<>();
//
//        for(Post post : posts) {
//            PostResponseDto postResponseDto = new PostResponseDto(post.getId(), post.getTitle(), post.getContents(), post.getCreatedDate(), post.getModifiedDate());
//            dtos.add(postResponseDto);
//        }
//        return dtos;
//    }

    //게시글 전체 검색 서비스(페이징, 내림차순)
    public PostPageResponse findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Post> posts =  postRepository.findAll(pageable);

        List<PostResponseDto> dtos = new ArrayList<>();
        for(Post post : posts) {
            PostResponseDto dto = new PostResponseDto(post.getId(), post.getTitle(), post.getContents(), post.getCreatedDate(), post.getModifiedDate());
            dtos.add(dto);
        }

        return new PostPageResponse(dtos, posts.getNumber(), posts.getTotalPages(), posts.getTotalElements());

    }

    //게시글 상세 검색 서비스
    public PostResponseDto findById(Long id) {
        Optional<Post> posted = postRepository.findById(id);
        if(posted.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "작성된 일정이 없습니다.");
        }

        Post findPost = posted.get();
        return new PostResponseDto(findPost.getId(), findPost.getTitle(), findPost.getContents(), findPost.getCreatedDate(), findPost.getModifiedDate());
    }

    //게시글 수정 서비스
    @Transactional
    public void updatePost(Long id, String title, String contents) {
        Post posted = postRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "없는 글 입니다."));
        posted.updatePost(title, contents);
    }

    //게시글 삭제 서비스
    @Transactional
    public void deletePost(Long id) {
        Post posted = postRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "삭제 할 글이 없습니다."));
        postRepository.delete(posted);
    }
}
