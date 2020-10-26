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
class GalleryServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    GalleryService galleryService;

    @Test
    void insertGallery() {
        Member member = memberService.findOne(1L);
        int beforeInsert = galleryService.findAllGalleries().size();
        galleryService.insertGallery(member.getMemberId(), "테스트 제목", "테스트 내용");
        assertThat(galleryService.findAllGalleries().size()).isEqualTo(beforeInsert + 1);
    }

    @Test
    void selectMembers() {
        List<Gallery> galleries = galleryService.findAllGalleries();
        System.out.println(galleries);
    }

    @Test
    void findGalleryById() {
        Gallery gallery = galleryService.findById(5L);
        assertThat(gallery.getGalleryId()).isEqualTo(5L);
    }

    @Test
    void updateById() {
        galleryService.updateById(2L, "testesteste", "ttttttttttttteeeeeeeeeeeesssssssst");
    }

    @Test
    void deleteGallery() {
        galleryService.deleteById(7L);
    }
}