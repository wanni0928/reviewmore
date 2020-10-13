package com.side.revicemore.repository;

import com.side.revicemore.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository {
    List<Member> selectMembers();
    Member findMemberById(Long id);
}
