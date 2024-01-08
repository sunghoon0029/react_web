package com.project.web.dto.response.file;

import com.project.web.entity.File;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class FileUploadResponse {

    private Long id;
    private String originalFileName;
    private String storedFileName;
    private String filePath;
    private Long fileSize;
    private String extension;
    private LocalDateTime createdAt;

    public static List<FileUploadResponse> toDTO(List<File> files) {
        return files.stream()
                .map(file -> FileUploadResponse.builder()
                        .id(file.getId())
                        .originalFileName(file.getOriginalFileName())
                        .storedFileName(file.getStoredFileName())
                        .filePath(file.getFilePath())
                        .fileSize(file.getFileSize())
                        .extension(file.getExtension())
                        .createdAt(file.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }
}
