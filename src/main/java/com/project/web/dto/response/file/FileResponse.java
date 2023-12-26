package com.project.web.dto.response.file;

import com.project.web.entity.File;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FileResponse {

    private Long id;
    private String originalFileName;
    private String fileName;
    private String filePath;
    private LocalDateTime createdAt;

    public static FileResponse toDTO(File file) {
        return FileResponse.builder()
                .id(file.getId())
                .originalFileName(file.getOriginalFileName())
                .fileName(file.getFileName())
                .filePath(file.getFilePath())
                .createdAt(file.getCreatedAt())
                .build();
    }
}
