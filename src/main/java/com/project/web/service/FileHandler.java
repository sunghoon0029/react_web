package com.project.web.service;

import com.project.web.entity.File;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileHandler {

    public List<File> parseFileInfo(List<MultipartFile> multipartFiles) throws Exception {

        List<File> fileList = new ArrayList<>();

        if (!CollectionUtils.isEmpty(multipartFiles)) {

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String currentDate = now.format(dateTimeFormatter);

            String absolutePath = new java.io.File("").getAbsolutePath() + java.io.File.separator + java.io.File.separator;

            String path = "file" + java.io.File.separator + currentDate;
            java.io.File file = new java.io.File(path);

            if (!file.exists()) {
                file.mkdirs();
            }

            for (MultipartFile multipartFile : multipartFiles) {

                String originalFileExtension;
                String contentType = multipartFile.getContentType();

                if (ObjectUtils.isEmpty(contentType)) {
                    break;
                } else {
                    if (contentType.contains("image/jpeg"))
                        originalFileExtension = ".jpg";
                    else if (contentType.contains("image/png"))
                        originalFileExtension = ".png";
                    else
                        break;
                }

                String newFileName = System.nanoTime() + originalFileExtension;

                File file1 = File.builder()
                        .originalFileName(multipartFile.getOriginalFilename())
                        .filePath(path + java.io.File.separator + newFileName)
                        .fileSize(multipartFile.getSize())
                        .build();

                fileList.add(file1);

                file = new java.io.File(absolutePath + path + java.io.File.separator + newFileName);

                multipartFile.transferTo(file);

                file.setWritable(true);
                file.setReadable(true);
            }
        }

        return fileList;
    }
}
