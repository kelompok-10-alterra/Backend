package com.capstone.kelompok10.service.implementation;

import java.util.ArrayList;
import java.util.List;

import com.capstone.kelompok10.model.dto.get.MembershipDtoGet;
import com.capstone.kelompok10.model.dto.post.MembershipDtoPost;
import com.capstone.kelompok10.model.entity.MemberEntity;
import com.capstone.kelompok10.model.entity.MembershipEntity;
import com.capstone.kelompok10.model.entity.UserEntity;
import com.capstone.kelompok10.repository.MembershipRepository;
import com.capstone.kelompok10.service.interfaces.MemberService;
import com.capstone.kelompok10.service.interfaces.MembershipService;
import com.capstone.kelompok10.service.interfaces.UserService;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class MembershipServiceImpl implements MembershipService {
    MembershipRepository membershipRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MemberService memberService;

    @Autowired
    public MembershipServiceImpl(MembershipRepository membershipRepository){
        this.membershipRepository = membershipRepository;
    }

    @Override
    public List<MembershipEntity> findAll() {
        log.info("Get all Membership without DTO");
        List<MembershipEntity> membership = new ArrayList<>();
        membershipRepository.findAll().forEach(membership::add);
        return membership;
    }
    
    @Override
    public Page<MembershipEntity> findAllPagination(int offset, int pageSize) {
        log.info("Get all Membership with Pagination");
        Page<MembershipEntity> membership = membershipRepository.findAll(PageRequest.of(offset, pageSize));
        return membership;
    }

    @Override
    public Page<MembershipEntity> findAllPaginationSort(int offset, int pageSize, String field){
        log.info("Get all Membership with Pagination and Sorting");
        Page<MembershipEntity> membership = membershipRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return membership;
    }

    @Override
    public List<MembershipDtoGet> findAllDto() {
        log.info("Get all Membership with DTO");
        List<MembershipEntity> memberships = membershipRepository.findAll();
        List<MembershipDtoGet> membershipDtos = new ArrayList<>();
        
        memberships.forEach(isi ->{
            MembershipDtoGet dto = new MembershipDtoGet();
            dto.setMembershipId(isi.getMembershipId());
            dto.setStatus(isi.getStatus());
            dto.setUser(isi.getUser().getName());
            dto.setMember(isi.getMember().getMemberId().toString());

            membershipDtos.add(dto);
        });
        return membershipDtos;
    }

    @Override
    public MembershipEntity getMembershipById(Long membershipId) {
        if(membershipRepository.findById(membershipId) != null){
            log.info("Membership with id {} found", membershipId);
            return membershipRepository.findById(membershipId).get();
        }else{
            log.info("Membership with id {} not found", membershipId);
            throw new IllegalStateException("Membership not Found");
        }
    }

    @Override
    public void updateMembership(Long membershipId, MembershipDtoPost membershipDtoPost) {
        if(membershipRepository.findById(membershipId) != null){
            MembershipEntity membership2 = membershipRepository.findById(membershipId).get();

            MemberEntity memberEntity = new MemberEntity();
            memberEntity.setMemberId(membershipDtoPost.getMemberId());

            UserEntity userEntity = new UserEntity();
            userEntity.setUserId(membershipDtoPost.getUserId());
            
            if(userService.userExist(membershipDtoPost.getUserId()) == true && memberService.memberExist(membershipDtoPost.getMemberId()) == true){
                membership2.setStatus(membershipDtoPost.getStatus());
                membership2.setUser(userEntity);
                membership2.setMember(memberEntity);
    
                membershipRepository.save(membership2);
                log.info("membership updated");
            }else{
                log.info("failed to update membership");
                throw new IllegalStateException("User or Member did'ny exist");
            }       
        }else{
            log.info("Membership with id {} not found", membershipId);
            throw new IllegalStateException("Membership not Found");
        }
    }

    @Override
    public void deleteMembership(Long membershipId) {
        if(membershipRepository.findById(membershipId) != null){
            log.info("Membership with id {} successfully deleted", membershipId);
            membershipRepository.deleteById(membershipId);
        }else{
            log.info("Membership with id {} not found", membershipId);
            throw new IllegalStateException("Membership not Found");
        }
        
    }

    @Override
    public void createMembershipDto(MembershipDtoPost membershipDtoPost) {
        MembershipEntity membershipEntity = new MembershipEntity();

        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(membershipDtoPost.getUserId());

        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberId(membershipDtoPost.getMemberId());

        if(userService.userExist(membershipDtoPost.getUserId()) == true && memberService.memberExist(membershipDtoPost.getMemberId()) == true){
            membershipEntity.setStatus(membershipDtoPost.getStatus());
            membershipEntity.setUser(userEntity);
            membershipEntity.setMember(memberEntity);

            membershipRepository.save(membershipEntity);
            log.info("membership created");
        }else{
            log.info("failed to create membership");
            throw new IllegalStateException("User or Member did'ny exist");
        }
    }
}
