package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import com.capstone.kelompok10.model.dto.MemberDto;
import com.capstone.kelompok10.model.entity.MemberEntity;

public interface MemberService {
    List<MemberEntity> getAllMember();
    List<MemberDto> getAllMemberDto();
    MemberEntity getMemberById(Long member_id);
    void createMember(MemberEntity member);
    void updateMember(Long member_id, MemberEntity member);
    void deleteMember(Long member_id);
}
