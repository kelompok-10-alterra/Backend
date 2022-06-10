package com.capstone.kelompok10.service.implementation;

import java.util.ArrayList;
import java.util.List;

import com.capstone.kelompok10.model.dto.get.MembershipDtoGet;
import com.capstone.kelompok10.model.dto.post.MembershipDtoPost;
import com.capstone.kelompok10.model.entity.MemberEntity;
import com.capstone.kelompok10.model.entity.MembershipEntity;
import com.capstone.kelompok10.model.entity.UserEntity;
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
    public List<MembershipDtoGet> getAllMembershipDto() {
        List<MembershipEntity> memberships = membershipRepository.findAll();
        List<MembershipDtoGet> membershipDtos = new ArrayList<>();
        
        memberships.forEach(isi ->{
            MembershipDtoGet dto = new MembershipDtoGet();
            dto.setMembership_id(isi.getMembership_id());
            dto.setStatus(isi.getStatus());
            dto.setUser(isi.getUser().getName());
            dto.setMember(isi.getMember().getMember_id().toString());

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
        membership2.setStatus(membership.getStatus());
        membership2.setUser(membership.getUser());
        membership2.setMember(membership.getMember());
        membershipRepository.save(membership2);
    }

    @Override
    public void deleteMembership(Long membership_id) {
        membershipRepository.deleteById(membership_id);
        
    }

    @Override
    public void createMembershipDto(MembershipDtoPost membershipDtoPost) {
        MembershipEntity membershipEntity = new MembershipEntity();

        UserEntity userEntity = new UserEntity();
        userEntity.setUser_id(membershipDtoPost.getUser_id());

        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMember_id(membershipDtoPost.getMember_id());

        membershipEntity.setStatus(membershipDtoPost.getStatus());
        membershipEntity.setUser(userEntity);
        membershipEntity.setMember(memberEntity);

        membershipRepository.save(membershipEntity);
    }
}
