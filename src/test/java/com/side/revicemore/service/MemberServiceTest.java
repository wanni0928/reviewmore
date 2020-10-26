package com.side.revicemore.service;

import com.side.revicemore.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
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

    @Test
    void findMemberJoinTime() {
        Member member1 = memberService.findOne(1L);
        Member member2 = memberService.findOne(2L);
        Member member3 = memberService.findOne(3L);

        String member1JoinDateTime = LocalDateTime
                .of(member1.getMemberDate().toLocalDate(), member1.getMemberDate().toLocalTime())
                .format(DateTimeFormatter.BASIC_ISO_DATE);

        String member2JoinDateTime = LocalDateTime
                .of(member2.getMemberDate().toLocalDate(), member2.getMemberDate().toLocalTime())
                .format(DateTimeFormatter.ISO_DATE_TIME);

        String member3JoinDateTime = LocalDateTime
                .of(member3.getMemberDate().toLocalDate(), member3.getMemberDate().toLocalTime())
                .format(DateTimeFormatter.ISO_DATE_TIME);

        System.out.println(member2.getMemberDate());

        System.out.println(member1JoinDateTime);
        System.out.println(member2JoinDateTime);

        assertThat(member1JoinDateTime).isEqualTo("20201015");
        assertThat(member2JoinDateTime).isEqualTo("2020-10-15T15:35:04");
        assertThat(member3JoinDateTime).isEqualTo("2020-10-15T15:35:24");
    }

    @Test
    void insert(){
        String memberAccount = "member" + (memberService.findMembers().size() + 1);
        String password = "1234";

        memberService.insert(memberAccount, password);

        assertThat(memberAccount).isEqualTo("member"+memberService.findMembers().size());
    }

    @Test
    void findGalleryByMemberId() {
        Member member = memberService.findOne(1L);
        Long memberId = member.getMemberId();
        HashMap<String, Object> galleryByMemberId = memberService.findGalleryByMemberId(memberId);



        System.out.println(galleryByMemberId.keySet());
        System.out.println(galleryByMemberId.values());

    }

    @Test
    void updateMember() {
        memberService.updateMemberById(1L, "newId", "newPassword");
    }
}