package com.side.revicemore.service;

import com.side.revicemore.domain.Gallery;
import com.side.revicemore.repository.GalleryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GalleryService {
    private final GalleryRepository galleryRepository;

    public GalleryService(GalleryRepository galleryRepository) {
        this.galleryRepository = galleryRepository;
    }


    public List<Gallery> findAllGalleries() {
        return galleryRepository.findAllGalleries();
    }

    public void insertGallery(Long memberId, String title, String content) {
        galleryRepository.insertGallery(memberId, title, content);
    }

    public Gallery findById(Long galleryId) {
        return galleryRepository.findById(galleryId);
    }

    public void deleteById(Long id){
        galleryRepository.deleteById(id);
    }

    public void updateById(Long id, String title, String content) {
        galleryRepository.updateById(id, title, content);
    }
}
