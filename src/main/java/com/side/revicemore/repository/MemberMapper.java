package com.side.revicemore.repository;

import com.side.revicemore.domain.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface MemberMapper {
    void updateMemberById(Long memberId, String memberAccount, String memberPassword);
    List<Member> selectMembers();
    Member findMemberById(Long id);
    void insert(String account, String password);
    HashMap<String, Object> findGalleryByMemberId(Long memberId);
}
