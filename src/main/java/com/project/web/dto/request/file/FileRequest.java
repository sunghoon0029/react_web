package com.project.web.dto.request.file;

import com.project.web.entity.File;
import lombok.Getter;

@Getter
public class FileRequest {

    private String originalFileName;
    private String fileName;
    private String filePath;

    public static File toEntity(FileRequest request) {
        return File.builder()
                .originalFileName(request.originalFileName)
                .fileName(request.fileName)
                .filePath(request.filePath)
                .build();
    }
}
