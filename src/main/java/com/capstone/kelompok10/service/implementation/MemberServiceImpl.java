package com.capstone.kelompok10.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.capstone.kelompok10.model.dto.get.MemberDtoGet;
import com.capstone.kelompok10.model.dto.post.MemberDtoPost;
import com.capstone.kelompok10.model.entity.MemberEntity;
import com.capstone.kelompok10.repository.MemberRepository;
import com.capstone.kelompok10.service.interfaces.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Override
    public List<MemberDtoGet> findAll() {
        List<MemberEntity> members = memberRepository.findAll();
        List<MemberDtoGet> memberDtos = new ArrayList<>();
        
        members.forEach(isi ->{
            MemberDtoGet dto = new MemberDtoGet();
            dto.setMemberId(isi.getMemberId());
            dto.setPeriod(isi.getPeriod());
            dto.setPrice(isi.getPrice());
            dto.setCreatedAt(isi.getCreated_at().toString());
            dto.setUpdatedAt(isi.getUpdated_at().toString());

            memberDtos.add(dto);
        });
        return memberDtos;
    }
    
    @Override
    public Page<MemberEntity> findAllPagination(int offset, int pageSize) {
        log.info("Get all Member with Pagination");
        Page<MemberEntity> member = memberRepository.findAll(PageRequest.of(offset, pageSize));
        return member;
    }

    @Override
    public Page<MemberEntity> findAllPaginationSort(int offset, int pageSize, String field){
        log.info("Get all Member with Pagination and Sorting");
        Page<MemberEntity> member = memberRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return member;
    }

    // @Override
    // public List<MemberDtoGet> findAllDto() {
    //     log.info("Get all Member with DTO");
    //     List<MemberEntity> members = memberRepository.findAll();
    //     List<MemberDtoGet> memberDtos = new ArrayList<>();
        
    //     members.forEach(isi ->{
    //         MemberDtoGet dto = new MemberDtoGet();
    //         dto.setMemberId(isi.getMemberId());
    //         dto.setPeriod(isi.getPeriod());
    //         dto.setPrice(isi.getPrice());

    //         memberDtos.add(dto);
    //     });
    //     return memberDtos;
    // }

    @Override
    public MemberEntity getMemberById(Long memberId) {
        if(memberRepository.findById(memberId) != null){
            log.info("Member with id {} found", memberId);
            return memberRepository.findById(memberId).get();
        }else{
            log.info("Member with id {} not found", memberId);
            throw new IllegalStateException("Member not found");
        }
    }

    @Override
    public void updateMember(Long memberId, MemberDtoPost memberDtoPost) {
        if(memberRepository.findById(memberId) != null){
            MemberEntity member2 = memberRepository.findById(memberId).get();
            member2.setPeriod(memberDtoPost.getPeriod());
            member2.setPrice(memberDtoPost.getPrice());

            memberRepository.save(member2);
            log.info("Member updated");
        }else{
            log.info("Member with id {} not found", memberId);
            throw new IllegalStateException("Member not found");
        }
    }

    @Override
    public void deleteMember(Long memberId) {
        if(memberRepository.findById(memberId).isPresent() == true){
            memberRepository.deleteById(memberId);
            log.info("Member with id {} successfully deleted", memberId);
        }else{
            log.info("Member with id {} not found", memberId);
            throw new IllegalStateException("Member not found");
        }
    }

	@Override
	public void createMemberDto(MemberDtoPost memberDtoPost) {
        if(memberRepository.findByPeriod(memberDtoPost.getPeriod()) == null){
            MemberEntity memberEntity = new MemberEntity();
            memberEntity.setPeriod(memberDtoPost.getPeriod());
            memberEntity.setPrice(memberDtoPost.getPrice());

            memberRepository.save(memberEntity);
            log.info("Member created");
        }else{
            log.info("Member with length {} already exist");
            throw new IllegalStateException("Member already exist");
        }
	}

    @Override
    public Boolean memberExist(Long memberId) {
        if(memberRepository.findById(memberId) == null){
            return false;
        }else{
            return true;
        }
    }
}
