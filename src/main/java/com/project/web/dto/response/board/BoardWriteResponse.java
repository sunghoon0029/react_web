package com.project.web.dto.response.board;

import com.project.web.dto.response.file.FileUploadResponse;
import com.project.web.entity.Board;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class BoardWriteResponse {

    private Long id;
    private String title;
    private String contents;
    private int hits;
    private Long member;
    private List<FileUploadResponse> files;
    private LocalDateTime createdAt;

    public static BoardWriteResponse toDTO(Board board) {
        return BoardWriteResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .contents(board.getContents())
                .hits(board.getHits())
                .member(board.getMember().getId())
                .files(FileUploadResponse.toDTO(board.getFiles()))
                .createdAt(board.getCreatedAt())
                .build();
    }
}
