package org.example.newspeedproject.comment.dto;


import lombok.Getter;

@Getter
public class CommentResponseDto {


    private final String comment;

    public CommentResponseDto(String comment) {
        this.comment = comment;
    }



}
