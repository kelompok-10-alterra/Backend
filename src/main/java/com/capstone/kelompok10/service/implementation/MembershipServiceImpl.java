package com.capstone.kelompok10.service.implementation;

import java.time.LocalDateTime;
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

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@AllArgsConstructor
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
    public List<MembershipDtoGet> findAll() {
        List<MembershipEntity> memberships = membershipRepository.findAll();
        List<MembershipDtoGet> membershipDtos = new ArrayList<>();
        
        memberships.forEach(isi ->{
            MembershipDtoGet dto = new MembershipDtoGet();
            dto.setMembershipId(isi.getMembershipId());
            dto.setStatus(isi.getStatus());
            dto.setCreatedAt(isi.getCreatedAt().toString());
            dto.setUpdatedAt(isi.getUpdated_at().toString());
            dto.setUserId(isi.getUser().getUserId());
            dto.setUsername(isi.getUser().getUsername());
            dto.setEmail(isi.getUser().getEmail());
            dto.setName(isi.getUser().getName());
            if(isi.getMember() == null){
                dto.setMemberId(null);
                dto.setMemberName("No Membership");
                dto.setMemberPeriod("No Membership");
            }else{
                dto.setMemberId(isi.getMember().getMemberId());
                dto.setMemberName(isi.getMember().getName());
                dto.setMemberPeriod(isi.getMember().getPeriod());
            }
            dto.setContact(isi.getUser().getPhone());
            dto.setAddress(isi.getUser().getAddress());
            dto.setExpiredAt(isi.getExpiredAt());

            membershipDtos.add(dto);
        });
        return membershipDtos;
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
            dto.setCreatedAt(isi.getCreatedAt().toString());
            dto.setUpdatedAt(isi.getUpdated_at().toString());
            dto.setUserId(isi.getUser().getUserId());
            dto.setUsername(isi.getUser().getUsername());
            dto.setEmail(isi.getUser().getEmail());
            dto.setName(isi.getUser().getName());
            if(isi.getMember() == null){
                dto.setMemberId(null);
                dto.setMemberName("No Membership");
                dto.setMemberPeriod("No Membership");
            }else{
                dto.setMemberId(isi.getMember().getMemberId());
                dto.setMemberName(isi.getMember().getName());
                dto.setMemberPeriod(isi.getMember().getPeriod());
            }
            dto.setContact(isi.getUser().getPhone());
            dto.setAddress(isi.getUser().getAddress());
            dto.setExpiredAt(isi.getExpiredAt());

            membershipDtos.add(dto);
        });
        return membershipDtos;
    }

    @Override
    public MembershipDtoGet getMembershipByIdDto(Long membershipId) {
        if(membershipRepository.findById(membershipId) != null){
            log.info("Membership with id {} found", membershipId);
            MembershipEntity isi = membershipRepository.findById(membershipId).get();
            MembershipDtoGet dto = new MembershipDtoGet();
            dto.setMembershipId(isi.getMembershipId());
            dto.setStatus(isi.getStatus());
            dto.setCreatedAt(isi.getCreatedAt().toString());
            dto.setUpdatedAt(isi.getUpdated_at().toString());
            dto.setUserId(isi.getUser().getUserId());
            dto.setUsername(isi.getUser().getUsername());
            dto.setEmail(isi.getUser().getEmail());
            dto.setName(isi.getUser().getName());
            if(isi.getMember() == null){
                dto.setMemberId(null);
                dto.setMemberName("No Membership");
                dto.setMemberPeriod("No Membership");
            }else{
                dto.setMemberId(isi.getMember().getMemberId());
                dto.setMemberName(isi.getMember().getName());
                dto.setMemberPeriod(isi.getMember().getPeriod());
            }
            dto.setContact(isi.getUser().getPhone());
            dto.setExpiredAt(isi.getExpiredAt());
            
            return dto;
        }else{
            log.info("Membership with id {} not found", membershipId);
            throw new IllegalStateException("Membership not Found");
        }
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
            
            if(userService.userExist(membershipDtoPost.getUserId()) == true && memberService.memberExist(membershipDtoPost.getMemberId()) == true && userService.nativeUser(membershipDtoPost.getUserId()) == false){
                membership2.setUser(userEntity);
                membership2.setMember(memberEntity);
                if(membershipDtoPost.getMemberId() == 1){
                    membership2.setExpiredAt(LocalDateTime.now().plusMonths(1));
                }if(membershipDtoPost.getMemberId() == 2){
                    membership2.setExpiredAt(LocalDateTime.now().plusMonths(3));
                }if(membershipDtoPost.getMemberId() == 3){
                    membership2.setExpiredAt(LocalDateTime.now().plusMonths(6));
                }
                membershipRepository.save(membership2);
                userService.getMembership(membershipDtoPost.getUserId(), membershipDtoPost.getMemberId(), membershipId);
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
        if(membershipRepository.findById(membershipId).isPresent() == true){
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

        if(userService.userExist(membershipDtoPost.getUserId()) == true && memberService.memberExist(membershipDtoPost.getMemberId()) == true && userService.nativeUser(membershipDtoPost.getUserId()) == false){
            membershipEntity.setStatus(true);
            membershipEntity.setUser(userEntity);
            membershipEntity.setMember(memberEntity);
            membershipEntity.setCreatedAt(LocalDateTime.now());
            if(membershipDtoPost.getMemberId() == 1){
                membershipEntity.setExpiredAt(LocalDateTime.now().plusMonths(1));
            }if(membershipDtoPost.getMemberId() == 2){
                membershipEntity.setExpiredAt(LocalDateTime.now().plusMonths(3));
            }if(membershipDtoPost.getMemberId() == 3){
                membershipEntity.setExpiredAt(LocalDateTime.now().plusMonths(6));
            }
            membershipRepository.save(membershipEntity);
            userService.getMembership(membershipDtoPost.getUserId(), membershipDtoPost.getMemberId(), membershipEntity.getMembershipId());
            log.info("membership created");
        }else{
            log.info("failed to create membership");
            throw new IllegalStateException("User or Member did'ny exist");
        }
    }

    @Override
    public int totalMembership() {
        List<MembershipEntity> membership = new ArrayList<>();
        membershipRepository.findAll().forEach(membership::add);
        int sum = membership.size();
        return sum;
    }

	@Override
	public Boolean membershipExpired(Long membershipId) {
        MembershipEntity member = membershipRepository.findById(membershipId).get();
		if(member.getExpiredAt().isAfter(LocalDateTime.now())){
            log.info("Membership is not expired yet");
            return false;
        }else{
            log.info("Membership has been expired");
            member.setMember(null);
            member.setStatus(false);
            membershipRepository.save(member);
            return true;
        }
	}

    @Override
    public List<MembershipDtoGet> findAll(Boolean keyword) {
        List<MembershipEntity> memberships = membershipRepository.findAll(keyword);
        List<MembershipDtoGet> membershipDtos = new ArrayList<>();
        
        memberships.forEach(isi ->{
            MembershipDtoGet dto = new MembershipDtoGet();
            dto.setMembershipId(isi.getMembershipId());
            dto.setStatus(isi.getStatus());
            dto.setCreatedAt(isi.getCreatedAt().toString());
            dto.setUpdatedAt(isi.getUpdated_at().toString());
            dto.setUserId(isi.getUser().getUserId());
            dto.setUsername(isi.getUser().getUsername());
            dto.setEmail(isi.getUser().getEmail());
            dto.setName(isi.getUser().getName());
            if(isi.getMember() == null){
                dto.setMemberId(null);
                dto.setMemberName("No Membership");
                dto.setMemberPeriod("No Membership");
            }else{
                dto.setMemberId(isi.getMember().getMemberId());
                dto.setMemberName(isi.getMember().getName());
                dto.setMemberPeriod(isi.getMember().getPeriod());
            }
            dto.setContact(isi.getUser().getPhone());
            dto.setAddress(isi.getUser().getAddress());
            dto.setExpiredAt(isi.getExpiredAt());

            membershipDtos.add(dto);
        });
        return membershipDtos;
    }
}
