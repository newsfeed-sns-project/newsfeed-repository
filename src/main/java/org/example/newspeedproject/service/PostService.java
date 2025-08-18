package org.example.newspeedproject.service;

import lombok.RequiredArgsConstructor;
import org.example.newspeedproject.dto.PostResponseDto;
import org.example.newspeedproject.entity.Post;
import org.example.newspeedproject.repository.PostRepository;
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

    @Transactional
    public PostResponseDto save(String title, String contents) {
        Post post = new Post(title, contents);
        Post savedPost = postRepository.save(post);

        return new PostResponseDto(savedPost.getId(), savedPost.getTitle(), savedPost.getContents(), savedPost.getCreatedDate(), savedPost.getModifiedDate());
    }

    public List<PostResponseDto> findAll() {
        List<Post> posts = postRepository.findAll();
        List<PostResponseDto> dtos = new ArrayList<>();

        for(Post post : posts) {
            PostResponseDto postResponseDto = new PostResponseDto(post.getId(), post.getTitle(), post.getContents(), post.getCreatedDate(), post.getModifiedDate());
            dtos.add(postResponseDto);
        }
        return dtos;
    }

    public PostResponseDto findById(Long id) {
        Optional<Post> posted = postRepository.findById(id);
        if(posted.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "작성된 일정이 없습니다.");
        }

        Post findPost = posted.get();
        return new PostResponseDto(findPost.getId(), findPost.getTitle(), findPost.getContents(), findPost.getCreatedDate(), findPost.getModifiedDate());
    }

    @Transactional
    public void updatePost(Long id, String title, String contents) {
        Post posted = postRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "없는 글 입니다."));
        posted.updatePost(title, contents);
    }

    @Transactional
    public void deletePost(Long id) {
        Post posted = postRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "삭제 할 글이 없습니다."));
        postRepository.delete(posted);
    }
}
