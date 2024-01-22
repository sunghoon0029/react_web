package com.project.web.service;

import com.project.web.entity.Board;
import com.project.web.entity.File;
import com.project.web.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    @Value("${file.dir}")
    private String fileDir;

    private final FileRepository fileRepository;

    @Transactional
    public void uploadFile(Board board, List<MultipartFile> multipartFiles) throws FileUploadException {

        if (!CollectionUtils.isEmpty(multipartFiles)) {

            for (MultipartFile multipartFile : multipartFiles) {

                try {
                    String originalFileName = multipartFile.getOriginalFilename();
                    String storedFileName = UUID.randomUUID().toString() + "_" + originalFileName;
                    String filePath = fileDir + java.io.File.separator + storedFileName;
                    long fileSize = multipartFile.getSize();
                    String extension = multipartFile.getContentType();

                    File file = new File(originalFileName, storedFileName, filePath, fileSize, extension);
                    file.setBoard(board);
                    fileRepository.save(file);

                    multipartFile.transferTo(new java.io.File(filePath));
                } catch (IOException e) {
                    throw new FileUploadException("파일 업로드 실패", e);
                }
            }
        }
    }

    public static byte[] loadImageAsByteArray(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readAllBytes(path);
    }
}
