package com.project.web.dto.response.board;

import com.project.web.entity.Board;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BoardListResponse {

    private Long id;
    private String title;
    private int hits;
    private String member;
    private LocalDateTime createdAt;

    public static BoardListResponse toDTO(Board board, String member) {
        return BoardListResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .hits(board.getHits())
                .member(member)
                .createdAt(board.getCreatedAt())
                .build();
    }
}
