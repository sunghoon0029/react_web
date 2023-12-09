package com.project.web.controller;

import com.project.web.dto.request.BoardRequest;
import com.project.web.dto.response.BoardResponse;
import com.project.web.entity.Member;
import com.project.web.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

//    @PostMapping("/save")
//    public ResponseEntity<Boolean> save(@RequestBody BoardRequest request) throws Exception {
//        return ResponseEntity.ok(boardService.save(request));
//    }

    @PostMapping("/save")
    public ResponseEntity<BoardResponse> write(@RequestBody BoardRequest request,
                                               @AuthenticationPrincipal Member member) {

        System.out.println("Received member: " + member);

        return ResponseEntity.ok(boardService.write(request, member));
    }

//    @GetMapping("/")
//    public ResponseEntity<List<BoardResponse>> findAll() throws Exception {
//        return ResponseEntity.ok(boardService.findAll());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<BoardResponse> findById(@PathVariable Long id) throws Exception {
//        return ResponseEntity.ok(boardService.findById(id));
//    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updateById(@PathVariable Long id,
                                              @RequestBody BoardRequest request) throws Exception {
        return ResponseEntity.ok(boardService.updateById(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(boardService.deleteById(id));
    }
}
