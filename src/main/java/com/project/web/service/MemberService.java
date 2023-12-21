package com.project.web.service;

import com.project.web.dto.request.member.MemberRequest;
import com.project.web.dto.response.member.MemberResponse;
import com.project.web.entity.Member;
import com.project.web.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public List<MemberResponse> findAll() {

        List<Member> memberList = memberRepository.findAll();
        List<MemberResponse> memberResponseList = new ArrayList<>();

        for (Member member: memberList) {
            memberResponseList.add(MemberResponse.toDTO(member));
        }

        return memberResponseList;
    }

    public MemberResponse findById(Long id) throws Exception {

        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new Exception("사용자 정보를 찾을 수 없습니다."));
        MemberResponse response = MemberResponse.toDTO(member);

        return response;
    }

    public MemberResponse findByEmail(String email) throws Exception {

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("사용자 정보를 찾을 수 없습니다."));
        MemberResponse response = MemberResponse.toDTO(member);

        return response;
    }

    public boolean updateById(Long id, MemberRequest request) throws Exception {

        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new Exception("사용자 정보를 찾을 수 없습니다."));
        member.update(passwordEncoder.encode(request.getPassword()),
                request.getName(),
                request.getBirth(),
                request.getGender(),
                request.getPhoneNumber());
        memberRepository.save(member);

        return true;
    }

    public boolean deleteById(Long id) {

        memberRepository.deleteById(id);

        return true;
    }
}
