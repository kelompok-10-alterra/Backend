package com.capstone.kelompok10.controller;

import java.util.List;

import com.capstone.kelompok10.model.dto.get.MembershipDtoGet;
import com.capstone.kelompok10.model.dto.post.MembershipDtoPost;
import com.capstone.kelompok10.model.entity.MembershipEntity;
import com.capstone.kelompok10.model.payload.BuyMembership;
import com.capstone.kelompok10.service.interfaces.MembershipService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/capstone/membership")
public class MembershipController {
    MembershipService membershipService;

    @Autowired
    public MembershipController(MembershipService membershipService){
        this.membershipService = membershipService;
    }

    @GetMapping("/adminAccess/getAllMembership")
    public ResponseEntity<List<MembershipDtoGet>> findAll(@RequestParam(required = false) Boolean keyword){
        if(keyword == null){
            List<MembershipDtoGet> memberships = membershipService.findAll();
            return new ResponseEntity<>(memberships, HttpStatus.OK);
        }else{
            List<MembershipDtoGet> memberships = membershipService.findAll(keyword);
            return new ResponseEntity<>(memberships, HttpStatus.OK);
        }
    }

    // @GetMapping("/adminAccess/{offset}/{pageSize}")
    // public ResponseEntity<Page<MembershipEntity>> findAllPaginationSorting(@PathVariable int offset,@PathVariable int pageSize){
    //     Page<MembershipEntity> memberships = membershipService.findAllPagination(offset, pageSize);
    //     return new ResponseEntity<>(memberships, HttpStatus.OK);
    // }

    // @GetMapping("/adminAccess/{offset}/{pageSize}/{field}")
    // public ResponseEntity<Page<MembershipEntity>> findAllPaginationSorting(@PathVariable int offset,@PathVariable int pageSize,@PathVariable String field){
    //     Page<MembershipEntity> memberships = membershipService.findAllPaginationSort(offset, pageSize, field);
    //     return new ResponseEntity<>(memberships, HttpStatus.OK);
    // }

    @GetMapping("/adminAccess/getAllMembershipWithDto")
    public ResponseEntity<List<MembershipDtoGet>> findAllDto(){
        List<MembershipDtoGet> membershipDtos = membershipService.findAllDto();
        return new ResponseEntity<>(membershipDtos, HttpStatus.OK);
    }

    @GetMapping("/userAccess/getMembershipById")
    public ResponseEntity<MembershipDtoGet> getMembershipByIdDto(@RequestParam("membershipId") Long membershipId){
        return new ResponseEntity<>(membershipService.getMembershipByIdDto(membershipId), HttpStatus.OK);
    }

    @GetMapping("/userAccess/getMembershipByUsername")
    public ResponseEntity<List<MembershipDtoGet>> getMembershipByUsername(@RequestParam("username") String username){
        return new ResponseEntity<>(membershipService.getMembershipByUsername(username), HttpStatus.OK);
    }

    @PostMapping("/adminAccess/createNewMembership")
    public ResponseEntity<MembershipDtoPost> createMemberDto(@RequestBody MembershipDtoPost membershipDtoPost){
        membershipService.createMembershipDto(membershipDtoPost);
        return new ResponseEntity<>(membershipDtoPost, HttpStatus.OK);
    }

    @PutMapping("/adminAccess/updateMembership/{membershipId}")
    public ResponseEntity<MembershipEntity> updateMembership(@PathVariable("membershipId") Long membershipId, @RequestBody MembershipDtoPost membershipDtoPost){
        membershipService.updateMembership(membershipId, membershipDtoPost);
        return new ResponseEntity<>(membershipService.getMembershipById(membershipId), HttpStatus.OK);
    }

    @DeleteMapping("/adminAccess/deleteMembership/{membershipId}")
    public ResponseEntity<MembershipEntity> deleteMembership(@PathVariable("membershipId") Long membershipId){
        membershipService.deleteMembership(membershipId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/adminAccess/countTotalMembership")
    public int totalUser(){
        return membershipService.totalMembership();
    }

    @PostMapping("/userAccess/buyMembership")
    public String buyMembership(@RequestBody BuyMembership buyMembership){
        return membershipService.buyMembership(buyMembership);
    }
}
