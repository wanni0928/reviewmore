package com.side.revicemore.domain;

import com.side.revicemore.repository.MemberMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest

class MemberMapperTest {
    @Autowired
    private MemberMapper memberRepository;


    @Test
    void test() throws Exception {
        List<Member> members = new ArrayList<>();
        members = memberRepository.selectMembers();
        assertThat(members.size()).isEqualTo(3);
    }

    @Test
    void test2() throws Exception {
        Member member = memberRepository.findMemberById(1L);
        assertThat(member.getMemberAccount()).isEqualTo("member1");
    }
}