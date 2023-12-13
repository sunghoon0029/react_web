package com.project.web.controller;

import com.project.web.dto.request.board.BoardRequest;
import com.project.web.dto.response.board.BoardDetailResponse;
import com.project.web.dto.response.board.BoardListResponse;
import com.project.web.dto.response.board.BoardWriteResponse;
import com.project.web.security.CustomUserDetails;
import com.project.web.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/save")
    public ResponseEntity<BoardWriteResponse> save(@RequestBody BoardRequest request,
                                                   @AuthenticationPrincipal CustomUserDetails member) throws Exception {
        return ResponseEntity.ok(boardService.save(request, member));
    }

    @GetMapping("/")
    public ResponseEntity<List<BoardListResponse>> findAll() throws Exception {
        return ResponseEntity.ok(boardService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardDetailResponse> findById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(boardService.findById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updateById(@PathVariable Long id,
                                              @RequestBody BoardRequest request,
                                              @AuthenticationPrincipal CustomUserDetails member) throws Exception {
        return ResponseEntity.ok(boardService.updateById(id, request, member));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(boardService.deleteById(id));
    }
}
