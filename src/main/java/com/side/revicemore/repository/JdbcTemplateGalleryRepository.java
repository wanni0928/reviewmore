package com.side.revicemore.repository;

import com.side.revicemore.domain.Gallery;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcTemplateGalleryRepository implements GalleryRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Gallery save(Gallery gallery) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("gallery").usingGeneratedKeyColumns("galleryId");

        Map<String, Object> parenters = new HashMap<>();
        parenters.put("memberId", gallery.getMemberId());
        parenters.put("title", gallery.getTitle());
        parenters.put("content", gallery.getContent());
        parenters.put("author", gallery.getAuthor());
        parenters.put("uploadDate", gallery.getUploadDate());
        parenters.put("galleryStatus", gallery.getGalleryStatus());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parenters));
        gallery.setGalleryId(key.longValue());

        return gallery;
    }

    @Override
    public int update(Gallery gallery) {
        return jdbcTemplate.update("update gallery set title = ?, content = ?, author = ?, galleryStatus = ? where galleryId = ?", gallery.getTitle(), gallery.getContent(), gallery.getAuthor(), gallery.getGalleryStatus(), gallery.getGalleryId());
    }

    @Override
    public Optional<Gallery> findById(Long id) {
        return jdbcTemplate.query("select * from gallery where galleryId = ?", galleryRowMapper(), id).stream().findAny();
    }

    @Override
    public Optional<Gallery> findByName(String name) {
        return jdbcTemplate.query("select * from gallery where title = ?", galleryRowMapper(), name).stream().findAny();
    }

    @Override
    public List<Gallery> findAll() {
        return jdbcTemplate.query("select * from gallery", galleryRowMapper());
    }

    private RowMapper<Gallery> galleryRowMapper() {
        return (rs, rowNum) -> {
            Gallery gallery = new Gallery();
            gallery.setGalleryId(rs.getLong("galleryId"));
            gallery.setMemberId(rs.getLong("memberId"));
            gallery.setTitle(rs.getString("title"));
            gallery.setContent(rs.getString("content"));
            gallery.setAuthor(rs.getString("author"));
            gallery.setUploadDate(rs.getObject("uploadDate", LocalDateTime.class));
            gallery.setGalleryStatus(rs.getString("galleryStatus"));
            return gallery;
        };
    }
}
