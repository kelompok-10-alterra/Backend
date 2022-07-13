package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import com.capstone.kelompok10.model.dto.get.MemberDtoGet;
import com.capstone.kelompok10.model.dto.post.MemberDtoPost;
import com.capstone.kelompok10.model.entity.MemberEntity;

public interface MemberService {
    List<MemberDtoGet> findAll();
    // List<MemberDtoGet> findAllDto();
    // Page<MemberEntity> findAllPagination(int offset, int pageSize);
    // Page<MemberEntity> findAllPaginationSort(int offset, int pageSize, String field);
    MemberEntity getMemberById(Long memberId);
    void createMemberDto(MemberDtoPost memberDtoPost);
    void updateMember(Long memberId, MemberDtoPost memberDtoPost);
    void deleteMember(Long memberId);
    void createMember(MemberEntity memberEntity);

    //Verify Method
    Boolean memberExist(Long memberId);
}
