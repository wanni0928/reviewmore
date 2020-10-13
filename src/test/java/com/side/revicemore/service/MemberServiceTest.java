package com.side.revicemore.service;

import com.side.revicemore.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;

    @Test
    void findAll() {
        List<Member> members = memberService.findMembers();
        assertThat(members.size()).isEqualTo(3);
    }

    @Test
    void findById() {
        Member member1 = memberService.findOne(1L);
        Member member2 = memberService.findOne(2L);
        Member member3 = memberService.findOne(3L);

        assertThat(member1.getMemberAccount()).isEqualTo("member1");
        assertThat(member2.getMemberAccount()).isEqualTo("member2");
        assertThat(member3.getMemberAccount()).isEqualTo("member3");

    }
}