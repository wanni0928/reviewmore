package com.side.revicemore.service;

import com.side.revicemore.domain.Gallery;
import com.side.revicemore.domain.GalleryStatus;
import com.side.revicemore.domain.Member;
import com.side.revicemore.domain.MemberStatus;
import com.side.revicemore.repository.GalleryRepository;
import com.side.revicemore.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional(readOnly = true)
class GalleryServiceTest {
    @Autowired
    GalleryService galleryService;
    @Autowired
    GalleryRepository galleryRepository;
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @DisplayName("한 회원이 게시글을 입력했다. 게시자가 지은 제목이 일치해야하고, ACTIVATED 상태여야 한다.")
    void write() {
        // given
        Member member = new Member();
        member.setMemberAccount("choi");
        member.setMemberPassword("910404@");
        Long joinId = memberService.join(member);

        Gallery gallery = new Gallery();
        gallery.setTitle("제목");
        gallery.setContent("내용");

        // when
        Long savedId = galleryService.write(joinId, gallery.getTitle(), gallery.getContent());

        // then
        assertThat(Objects.requireNonNull(galleryRepository.findById(savedId).orElse(null)).getTitle()).isEqualTo("제목");
        assertThat(Objects.requireNonNull(galleryRepository.findById(savedId).orElse(null)).getGalleryStatus()).isEqualTo(GalleryStatus.ACTIVATED.name());
    }

    @Test
    @Transactional
    @DisplayName("한 회원이 게시글 1개를 썼다. 해당 게시글의 내용이 같고 ACTIVATED 상태여야 한다.")
    void findOne() {
        // given
        Member member = new Member();
        member.setMemberAccount("choi");
        member.setMemberPassword("910404@");
        member.setMemberStatus(MemberStatus.MEMBER.name());
        member.setMemberDate(LocalDateTime.now());
        Long joinId = memberService.join(member);

        Gallery gallery = new Gallery();
        gallery.setTitle("제목");
        gallery.setContent("내용");

        // when
        Long savedId = galleryService.write(joinId, gallery.getTitle(), gallery.getContent());

        // then
        assertThat(Objects.requireNonNull(galleryService.findOne(savedId)).getTitle()).isEqualTo("제목");
        assertThat(Objects.requireNonNull(galleryService.findOne(savedId)).getContent()).isEqualTo("내용");
        assertThat(Objects.requireNonNull(galleryService.findOne(savedId)).getGalleryStatus()).isEqualTo(GalleryStatus.ACTIVATED.name());
    }

    @Test
    @Transactional
    @DisplayName("한 회원이 게시글 3개를 썼다. 게시글의 길이는 3개여야 한다.")
    void findAll() {
        // given
        Member member = new Member();
        member.setMemberAccount("choi");
        member.setMemberPassword("910404@");
        member.setMemberStatus(MemberStatus.MEMBER.name());
        member.setMemberDate(LocalDateTime.now());
        Long joinId = memberService.join(member);

        Gallery gallery = new Gallery();
        gallery.setTitle("제목");
        gallery.setContent("내용");

        // when
        galleryService.write(joinId, gallery.getTitle(), gallery.getContent());
        galleryService.write(joinId, gallery.getTitle(), gallery.getContent());
        galleryService.write(joinId, gallery.getTitle(), gallery.getContent());

        List<Gallery> galleries = galleryService.findGalleries();

        // then
        assertThat(galleries.size()).isEqualTo(3);
    }

    @Test
    @Transactional
    @DisplayName("한 회원이 게시글을 쓰고 수정을 했다. 수정된 내용이 같아야 한다.")
    void update(){
        // given
        Member member = new Member();
        member.setMemberAccount("choi");
        member.setMemberPassword("910404@");
        member.setMemberStatus(MemberStatus.MEMBER.name());
        member.setMemberDate(LocalDateTime.now());
        Long joinId = memberService.join(member);

        Gallery gallery = new Gallery();
        gallery.setTitle("제목");
        gallery.setContent("내용");

        // when
        Long savedId = galleryService.write(joinId, gallery.getTitle(), gallery.getContent());
        galleryService.updateGallery(savedId, "수정된 제목", "수정된 내용");

        // then
        assertThat(galleryService.findOne(savedId).getTitle()).isEqualTo("수정된 제목");
    }

    @Test
    @Transactional
    @DisplayName("한 회원이 게시글을 쓰고 삭제를 했다. 삭제된 게시글의 상태가 DEAVTOVATED 여야 한다.")
    void delete() {
        // given
        Member member = new Member();
        member.setMemberAccount("choi");
        member.setMemberPassword("910404@");
        member.setMemberStatus(MemberStatus.MEMBER.name());
        member.setMemberDate(LocalDateTime.now());
        Long joinId = memberService.join(member);

        Gallery gallery = new Gallery();
        gallery.setTitle("제목");
        gallery.setContent("내용");

        // when
        Long savedId = galleryService.write(joinId, gallery.getTitle(), gallery.getContent());
        int affectedRowNum = galleryService.deleteGallery(savedId);

        // then
        assertThat(affectedRowNum).isEqualTo(1);
        assertThat(galleryService.findOne(savedId).getGalleryStatus()).isEqualTo(GalleryStatus.DEACTIVATED.name());
    }
}