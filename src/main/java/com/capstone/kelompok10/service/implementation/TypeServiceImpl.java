package com.capstone.kelompok10.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.kelompok10.model.dto.TypeDto;
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
    public List<TypeDto> getAllTypeDto() {
        List<TypeEntity> types = typeRepository.findAll();
        List<TypeDto> typeDtos = new ArrayList<>();
        
        types.forEach(isi ->{
            TypeDto dto = new TypeDto();
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
}
