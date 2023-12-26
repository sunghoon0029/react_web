package com.project.web.controller;

import com.project.web.entity.File;
import com.project.web.service.FileService;
import com.project.web.service.FileTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("files") List<MultipartFile> files) throws Exception {

        for (MultipartFile file : files) {
            fileService.upload(file);
        }

        return ResponseEntity.ok("success");
    }

//    @PostMapping("/upload")
//    public ResponseEntity uploadFiles(@RequestPart List<MultipartFile> files) throws IOException {
//
//        for (MultipartFile file : files) {
//            fileService.upload(file);
//        }
//
//        return ResponseEntity.ok("success");
//    }

//    @PostMapping("/upload/file")
//    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
//        return ResponseEntity.ok(fileService.uploadFile(file));
//    }
//
//    @PostMapping("/upload/files")
//    public ResponseEntity<?> uploadFiles(@RequestParam("files") List<MultipartFile> files) {
//        return ResponseEntity.ok(fileService.uploadFiles(files));
//    }
}
