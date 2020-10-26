package com.side.revicemore.service;

import com.side.revicemore.domain.Member;
import com.side.revicemore.repository.MemberMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MemberService {
    private final MemberMapper memberRepository;

    public MemberService(MemberMapper memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> findMembers(){
        return memberRepository.selectMembers();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findMemberById(memberId);
    }

    public void insert(String account, String password){
        memberRepository.insert(account, password);
    }

    public void updateMemberById(Long id, String account, String password) {
        memberRepository.updateMemberById(id, account, password);
    }

    public HashMap<String, Object> findGalleryByMemberId(Long memberId){
        return memberRepository.findGalleryByMemberId(memberId);
    }
}
