package com.side.revicemore.service;

import com.side.revicemore.domain.Member;
import com.side.revicemore.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberRepository;

    /**
     * 회원가입
     */
    @Transactional
    public Long join(Member member) {
        // 같은 이름이 있는지 확인
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getMemberId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getMemberAccount())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public void updateMember(Long memberId, String memberAccount, String memberPassword) {
        Member member = findOne(memberId);
        member.setMemberAccount(memberAccount);
        member.setMemberPassword(memberPassword);
    }

    /**
     *  전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

}
