package com.project.web.dto.response.board;

import com.project.web.entity.Board;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BoardUpdateResponse {

    private Long id;
    private String title;
    private String contents;
    private int hits;
    private Long member;
    private LocalDateTime updatedAt;

    public static BoardUpdateResponse toDTO(Board board) {
        return BoardUpdateResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .contents(board.getContents())
                .hits(board.getHits())
                .member(board.getMember().getId())
                .updatedAt(board.getUpdatedAt())
                .build();
    }
}
