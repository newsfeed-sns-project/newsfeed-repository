package org.example.newspeedproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.newspeedproject.dto.CreatePostRequestDto;
import org.example.newspeedproject.dto.PostPageResponse;
import org.example.newspeedproject.dto.PostResponseDto;
import org.example.newspeedproject.service.PostService;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    //게시물 작성
    @PostMapping("/posts")
    public ResponseEntity<PostResponseDto> save(@RequestBody CreatePostRequestDto requestDto) {
        PostResponseDto postResponseDto = postService.save(requestDto.getTitle(), requestDto.getContents());
        return new ResponseEntity<>(postResponseDto, HttpStatus.CREATED);
    }

//    //게시물 검색
//    @GetMapping("/posts")
//    public ResponseEntity<List<PostResponseDto>> findAll() {
//        List<PostResponseDto> postResponseDtos = postService.findAll();
//        return new ResponseEntity<>(postResponseDtos, HttpStatus.OK);
//    }

    //게시물 검색
    @GetMapping("/posts")
    public ResponseEntity<PostPageResponse> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        PostPageResponse response = postService.findAll(page, size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //게시물 id검색
    @GetMapping("/posts/{id}")
    public ResponseEntity<PostResponseDto> findById(@PathVariable Long id) {
        PostResponseDto postResponseDto = postService.findById(id);
        return new ResponseEntity<>(postResponseDto, HttpStatus.OK);
    }

    //게시물 내용 변경
    @PatchMapping("/posts/{id}")
    public ResponseEntity<String> updatePost(@PathVariable Long id, @RequestBody CreatePostRequestDto requestDto) {
        postService.updatePost(id, requestDto.getTitle(), requestDto.getContents());
        return new ResponseEntity<>("*** 게시글 수정 완료 ***", HttpStatus.OK);
    }

    //게시물 삭제
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>("*** 게시글 삭제 성공 ***", HttpStatus.OK);
    }
}
