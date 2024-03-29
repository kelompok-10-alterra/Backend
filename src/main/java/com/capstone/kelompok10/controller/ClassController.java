package com.capstone.kelompok10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Page;
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

import com.capstone.kelompok10.model.dto.get.ClassDtoGet;
import com.capstone.kelompok10.model.dto.post.ClassDtoPost;
import com.capstone.kelompok10.model.entity.ClassEntity;
import com.capstone.kelompok10.model.payload.GetUserByClass;
import com.capstone.kelompok10.service.interfaces.ClassService;

@RestController
@RequestMapping("/capstone/class")
public class ClassController {
    ClassService classService;

    @Autowired
    public ClassController(ClassService classService){
        this.classService = classService;
    }

    @GetMapping("/userAccess/getAllClass")
    public ResponseEntity<List<ClassDtoGet>> findAll(@RequestParam(required = false) String keyword){
        if(keyword == null){
            List<ClassDtoGet> classs = classService.findAll();
            return new ResponseEntity<>(classs, HttpStatus.OK);
        }else{
            List<ClassDtoGet> classs = classService.findAll(keyword);
            return new ResponseEntity<>(classs, HttpStatus.OK);
        }
    }

    // @GetMapping("/userAccess/getAllClass/{offset}/{pageSize}")
    // public ResponseEntity<Page<ClassEntity>> findAllPaginationSorting(@PathVariable int offset,@PathVariable int pageSize){
    //     Page<ClassEntity> classs = classService.findAllPagination(offset, pageSize);
    //     return new ResponseEntity<>(classs, HttpStatus.OK);
    // }

    // @GetMapping("/userAccess/getAllClass/{offset}/{pageSize}/{field}")
    // public ResponseEntity<Page<ClassEntity>> findAllPaginationSorting(@PathVariable int offset,@PathVariable int pageSize,@PathVariable String field){
    //     Page<ClassEntity> classs = classService.findAllPaginationSort(offset, pageSize, field);
    //     return new ResponseEntity<>(classs, HttpStatus.OK);
    // }

    // @GetMapping("/userAccess/getAllClassWithDto")
    // public ResponseEntity<List<ClassDtoGet>> findAllDto(){
    //     List<ClassDtoGet> classDtos = classService.findAllDto();
    //     return new ResponseEntity<>(classDtos, HttpStatus.OK);
    // }

    // @GetMapping("/userAccess/getClassById")
    // public ResponseEntity<ClassEntity> getClassById(@RequestParam("classId") Long classId){
    //     return new ResponseEntity<>(classService.getClassById(classId), HttpStatus.OK);
    // }

    @GetMapping("/userAccess/getClassByIdDto")
    public ResponseEntity<ClassDtoGet> getClassByIdDto(@RequestParam("classId") Long classId){
        return new ResponseEntity<>(classService.getClassByIdDto(classId), HttpStatus.OK);
    }

    @PostMapping("/adminAccess/createNewClass")
    public ResponseEntity<ClassDtoPost> createClassDto(@RequestBody ClassDtoPost classDtoPost){
        classService.createClassDto(classDtoPost);
        return new ResponseEntity<>(classDtoPost, HttpStatus.OK);
    }

    @PutMapping("/adminAccess/updateClass/{classId}")
    public ResponseEntity<ClassEntity> updateClass(@PathVariable("classId") Long classId, @RequestBody ClassDtoPost classesDtoPost){
        classService.updateClass(classId, classesDtoPost);
        return new ResponseEntity<>(classService.getClassById(classId), HttpStatus.OK);
    }

    @DeleteMapping("/adminAccess/deleteClass/{classId}")
    public ResponseEntity<ClassEntity> deleteClass(@PathVariable("classId") Long classId){
        classService.deleteClass(classId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/adminAccess/getUserByClassId")
    public ResponseEntity <List<GetUserByClass>> getUserByClass(@RequestParam("classId") Long classId){
        List<GetUserByClass> user = classService.getBookingByClassId(classId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/userAccess/getClassByType")
    public ResponseEntity <List<ClassDtoGet>> getClassByType(@RequestParam("typeName") String typeName){
        List<ClassDtoGet> classes = classService.getClassByType(typeName);
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @GetMapping("/userAccess/getClassByCategory")
    public ResponseEntity <List<ClassDtoGet>> getClassByCategory(@RequestParam("categoryName") String categoryName){
        List<ClassDtoGet> classes = classService.getClassByCategoryName(categoryName);
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }
}
