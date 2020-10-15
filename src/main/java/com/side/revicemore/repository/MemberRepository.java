package com.side.revicemore.repository;

import com.side.revicemore.domain.Member;

import java.util.HashMap;
import java.util.List;

public interface MemberRepository {
    List<Member> selectMembers();
    Member findMemberById(Long id);
    void insert(String account, String password);
    HashMap<String, Object> findGalleryByMemberId(Long memberId);
}
