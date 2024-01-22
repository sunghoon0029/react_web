package com.project.web.dto.response.board;

import com.project.web.entity.Board;
import com.project.web.entity.File;
import com.project.web.service.FileService;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class BoardDetailResponse {

    private String title;
    private String contents;
    private int hits;
    private String member;
    private List<String> fileUrls;
    private List<byte[]> fileBytes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static BoardDetailResponse toDTO(Board board) {
        List<String> fileUrls = board.getFiles()
                .stream()
                .map(File::getFilePath)
                .collect(Collectors.toList());

        List<byte[]> fileBytes = new ArrayList<>();

        if (!CollectionUtils.isEmpty(board.getFiles())) {
            for (File file : board.getFiles()) {
                try {
                    byte[] bytes = FileService.loadImageAsByteArray(file.getFilePath());
                    fileBytes.add(bytes);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return BoardDetailResponse.builder()
                .title(board.getTitle())
                .contents(board.getContents())
                .hits(board.getHits())
                .member(board.getMember().getName())
                .fileUrls(fileUrls)
                .fileBytes(fileBytes)
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .build();
    }
}
