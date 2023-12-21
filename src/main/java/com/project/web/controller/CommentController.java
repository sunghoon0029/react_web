package com.project.web.controller;

import com.project.web.dto.request.comment.CommentRequest;
import com.project.web.dto.response.comment.CommentListResponse;
import com.project.web.dto.response.comment.CommentUpdateResponse;
import com.project.web.dto.response.comment.CommentWriteResponse;
import com.project.web.security.CustomUserDetails;
import com.project.web.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/board/{id}/comment/save")
    public ResponseEntity<CommentWriteResponse> save(@PathVariable Long id,
                                                     @RequestBody CommentRequest request,
                                                     @AuthenticationPrincipal CustomUserDetails member) throws Exception {
        return ResponseEntity.ok(commentService.save(id, request, member));
    }

    @GetMapping("/")
    public ResponseEntity<List<CommentListResponse>> findAll() {
        return ResponseEntity.ok(commentService.findAll());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CommentUpdateResponse> updateById(@PathVariable Long id,
                                                            @RequestBody CommentRequest request,
                                                            @AuthenticationPrincipal CustomUserDetails member) throws Exception {
        return ResponseEntity.ok(commentService.updateById(id, request, member));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.deleteById(id));
    }
}
