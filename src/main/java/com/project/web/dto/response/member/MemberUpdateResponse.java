package com.project.web.dto.response.member;

import com.project.web.entity.Member;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MemberUpdateResponse {

    private Long id;
    private String email;
    private String name;
    private String birth;
    private String gender;
    private String phoneNumber;
    private LocalDateTime updatedAt;

    public static MemberUpdateResponse toDTO(Member member) {
        return MemberUpdateResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .birth(member.getBirth())
                .gender(member.getGender())
                .phoneNumber(member.getPhoneNumber())
                .updatedAt(member.getUpdatedAt())
                .build();
    }
}
