package com.side.revicemore.service;

import com.side.revicemore.domain.Member;
import com.side.revicemore.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback
class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void join(){
        // given
        Member member = new Member();
        member.setMemberAccount("choi");
        member.setMemberPassword("910404@");

        // when
        Long savedId = memberService.join(member);
        System.out.println(memberRepository.findById(savedId));

        // then
        assertThat(member.getMemberId()).isEqualTo(Objects.requireNonNull(memberRepository.findById(savedId).orElse(null)).getMemberId());
    }

    @Test
    @DisplayName("두 회원이 회원가입을 했는데, 한 명은 회원탈퇴를 했다. 두 회원의 AccountStatus 값은 달라야 한다.")
    void secession(){
        //given
        Member member1 = new Member();
        member1.setMemberAccount("choi");
        member1.setMemberPassword("910404");

        Member member2 = new Member();
        member2.setMemberAccount("wanni");
        member2.setMemberPassword("160928");


        //when
        Long savedId1 = memberService.join(member1);
        Long savedId2 = memberService.join(member2);

        int affectedRowNum = memberService.secession(savedId1);

        //then
        assertThat(affectedRowNum).isSameAs(1);
        assertThat(memberService.findOne(savedId1).getMemberStatus()).isNotEqualTo(memberService.findOne(savedId2));
    }

    @Test
    @DisplayName("한 회원이 비밀번호를 바꿨다.")
    void update() {
        //given
        Member member1 = new Member();
        member1.setMemberPassword("910404@");

        //when
        Long savedId = memberService.join(member1);
        int affectedRowNum = memberService.updateMember(savedId);

        //then
        assertThat(affectedRowNum).isSameAs(1);
        assertThat(memberService.findOne(savedId).getMemberPassword()).isEqualTo("910404@");


    }

    @Test
    @Transactional
    void findAll() {
        // given
        Member member = new Member();
        member.setMemberAccount("choi");
        member.setMemberPassword("910404@");
        memberService.join(member);

        Member member1 = new Member();
        member1.setMemberAccount("wanni");
        member1.setMemberPassword("160426@");
        memberService.join(member1);

        Member member2 = new Member();
        member2.setMemberAccount("curry");
        member2.setMemberPassword("161025@");
        memberService.join(member2);

        // when
        List<Member> members = memberService.findMembers();

        // then
        assertThat(members.size()).isEqualTo(3);
    }
}