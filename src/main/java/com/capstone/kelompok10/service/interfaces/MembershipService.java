package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import com.capstone.kelompok10.model.dto.get.MembershipDtoGet;
import com.capstone.kelompok10.model.dto.post.MembershipDtoPost;
import com.capstone.kelompok10.model.entity.MembershipEntity;
import com.capstone.kelompok10.model.payload.BuyMembership;

public interface MembershipService {
    List<MembershipDtoGet> findAll();
    List<MembershipDtoGet> findAll(Boolean keyword);
    List<MembershipDtoGet> findAllDto();
    // Page<MembershipEntity> findAllPagination(int offset, int pageSize);
    // Page<MembershipEntity> findAllPaginationSort(int offset, int pageSize, String field);
    MembershipDtoGet getMembershipByIdDto(Long membershipId);
    MembershipEntity getMembershipById(Long membershipId);
    MembershipDtoGet getMembershipByUsername(String username);
    void createMembershipDto(MembershipDtoPost membershipDtoPost);
    void updateMembership(Long membershipId, MembershipDtoPost membershipDtoPost);
    void deleteMembership(Long membershipId);
    Boolean membershipExpired(Long membershipId);
    String buyMembership(BuyMembership buyMembership);
    int totalMembership();
}

