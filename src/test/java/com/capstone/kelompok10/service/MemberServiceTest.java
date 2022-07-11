package com.capstone.kelompok10.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capstone.kelompok10.model.dto.post.MemberDtoPost;
import com.capstone.kelompok10.model.entity.MemberEntity;
import com.capstone.kelompok10.repository.MemberRepository;
import com.capstone.kelompok10.service.implementation.MemberServiceImpl;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class MemberServiceTest {
    private final EasyRandom EASY_RANDOM = new EasyRandom();

    @InjectMocks
    private MemberServiceImpl service;

    @Mock
    private MemberRepository repository;
    
    @Test
    void findAll(){
        List<MemberEntity> member = EASY_RANDOM.objects(MemberEntity.class, 2)
        .collect(Collectors.toList());

        when(repository.findAll()).thenReturn(member);
        service.findAll();
        verify(repository, times(1)).findAll();
    }

    @Test
    void getMemberById(){
        MemberEntity member = EASY_RANDOM.nextObject(MemberEntity.class);

        when(repository.findById(member.getMemberId())).thenReturn(Optional.of(member));
        service.getMemberById(member.getMemberId());
        verify(repository, times(2)).findById(member.getMemberId());
    }

    @Test
    public void updateMember() {
        MemberEntity member = EASY_RANDOM.nextObject(MemberEntity.class);
        MemberDtoPost newMember = new MemberDtoPost();
        newMember.setName("Baru");

        when(repository.findById(member.getMemberId())).thenReturn(Optional.of(member));
        service.updateMember(member.getMemberId(), newMember);
        verify(repository).save(member);
        verify(repository, times(2)).findById(member.getMemberId());
    }

    @Test
    public void createMemberDto(){
        MemberEntity memberEntity = new MemberEntity();
        MemberDtoPost memberDtoPost = EASY_RANDOM.nextObject(MemberDtoPost.class);
        memberEntity.setName(memberDtoPost.getName());
        memberEntity.setPeriod(memberDtoPost.getPeriod());
        memberEntity.setPrice(memberDtoPost.getPrice());

        service.createMemberDto(memberDtoPost);
        verify(repository).save(memberEntity);
    }

    @Test
    public void deleteMember(){
        MemberEntity memberEntity = EASY_RANDOM.nextObject(MemberEntity.class);

        when(repository.findById(memberEntity.getMemberId())).thenReturn(Optional.of(memberEntity));
        service.deleteMember(memberEntity.getMemberId());
        verify(repository, times(1)).deleteById(memberEntity.getMemberId());
        verify(repository, times(1)).findById(memberEntity.getMemberId());
    }

    @Test
    public void MemberExist(){
        MemberEntity memberEntity = EASY_RANDOM.nextObject(MemberEntity.class);

        when(repository.findById(memberEntity.getMemberId())).thenReturn(Optional.of(memberEntity));
        service.memberExist(memberEntity.getMemberId());
        assertEquals(true, service.memberExist(memberEntity.getMemberId()));
    }
}
