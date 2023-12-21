package com.project.web.dto.response.comment;

import com.project.web.entity.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommentUpdateResponse {

    private String contents;
    private String member;
    private LocalDateTime updatedAt;

    public static CommentUpdateResponse toDTO(Comment comment) {
        return CommentUpdateResponse.builder()
                .contents(comment.getContents())
                .member(comment.getMember().getName())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }
}
