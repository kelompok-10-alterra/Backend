package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import com.capstone.kelompok10.model.dto.get.MembershipDtoGet;
import com.capstone.kelompok10.model.dto.post.MembershipDtoPost;
import com.capstone.kelompok10.model.entity.MembershipEntity;

public interface MembershipService {
    List<MembershipEntity> getAllMembership();
    List<MembershipDtoGet> getAllMembershipDto();
    MembershipEntity getMembershipById(Long membership_id);
    void createMembership(MembershipEntity membership);
    void createMembershipDto(MembershipDtoPost membershipDtoPost);
    void updateMembership(Long membership_id, MembershipEntity membership);
    void deleteMembership(Long membership_id);
}

