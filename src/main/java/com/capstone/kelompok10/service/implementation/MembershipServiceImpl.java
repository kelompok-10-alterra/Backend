package com.capstone.kelompok10.service.implementation;

import java.util.ArrayList;
import java.util.List;

import com.capstone.kelompok10.model.dto.MembershipDto;
import com.capstone.kelompok10.model.entity.MembershipEntity;
import com.capstone.kelompok10.repository.MembershipRepository;
import com.capstone.kelompok10.service.interfaces.MembershipService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembershipServiceImpl implements MembershipService {
    MembershipRepository membershipRepository;

    @Autowired
    public MembershipServiceImpl(MembershipRepository membershipRepository){
        this.membershipRepository = membershipRepository;
    }

    @Override
    public List<MembershipEntity> getAllMembership() {
        List<MembershipEntity> memberships = new ArrayList<>();
        membershipRepository.findAll().forEach(memberships::add);
        return memberships;
    }

    @Override
    public List<MembershipDto> getAllMembershipDto() {
        List<MembershipEntity> memberships = membershipRepository.findAll();
        List<MembershipDto> membershipDtos = new ArrayList<>();
        
        memberships.forEach(isi ->{
            MembershipDto dto = new MembershipDto();
            dto.setMembership_id(isi.getMembership_id());
            dto.setName(isi.getName());
            dto.setPrice(isi.getPrice());

            membershipDtos.add(dto);
        });
        return membershipDtos;
    }

    @Override
    public MembershipEntity getMembershipById(Long membership_id) {
        return membershipRepository.findById(membership_id).get();
    }

    @Override
    public void createMembership(MembershipEntity membership) {
        membershipRepository.save(membership);
    }

    @Override
    public void updateMembership(Long membership_id, MembershipEntity membership) {
        MembershipEntity membership2 = membershipRepository.findById(membership_id).get();
        System.out.println(membership2.toString());
        membership2.setName(membership.getName());
        membership2.setPrice(membership.getPrice());
        membershipRepository.save(membership2);
    }

    @Override
    public void deleteMembership(Long membership_id) {
        membershipRepository.deleteById(membership_id);
        
    }
}
