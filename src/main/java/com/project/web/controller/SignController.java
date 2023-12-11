package com.project.web.controller;

import com.project.web.dto.request.member.MemberRequest;
import com.project.web.security.jwt.TokenDTO;
import com.project.web.service.SignService;
import lombok.RequiredArgsConstructor;
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

    @DeleteMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization")  String accessToken, @RequestHeader("RefreshToken") String refreshToken) {
        try {
            signService.logout(accessToken, refreshToken);
            return ResponseEntity.ok("로그아웃 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("로그아웃 실패" + e.getMessage());
        }
    }

    @GetMapping("/refresh")
    public ResponseEntity<TokenDTO> refreshAccessToken(@RequestBody TokenDTO token) throws Exception {
        return new ResponseEntity<>(signService.refreshAccessToken(token), HttpStatus.OK);
    }
}
