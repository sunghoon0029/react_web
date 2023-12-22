package com.project.web.dto.response.comment;

import com.project.web.entity.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommentWriteResponse {

    private String contents;
    private String member;
    private Long board;
    private LocalDateTime createdAt;

    public static CommentWriteResponse toDTO(Comment comment) {
        return CommentWriteResponse.builder()
                .contents(comment.getContents())
                .member(comment.getMember().getName())
                .board(comment.getBoard().getId())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
