package com.project.web.dto.response.member;

import com.project.web.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberProfileResponse {

    private String email;
    private String name;
    private String birth;
    private String gender;
    private String phoneNumber;

    public static MemberProfileResponse toDTO(Member member) {
        return MemberProfileResponse.builder()
                .email(member.getEmail())
                .name(member.getName())
                .birth(member.getBirth())
                .gender(member.getGender())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }
}
