package com.capstone.kelompok10.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.kelompok10.model.dto.MemberDto;
import com.capstone.kelompok10.model.entity.MemberEntity;
import com.capstone.kelompok10.repository.MemberRepository;
import com.capstone.kelompok10.service.interfaces.MemberService;

@Service
public class MemberServiceImpl implements MemberService {
    MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Override
    public List<MemberEntity> getAllMember() {
        List<MemberEntity> members = new ArrayList<>();
        memberRepository.findAll().forEach(members::add);
        return members;
    }

    @Override
    public List<MemberDto> getAllMemberDto() {
        List<MemberEntity> members = memberRepository.findAll();
        List<MemberDto> memberDtos = new ArrayList<>();
        
        members.forEach(isi ->{
            MemberDto dto = new MemberDto();
            dto.setMember_id(isi.getMember_id());
            dto.setLength(isi.getLength());
            dto.setPrice(isi.getPrice());

            memberDtos.add(dto);
        });
        return memberDtos;
    }

    @Override
    public MemberEntity getMemberById(Long member_id) {
        return memberRepository.findById(member_id).get();
    }

    @Override
    public void createMember(MemberEntity member) {
        memberRepository.save(member);
    }

    @Override
    public void updateMember(Long member_id, MemberEntity member) {
        MemberEntity member2 = memberRepository.findById(member_id).get();
        System.out.println(member2.toString());
        member2.setLength(member.getLength());
        member2.setPrice(member.getPrice());

        memberRepository.save(member2);
    }

    @Override
    public void deleteMember(Long member_id) {
        memberRepository.deleteById(member_id);
    }
}