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
    private String galleryStatus;

    public Gallery() {}

    @Override
    public String toString() {
        return "Gallery{" +
                "galleryId=" + galleryId +
                ", memberId=" + memberId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", uploadDate=" + uploadDate +
                ", galleryStatus=" + galleryStatus +
                '}';
    }
}
