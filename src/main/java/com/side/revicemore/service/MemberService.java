package com.side.revicemore.service;

import com.side.revicemore.domain.Member;
import com.side.revicemore.domain.MemberStatus;
import com.side.revicemore.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    @Transactional
    public Long join(Member member) {
        // 같은 이름이 있는지 확인
        validateDuplicateMember(member);

        member.setMemberDate(LocalDateTime.now());
        member.setMemberStatus(MemberStatus.MEMBER.name());
        memberRepository.save(member);

        return member.getMemberId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getMemberAccount())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public int updateMember(Long id) {
        Member member = findOne(id);
        member.setMemberAccount(member.getMemberAccount());
        member.setMemberPassword(member.getMemberPassword());
        // 회원 수정
        return memberRepository.update(member);
    }

    public int secession(Long id) {
        Member member = findOne(id);
        member.setMemberStatus(MemberStatus.NON_MEMBER.name());
        // 회원 탈퇴
        return memberRepository.update(member);
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
