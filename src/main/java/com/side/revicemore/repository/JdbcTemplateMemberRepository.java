package com.side.revicemore.repository;

import com.side.revicemore.domain.Member;
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
public class JdbcTemplateMemberRepository implements MemberRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("members").usingGeneratedKeyColumns("memberId");

        Map<String, Object> paraneters = new HashMap<>();
        paraneters.put("memberAccount", member.getMemberAccount());
        paraneters.put("memberPassword", member.getMemberPassword());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(paraneters));
        member.setMemberId(key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return jdbcTemplate.query("select * from members where memberId = ?", memberRowMapper(), id).stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        return jdbcTemplate.query("select * from members where memberAccount = ?", memberRowMapper(), name).stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from members", memberRowMapper());
    }

    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setMemberId(rs.getLong("memberId"));
            member.setMemberAccount("memberAccount");
            member.setMemberPassword("memberPassword");
            return member;
        };
    }
}
