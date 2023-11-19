package com.project.web.service;

import com.project.web.dto.request.MemberRequest;
import com.project.web.dto.response.MemberResponse;
import com.project.web.entity.Member;
import com.project.web.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public boolean save(MemberRequest request) {

        Member member = request.toEntity(request);
        memberRepository.save(member);

        return true;
    }

    public List<MemberResponse> findAll() {

        List<Member> memberList = memberRepository.findAll();
        List<MemberResponse> memberResponseList = new ArrayList<>();

        for (Member member: memberList) {
            memberResponseList.add(MemberResponse.toDTO(member));
        }

        return memberResponseList;
    }

    public MemberResponse findById(Long id) {

        Member member = memberRepository.findById(id).get();
        MemberResponse response = MemberResponse.toDTO(member);

        return response;
    }

    public MemberResponse findByEmail(String email) throws Exception {

        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new Exception("계정 정보를 찾을 수 없습니다."));
        MemberResponse response = MemberResponse.toDTO(member);

        return response;
    }

    public boolean updateById(Long id, MemberRequest request) {

        Member member = memberRepository.findById(id).get();
        member.updateMember(request.getPassword(), request.getName());
        memberRepository.save(member);

        return true;
    }

    public boolean deleteById(Long id) {

        memberRepository.deleteById(id);

        return true;
    }
}
