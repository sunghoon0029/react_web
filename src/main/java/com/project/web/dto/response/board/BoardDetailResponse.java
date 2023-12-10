package com.project.web.dto.response.board;

import com.project.web.entity.Board;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BoardDetailResponse {

    private String title;
    private String contents;
    private int hits;
    private String member;
    private LocalDateTime createdAt;

    public static BoardDetailResponse toDTO(Board board, String member) {
        return BoardDetailResponse.builder()
                .title(board.getTitle())
                .contents(board.getContents())
                .hits(board.getHits())
                .member(member)
                .createdAt(board.getCreatedAt())
                .build();
    }
}
