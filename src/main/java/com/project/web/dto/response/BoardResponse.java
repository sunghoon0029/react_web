package com.project.web.dto.response;

import com.project.web.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class BoardResponse {

    private Long id;
    private String title;
    private String contents;
    private int hits;
    private String member;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static BoardResponse toDTO(Board board, String member) {
        return BoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .contents(board.getContents())
                .hits(board.getHits())
                .member(member)
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .build();
    }
}
