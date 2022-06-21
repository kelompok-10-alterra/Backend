package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;

import com.capstone.kelompok10.model.dto.get.MembershipDtoGet;
import com.capstone.kelompok10.model.dto.post.MembershipDtoPost;
import com.capstone.kelompok10.model.entity.MembershipEntity;

public interface MembershipService {
    List<MembershipEntity> findAll();
    List<MembershipDtoGet> findAllDto();
    Page<MembershipEntity> findAllPagination(int offset, int pageSize);
    Page<MembershipEntity> findAllPaginationSort(int offset, int pageSize, String field);
    MembershipEntity getMembershipById(Long membershipId);
    void createMembershipDto(MembershipDtoPost membershipDtoPost);
    void updateMembership(Long membershipId, MembershipDtoPost membershipDtoPost);
    void deleteMembership(Long membershipId);
}

