package com.project.web.controller;

import com.project.web.dto.request.board.BoardRequest;
import com.project.web.dto.response.board.BoardDetailResponse;
import com.project.web.dto.response.board.BoardListResponse;
import com.project.web.dto.response.board.BoardUpdateResponse;
import com.project.web.dto.response.board.BoardWriteResponse;
import com.project.web.security.CustomUserDetails;
import com.project.web.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

//    @PostMapping("/save")
//    public ResponseEntity<BoardWriteResponse> save(@RequestBody BoardRequest request,
//                                                   @AuthenticationPrincipal CustomUserDetails member) throws Exception {
//        return ResponseEntity.ok(boardService.save(request, member));
//    }

//    @PostMapping(value = "/save", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @PostMapping("/save")
    public ResponseEntity<BoardWriteResponse> saveWithFiles(@RequestPart(value = "request") BoardRequest request,
                                                            @RequestPart(value = "image", required = false) List<MultipartFile> files,
                                                            @AuthenticationPrincipal CustomUserDetails member) throws Exception {
        return ResponseEntity.ok(boardService.saveWithFile(request, files, member));
    }

//    @GetMapping("/")
//    public ResponseEntity<List<BoardListResponse>> findAll() throws Exception {
//        return ResponseEntity.ok(boardService.findAll());
//    }

    @GetMapping("/")
    public ResponseEntity<Page<BoardListResponse>> page(@PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(boardService.page(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardDetailResponse> findById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(boardService.findById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BoardUpdateResponse> updateById(@PathVariable Long id,
                                                          @RequestBody BoardRequest request,
                                                          @AuthenticationPrincipal CustomUserDetails member) throws Exception {
        return ResponseEntity.ok(boardService.updateById(id, request, member));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(boardService.deleteById(id));
    }
}
