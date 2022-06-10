package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import com.capstone.kelompok10.model.dto.MembershipDto;
import com.capstone.kelompok10.model.entity.MembershipEntity;

public interface MembershipService {
    List<MembershipEntity> getAllMembership();
    List<MembershipDto> getAllMembershipDto();
    MembershipEntity getMembershipById(Long membership_id);
    void createMembership(MembershipEntity membership);
    void updateMembership(Long membership_id, MembershipEntity membership);
    void deleteMembership(Long membership_id);
}

