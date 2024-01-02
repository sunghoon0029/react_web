package com.project.web.service;

import com.project.web.entity.File;
import com.project.web.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    @Value("${file.dir}")
    private String fileDir;

    private final FileRepository fileRepository;

    public File uploadFile(MultipartFile file) throws IOException {

        String originalFilename = file.getOriginalFilename();
        String storedFilename = UUID.randomUUID().toString() + "_" + originalFilename;
        String filePath = fileDir + java.io.File.separator + storedFilename;

        file.transferTo(new java.io.File(filePath));

        File uploadFile = File.builder()
                .originalFilename(originalFilename)
                .storedFilename(storedFilename)
                .filePath(filePath)
                .build();

//        return fileRepository.save(uploadFile);
        return uploadFile;
    }

    public List<File> uploadFiles(List<MultipartFile> files) throws IOException {

        List<File> uploadFiles = new ArrayList<>();

        for (MultipartFile file : files) {

            String originalFilename = file.getOriginalFilename();
            String storedFilename = UUID.randomUUID().toString() + "_" + originalFilename;
            String filePath = fileDir + java.io.File.separator + storedFilename;

            file.transferTo(new java.io.File(filePath));

            File uploadFile = File.builder()
                    .originalFilename(originalFilename)
                    .storedFilename(storedFilename)
                    .filePath(filePath)
                    .build();

//            uploadFiles.add(fileRepository.save(uploadFile));
            uploadFiles.add(uploadFile);
        }
        return uploadFiles;
    }
}
