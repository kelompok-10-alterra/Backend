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

@Service
@RequiredArgsConstructor
public class TypeServiceImpl implements TypeService {
    TypeRepository typeRepository;

    @Autowired
    public TypeServiceImpl(TypeRepository typeRepository){
        this.typeRepository = typeRepository;
    }

    @Override
    public List<TypeEntity> getAllType() {
        List<TypeEntity> types = new ArrayList<>();
        typeRepository.findAll().forEach(types::add);
        return types;
    }

    @Override
    public List<TypeDtoGet> getAllTypeDto() {
        List<TypeEntity> types = typeRepository.findAll();
        List<TypeDtoGet> typeDtos = new ArrayList<>();
        
        types.forEach(isi ->{
            TypeDtoGet dto = new TypeDtoGet();
            dto.setType_id(isi.getType_id());
            dto.setName(isi.getName());

            typeDtos.add(dto);
        });
        return typeDtos;
    }

    @Override
    public TypeEntity getTypeById(Long type_id) {
        return typeRepository.findById(type_id).get();
    }

    @Override
    public void createType(TypeEntity type) {
        typeRepository.save(type);
    }

    @Override
    public void updateType(Long type_id, TypeEntity type) {
        TypeEntity type2 = typeRepository.findById(type_id).get();
        System.out.println(type2.toString());
        type2.setName(type.getName());

        typeRepository.save(type2);
    }

    @Override
    public void deleteType(Long type_id) {
        typeRepository.deleteById(type_id);
    }

    @Override
    public void createTypeDto(TypeDtoPost typeDtoPost) {
        TypeEntity typeEntity = new TypeEntity();
        typeEntity.setName(typeDtoPost.getName());

        typeRepository.save(typeEntity);
    }
}
