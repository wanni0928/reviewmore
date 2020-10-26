package com.side.revicemore.repository;

import com.side.revicemore.domain.Gallery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GalleryMapper {
    List<Gallery> findAllGalleries();
    void insertGallery(Long id, String title, String content);
    Gallery findById(Long id);
    void updateById(Long id, String title, String content);
    void deleteById(Long id);
}
