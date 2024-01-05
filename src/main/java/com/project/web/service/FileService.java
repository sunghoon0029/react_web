package com.project.web.service;

import com.project.web.entity.Board;
import com.project.web.entity.File;
import com.project.web.repository.BoardRepository;
import com.project.web.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    private final BoardRepository boardRepository;

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

    @Transactional
    public List<File> uploadFiles(List<MultipartFile> files, Long boardId) throws Exception {

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new Exception("게시글 정보를 찾을 수 없습니다."));

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
                    .fileSize(file.getSize())
                    .build();

            uploadFile.setBoard(board);
            uploadFiles.add(uploadFile);
            fileRepository.save(uploadFile);
        }
        return uploadFiles;
    }
}
