package com.side.revicemore.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Gallery {
    private Long galleryId; // pk
    private Long memberId; // fk
    private String title;
    private String content;
    private String author;
    private LocalDateTime uploadDate;

    protected Gallery() {}

    public Gallery(Long galleryId, Long memberId, String title, String content, String author, LocalDateTime uploadDate) {
        this.galleryId = galleryId;
        this.memberId = memberId;
        this.title = title;
        this.content = content;
        this.author = author;
        this.uploadDate = uploadDate;
    }
}
