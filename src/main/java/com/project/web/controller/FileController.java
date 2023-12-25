package com.project.web.controller;

import com.project.web.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload/file")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(fileService.uploadFile(file));
    }

    @PostMapping("/upload/files")
    public ResponseEntity<?> uploadFiles(@RequestParam("files") List<MultipartFile> files) {
        return ResponseEntity.ok(fileService.uploadFiles(files));
    }
}
