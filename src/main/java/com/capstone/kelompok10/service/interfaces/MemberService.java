package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import com.capstone.kelompok10.model.dto.get.MemberDtoGet;
import com.capstone.kelompok10.model.dto.post.MemberDtoPost;
import com.capstone.kelompok10.model.entity.MemberEntity;

public interface MemberService {
    List<MemberEntity> getAllMember();
    List<MemberDtoGet> getAllMemberDto();
    MemberEntity getMemberById(Long member_id);
    void createMember(MemberEntity member);
    void createMemberDto(MemberDtoPost memberDtoPost);
    void updateMember(Long member_id, MemberDtoPost memberDtoPost);
    void deleteMember(Long member_id);
}
