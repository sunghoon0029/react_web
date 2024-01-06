package com.project.web.service;

import com.project.web.entity.Board;
import com.project.web.entity.File;
import com.project.web.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    @Value("${file.dir}")
    private String fileDir;

    private final FileRepository fileRepository;
//    private final BoardRepository boardRepository;
//
//    public File uploadFile(MultipartFile file) throws IOException {
//
//        String originalFileName = file.getOriginalFilename();
//        String storedFileName = UUID.randomUUID().toString() + "_" + originalFileName;
//        String filePath = fileDir + java.io.File.separator + storedFileName;
//
//        file.transferTo(new java.io.File(filePath));
//
//        File uploadFile = File.builder()
//                .originalFileName(originalFileName)
//                .storedFileName(storedFileName)
//                .filePath(filePath)
//                .build();
//
////        return fileRepository.save(uploadFile);
//        return uploadFile;
//    }
//
//    @Transactional
//    public List<File> uploadFiles(List<MultipartFile> files, Long boardId) throws Exception {
//
//        Board board = boardRepository.findById(boardId)
//                .orElseThrow(() -> new Exception("게시글 정보를 찾을 수 없습니다."));
//
//        List<File> uploadFiles = new ArrayList<>();
//
//        for (MultipartFile file : files) {
//
//            String originalFileName = file.getOriginalFilename();
//            String storedFileName = UUID.randomUUID().toString() + "_" + originalFileName;
//            String filePath = fileDir + java.io.File.separator + storedFileName;
//
//            file.transferTo(new java.io.File(filePath));
//
//            File uploadFile = File.builder()
//                    .originalFileName(originalFileName)
//                    .storedFileName(storedFileName)
//                    .filePath(filePath)
//                    .fileSize(file.getSize())
//                    .build();
//
//            uploadFile.setBoard(board);
//            uploadFiles.add(uploadFile);
//            fileRepository.save(uploadFile);
//        }
//        return uploadFiles;
//    }

    @Transactional
    public void uploadFiles(Board board, List<MultipartFile> multipartFiles) throws Exception {

        if (!CollectionUtils.isEmpty(multipartFiles)) {

            for (MultipartFile multipartFile : multipartFiles) {

                String originalFileName = multipartFile.getOriginalFilename();
                String storedFileName = UUID.randomUUID().toString() + "_" + originalFileName;
                String filePath = fileDir + originalFileName;
                long fileSize = multipartFile.getSize();
                String extension = multipartFile.getContentType();

                File file = new File(originalFileName, storedFileName, filePath, fileSize, extension);
                file.setBoard(board);
                fileRepository.save(file);

                try {
                    multipartFile.transferTo(new java.io.File(filePath));
                } catch (IOException e) {
                    throw new Exception("파일 업로드 실패", e);
                }
            }
        }
    }


}
