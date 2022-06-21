package com.capstone.kelompok10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.kelompok10.model.dto.get.MemberDtoGet;
import com.capstone.kelompok10.model.dto.post.MemberDtoPost;
import com.capstone.kelompok10.model.entity.MemberEntity;
import com.capstone.kelompok10.service.interfaces.MemberService;

// @CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/capstone/member")
public class MemberController {
    MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity<List<MemberEntity>> findAll(){
        List<MemberEntity> members = memberService.findAll();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/{offset}/{pageSize}")
    public ResponseEntity<Page<MemberEntity>> findAllPaginationSorting(@PathVariable int offset,@PathVariable int pageSize){
        Page<MemberEntity> members = memberService.findAllPagination(offset, pageSize);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/{offset}/{pageSize}/{field}")
    public ResponseEntity<Page<MemberEntity>> findAllPaginationSorting(@PathVariable int offset,@PathVariable int pageSize,@PathVariable String field){
        Page<MemberEntity> members = memberService.findAllPaginationSort(offset, pageSize, field);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/dto")
    public ResponseEntity<List<MemberDtoGet>> findAllDto(){
        List<MemberDtoGet> memberDtos = memberService.findAllDto();
        return new ResponseEntity<>(memberDtos, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<MemberEntity> getMemberById(@RequestParam("memberId") Long memberId){
        return new ResponseEntity<>(memberService.getMemberById(memberId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MemberEntity> createMember(@RequestBody MemberEntity member){
        memberService.createMember(member);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PostMapping("/dto")
    public ResponseEntity<MemberDtoPost> createMemberDto(@RequestBody MemberDtoPost memberDtoPost){
        memberService.createMemberDto(memberDtoPost);
        return new ResponseEntity<>(memberDtoPost, HttpStatus.OK);
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<MemberEntity> updateMember(@PathVariable("memberId") Long memberId, @RequestBody MemberDtoPost memberDtoPost){
        memberService.updateMember(memberId, memberDtoPost);
        return new ResponseEntity<>(memberService.getMemberById(memberId), HttpStatus.OK);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<MemberEntity> deleteMember(@PathVariable("memberId") Long memberId){
        memberService.deleteMember(memberId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
