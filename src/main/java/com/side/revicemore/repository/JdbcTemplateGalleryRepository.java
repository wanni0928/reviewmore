package com.side.revicemore.repository;

import com.side.revicemore.domain.Gallery;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

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

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parenters));
        gallery.setGalleryId(key.longValue());

        return gallery;
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
            gallery.setMemberId(rs.getLong("memberId"));
            gallery.setTitle("title");
            gallery.setContent("content");
            gallery.setAuthor("author");
            return gallery;
        };
    }
}
