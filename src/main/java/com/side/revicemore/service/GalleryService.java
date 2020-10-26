package com.side.revicemore.service;

import com.side.revicemore.domain.Gallery;
import com.side.revicemore.repository.GalleryMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GalleryService {
    private final GalleryMapper galleryMapper;

    public GalleryService(GalleryMapper galleryMapper) {
        this.galleryMapper = galleryMapper;
    }


    public List<Gallery> findAllGalleries() {
        return galleryMapper.findAllGalleries();
    }

    public void insertGallery(Long memberId, String title, String content) {
        galleryMapper.insertGallery(memberId, title, content);
    }

    public Gallery findById(Long galleryId) {
        return galleryMapper.findById(galleryId);
    }

    public void deleteById(Long id){
        galleryMapper.deleteById(id);
    }

    public void updateById(Long id, String title, String content) {
        galleryMapper.updateById(id, title, content);
    }
}
