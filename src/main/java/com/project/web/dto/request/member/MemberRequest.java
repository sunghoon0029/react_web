package com.project.web.dto.request.member;

import com.project.web.entity.Member;
import lombok.Getter;

@Getter
public class MemberRequest {

    private Long id;
    private String email;
    private String password;
    private String name;
    private String birth;
    private String gender;
    private String phoneNumber;

    public Member toEntity(MemberRequest request) {
        return Member.builder()
                .id(id)
                .email(email)
                .password(password)
                .name(name)
                .birth(birth)
                .gender(gender)
                .phoneNumber(phoneNumber)
                .build();
    }
}
