package com.project.web.service;

import com.project.web.dto.request.member.MemberRequest;
import com.project.web.entity.Authority;
import com.project.web.entity.Member;
import com.project.web.repository.MemberRepository;
import com.project.web.security.jwt.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class SignService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final JwtProvider jwtProvider;
    private final RedisTemplate redisTemplate;
    private final RedisUtil redisUtil;

    public boolean join(MemberRequest request) throws Exception {
        try {
            Member member = request.toEntity(request);
            member.setRoles(Collections.singletonList(Authority.builder().name("ROLE_USER").build()));
            member.setPassword(passwordEncoder.encode(request.getPassword()));

            memberRepository.save(member);

        } catch (Exception e) {
            throw new Exception("잘못된 요청 입니다.");
        }
        return true;
    }

    public TokenDTO login(MemberRequest request) throws Exception {
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadCredentialsException("잘못된 계정 정보 입니다."));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new BadCredentialsException("잘못된 계정 정보 입니다.");
        }
        return TokenDTO.builder()
                .accessToken(jwtProvider.createToken(member.getEmail(), member.getRoles()))
                .refreshToken(createRefreshToken(member))
                .build();
    }

    public void logout(String accessToken, String refreshToken) throws Exception {
        if (!jwtProvider.validateToken(accessToken)) {
            throw new Exception("로그아웃 : 유효하지 않은 인증정보 입니다.");
        }

        Authentication authentication = jwtProvider.getAuthentication(accessToken);

        if (redisTemplate.opsForValue().get(authentication.getName()) != null) {
            redisUtil.delete(authentication.getName());
        }

        Long expiration = jwtProvider.getExpiration(accessToken);
        redisUtil.setBlackList(accessToken, "logout", expiration);
    }

    public String createRefreshToken(Member member) {
        Token token = tokenRepository.save(
                Token.builder()
                        .id(member.getId())
                        .refreshToken(UUID.randomUUID().toString())
                        .exp(7)
                        .build()
        );
        return token.getRefreshToken();
    }

    public Token validRefreshToken(Member member, String refreshToken) throws Exception {
        Token token = tokenRepository.findById(member.getId()).orElseThrow(() -> new Exception("만료된 계정입니다. 로그인을 다시 시도하세요."));
        if (token.getRefreshToken() == null) {
            return null;
        } else {
            if (token.getExp() < 1) {
                token.setExp(7);
                tokenRepository.save(token);
            }
            if (!token.getRefreshToken().equals(refreshToken)) {
                return null;
            } else {
                return token;
            }
        }
    }

    public TokenDTO refreshAccessToken(TokenDTO token) throws Exception {
        String email = jwtProvider.getEmail(token.getAccessToken());
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->
                new BadCredentialsException("잘못된 계정 정보 입니다."));
        Token refreshToken = validRefreshToken(member, token.getRefreshToken());

        if (refreshToken != null) {
            return TokenDTO.builder()
                    .accessToken(jwtProvider.createToken(email, member.getRoles()))
                    .refreshToken(refreshToken.getRefreshToken())
                    .build();
        } else {
            throw new Exception("로그인을 시도하세요.");
        }
    }

    public void addRefreshTokenToCookie(String token, HttpServletResponse response) {
        Cookie cookie = new Cookie("refreshToken", token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        response.addCookie(cookie);
    }
}
