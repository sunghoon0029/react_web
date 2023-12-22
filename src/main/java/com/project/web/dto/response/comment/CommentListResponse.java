package com.project.web.dto.response.comment;

import com.project.web.entity.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommentListResponse {

    private Long id;
    private String contents;
    private String member;
    private Long board;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static CommentListResponse toDTO(Comment comment) {
        return CommentListResponse.builder()
                .id(comment.getId())
                .contents(comment.getContents())
                .member(comment.getMember().getName())
                .board(comment.getBoard().getId())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }
}
