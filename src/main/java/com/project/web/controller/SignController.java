package com.project.web.controller;

import com.project.web.dto.request.member.MemberRequest;
import com.project.web.security.jwt.TokenDTO;
import com.project.web.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class SignController {

    private final SignService signService;

    @PostMapping("/join")
    public ResponseEntity<Boolean> join(@RequestBody MemberRequest request) throws Exception {
        return new ResponseEntity<>(signService.join(request), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody MemberRequest request) throws Exception {
        return new ResponseEntity<>(signService.login(request), HttpStatus.OK);
    }

    @PostMapping("/cookie/login")
    public ResponseEntity<TokenDTO> cookieLogin(@RequestBody MemberRequest request, HttpServletResponse response) throws Exception {

        TokenDTO tokenDTO = signService.login(request);
        signService.addRefreshTokenToCookie(tokenDTO.getRefreshToken(), response);

        return new ResponseEntity<>(tokenDTO, HttpStatus.OK);
    }

//    @PostMapping("/logout")
//    public ResponseEntity<Void> logout(@RequestHeader(HttpHeaders.AUTHORIZATION) String header) {
//        String accessToken = extractAccessToken(header);
//        try {
//            signService.logout(accessToken);
//            return  new ResponseEntity<>(HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//    }
//
//    private String extractAccessToken(String header) {
//        return header.replace("Bearer ", "");
//    }

//    @PostMapping("/logout")
//    public ResponseEntity<String> logout(@RequestBody TokenDTO tokenDTO) {
////        return new ResponseEntity<>(signService.logout(tokenDTO.getAccessToken()), HttpStatus.OK);
//        try {
//            signService.logout(tokenDTO.getAccessToken());
//            return ResponseEntity.ok("로그아웃 완료");
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization")  String accessToken) {
//        return new ResponseEntity<>(signService.logout(accessToken), HttpStatus.OK);
        try {
            signService.logout(accessToken);
            return ResponseEntity.ok("로그아웃 완료");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/refresh")
    public ResponseEntity<TokenDTO> refreshAccessToken(@RequestBody TokenDTO token) throws Exception {
        return new ResponseEntity<>(signService.refreshAccessToken(token), HttpStatus.OK);
    }
}
