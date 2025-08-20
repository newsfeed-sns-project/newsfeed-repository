package org.example.newspeedproject.post.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.newspeedproject.post.consts.Const;
import org.example.newspeedproject.post.dto.reponse.PostPageResponseDto;
import org.example.newspeedproject.post.dto.reponse.PostResponseDto;
import org.example.newspeedproject.post.dto.request.CreatePostRequestDto;
import org.example.newspeedproject.post.dto.request.FindDateRequestDto;
import org.example.newspeedproject.post.dto.request.UpdatePostRequestDto;
import org.example.newspeedproject.post.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    //게시물 작성
    @PostMapping("/posts")
    public ResponseEntity<PostResponseDto> create(
            @RequestBody CreatePostRequestDto requestDto,
            @SessionAttribute(name = Const.LOGIN_USER) Long userId
    ) {
        PostResponseDto postResponseDto = postService.save(
                requestDto.getTitle(),
                requestDto.getContents(),
                userId
        );
        return new ResponseEntity<>(postResponseDto, HttpStatus.CREATED);
    }

    //게시물 검색
    @GetMapping("/posts")
    public ResponseEntity<PostPageResponseDto> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PostPageResponseDto response = postService.findAll(page, size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //게시물 검색(수정일 기준)
    @GetMapping("/posts/array")
    public ResponseEntity<PostPageResponseDto> findAllByModi(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        PostPageResponseDto response = postService.findAllByModi(page, size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //게시물 기간별 검색(생성일 기준)
    @PostMapping("/posts/search")
    public ResponseEntity<PostPageResponseDto> findByDate(@Valid @RequestBody FindDateRequestDto request, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        LocalDate start = request.getStart();
        LocalDate end = request.getEnd();
        PostPageResponseDto result = postService.findByDate(start, end, page, size);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //게시물 id검색
    @GetMapping("/posts/{id}")
    public ResponseEntity<PostResponseDto> findById(@PathVariable Long id) {
        PostResponseDto postResponseDto = postService.findById(id);
        return new ResponseEntity<>(postResponseDto, HttpStatus.OK);
    }

    // 연관관계 설정 후 업데이트, 삭제 시 사용자 검증 구문
    @PutMapping("/posts/{id}")
    public ResponseEntity<String> updatePost(@PathVariable Long id, @Valid @RequestBody UpdatePostRequestDto requestDto, @SessionAttribute(name = Const.LOGIN_USER) Long userId) {
        postService.updatePost(id, requestDto.getTitle(), requestDto.getContents(), userId);
        return new ResponseEntity<>("*** 게시글 수정 완료 ***", HttpStatus.OK);
    }

    //게시물 삭제
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id, @SessionAttribute(name = Const.LOGIN_USER) Long userId) {
        postService.deletePost(id, userId);
        return new ResponseEntity<>("*** 게시글 삭제 성공 ***", HttpStatus.OK);
    }
}
