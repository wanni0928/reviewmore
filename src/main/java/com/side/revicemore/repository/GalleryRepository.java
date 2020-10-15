package com.side.revicemore.repository;

import com.side.revicemore.domain.Gallery;

import java.util.List;

public interface GalleryRepository {
    List<Gallery> findAllGalleries();
    void insertGallery(Long id, String title, String content);
    Gallery findById(Long id);
    void updateById(Long id, String title, String content);
    void deleteById(Long id);
}
