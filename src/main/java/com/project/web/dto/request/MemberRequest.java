package com.project.web.dto.request;

import com.project.web.entity.Member;
import lombok.Getter;

@Getter
public class MemberRequest {

    private Long id;

    private String email;

    private String password;

    private String name;

    public Member toEntity(MemberRequest request) {
        return Member.builder()
                .id(id)
                .email(email)
                .password(password)
                .name(name)
                .build();
    }
}
