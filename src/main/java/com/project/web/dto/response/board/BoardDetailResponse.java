package com.project.web.dto.response.board;

import com.project.web.dto.response.file.FileUploadResponse;
import com.project.web.entity.Board;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class BoardDetailResponse {

    private String title;
    private String contents;
    private int hits;
    private String member;
    private List<FileUploadResponse> files;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static BoardDetailResponse toDTO(Board board) {
        return BoardDetailResponse.builder()
                .title(board.getTitle())
                .contents(board.getContents())
                .hits(board.getHits())
                .member(board.getMember().getName())
                .files(FileUploadResponse.toDTO(board.getFiles()))
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .build();
    }
}
