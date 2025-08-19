package org.example.newspeedproject.comment.dto;


import lombok.Getter;

@Getter
public class CommentResponse {


    private final String comment;

    public CommentResponse(String comment) {
        this.comment = comment;
    }

}
