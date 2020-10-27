package com.side.revicemore.service;

import com.side.revicemore.domain.Gallery;
import com.side.revicemore.domain.GalleryStatus;
import com.side.revicemore.domain.Member;
import com.side.revicemore.repository.GalleryRepository;
import com.side.revicemore.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GalleryService {
    private final GalleryRepository galleryRepository;
    private final MemberRepository memberRepository;

    /**
     * 게시글 작성
     */
    @Transactional
    public Long write(Long memberId, String title, String content) {
        // 엔티티 조회
        Member member = memberRepository.findById(memberId).orElse(null);

        // 게시판 내용 생성
        Gallery gallery = new Gallery();
        gallery.setMemberId(member.getMemberId());
        gallery.setAuthor(member.getMemberAccount());
        gallery.setContent(content);
        gallery.setTitle(title);
        gallery.setGalleryStatus(GalleryStatus.ACTIVATED.name());
        gallery.setUploadDate(LocalDateTime.now());

        //게시판 저장
        galleryRepository.save(gallery);

        return gallery.getGalleryId();
    }

    /**
     * 게시판 삭제
     */
    @Transactional
    public int deleteGallery(Long galleryId){
        Gallery gallery = Objects.requireNonNull(galleryRepository.findById(galleryId).orElse(null));
        gallery.setGalleryStatus(GalleryStatus.DEACTIVATED.name());
        System.out.println(gallery.getGalleryStatus());
        return galleryRepository.update(gallery);
    }

    /**
     * 게시판 수정
     */
    @Transactional
    public int updateGallery(Long galleryId, String title, String content) {
        Gallery gallery = galleryRepository.findById(galleryId).orElse(null);
        gallery.setTitle(title);
        gallery.setContent(content);
        return galleryRepository.update(gallery);
    }

    /**
     * 게시판 목록
     */
    public List<Gallery> findGalleries() {
        return galleryRepository.findAll();
    }

    public Gallery findOne(Long id) {
        return galleryRepository.findById(id).orElse(null);
    }
}
