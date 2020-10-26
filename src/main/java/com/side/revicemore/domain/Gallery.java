package com.side.revicemore.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class Gallery {
    private Long galleryId; // pk
    private Long memberId; // fk
    private String title;
    private String content;
    private String author;
    private LocalDateTime uploadDate;

    public Gallery() {}

    public Gallery(Long galleryId, Long memberId, String title, String content, String author, LocalDateTime uploadDate) {
        this.galleryId = galleryId;
        this.memberId = memberId;
        this.title = title;
        this.content = content;
        this.author = author;
        this.uploadDate = uploadDate;
    }
}
