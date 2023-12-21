package com.project.web.dto.request.comment;

import com.project.web.entity.Comment;
import lombok.Getter;

@Getter
public class CommentRequest {

    private String contents;

    public static Comment toEntity(CommentRequest request) {
        return Comment.builder()
                .contents(request.contents)
                .build();
    }
}
