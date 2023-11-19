package com.project.web.controller;

import com.project.web.dto.request.MemberRequest;
import com.project.web.dto.response.MemberResponse;
import com.project.web.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/save")
    public ResponseEntity<Boolean> save(@RequestBody MemberRequest request) {
        return new ResponseEntity<>(memberService.save(request), HttpStatus.OK);
    }

    @GetMapping("/")
    public List<MemberResponse> findAll() {
        return memberService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> findById(@PathVariable Long id) {
        return new ResponseEntity<>(memberService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/email")
    public ResponseEntity<MemberResponse> findByEmail(@RequestParam String email) throws Exception {
        return new ResponseEntity<>(memberService.findByEmail(email), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updateById(@PathVariable Long id,
                                          @RequestBody MemberRequest request) {
        return new ResponseEntity<>(memberService.updateById(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        return new ResponseEntity<>(memberService.deleteById(id), HttpStatus.OK);
    }
}
