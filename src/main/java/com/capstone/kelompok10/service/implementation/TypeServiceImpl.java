package com.capstone.kelompok10.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.kelompok10.model.dto.get.TypeDtoGet;
import com.capstone.kelompok10.model.dto.post.TypeDtoPost;
import com.capstone.kelompok10.model.entity.TypeEntity;
import com.capstone.kelompok10.repository.TypeRepository;
import com.capstone.kelompok10.service.interfaces.TypeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class TypeServiceImpl implements TypeService {
    TypeRepository typeRepository;

    @Autowired
    public TypeServiceImpl(TypeRepository typeRepository){
        this.typeRepository = typeRepository;
    }

    @Override
    public List<TypeDtoGet> findAll() {
        log.info("Get all Type with DTO");
        List<TypeEntity> types = typeRepository.findAll();
        List<TypeDtoGet> typeDtos = new ArrayList<>();
        
        types.forEach(isi ->{
            TypeDtoGet dto = new TypeDtoGet();
            dto.setTypeId(isi.getTypeId());
            dto.setName(isi.getName());
            dto.setCreatedAt(isi.getCreated_at().toString());
            dto.setUpdatedAt(isi.getUpdated_at().toString());

            typeDtos.add(dto);
        });
        return typeDtos;
    }
    
    // @Override
    // public Page<TypeEntity> findAllPagination(int offset, int pageSize) {
    //     log.info("Get all Type with Pagination");
    //     Page<TypeEntity> type = typeRepository.findAll(PageRequest.of(offset, pageSize));
    //     return type;
    // }

    // @Override
    // public Page<TypeEntity> findAllPaginationSort(int offset, int pageSize, String field){
    //     log.info("Get all Type with Pagination and Sorting");
    //     Page<TypeEntity> type = typeRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
    //     return type;
    // }

    // @Override
    // public List<TypeDtoGet> findAllDto() {
    //     log.info("Get all Type with DTO");
    //     List<TypeEntity> types = typeRepository.findAll();
    //     List<TypeDtoGet> typeDtos = new ArrayList<>();
        
    //     types.forEach(isi ->{
    //         TypeDtoGet dto = new TypeDtoGet();
    //         dto.setTypeId(isi.getTypeId());
    //         dto.setName(isi.getName());

    //         typeDtos.add(dto);
    //     });
    //     return typeDtos;
    // }

    @Override
    public TypeEntity getTypeById(Long typeId) {
        if(typeRepository.findById(typeId) != null){
            log.info("Type with id {} found", typeId);
            return typeRepository.findById(typeId).get();
        }else{
            log.info("Type with id {} not found", typeId);
            throw new IllegalStateException("Type not Found");
        }
    }

    @Override
    public void updateType(Long typeId, TypeDtoPost typeDtoPost) {
        if(typeRepository.findById(typeId) != null){
            TypeEntity type2 = typeRepository.findById(typeId).get();
            type2.setName(typeDtoPost.getName());

            typeRepository.save(type2);
            log.info("Type updated");
        }else{
            log.info("Type with id {} not found", typeId);
            throw new IllegalStateException("Type not Found");
        }
    }

    @Override
    public void deleteType(Long typeId) {
        if(typeRepository.findById(typeId) != null){
            typeRepository.deleteById(typeId);
            log.info("Type with id {} successfully deleted", typeId);
        }else{
            log.info("Type with id {} not found", typeId);
            throw new IllegalStateException("Type not Found");
        }
    }

    @Override
    public void createTypeDto(TypeDtoPost typeDtoPost) {
        if(typeRepository.findByName(typeDtoPost.getName()) == null){
            TypeEntity typeEntity = new TypeEntity();
            typeEntity.setName(typeDtoPost.getName());

            typeRepository.save(typeEntity);
            log.info("Type created");
        }else{
            log.info("Type with name {} already exist", typeDtoPost.getName());
            throw new IllegalStateException("Type already Exist");
        }
    }

    @Override
    public Boolean typeExist(Long typeId) {
        if(typeRepository.findById(typeId).isPresent() == true){
            return true;
        }else{
            return false;
        }
    }
}
