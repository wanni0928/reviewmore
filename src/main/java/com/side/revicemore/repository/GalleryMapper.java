package com.side.revicemore.repository;

import com.side.revicemore.domain.Gallery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

public interface GalleryRepository {
    Gallery save(Gallery gallery);
    int update(Gallery gallery);
    Optional<Gallery> findById(Long id);
    Optional<Gallery> findByName(String name);
    List<Gallery> findAll();
}
