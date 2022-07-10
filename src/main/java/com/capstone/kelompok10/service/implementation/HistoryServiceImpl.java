package com.capstone.kelompok10.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capstone.kelompok10.model.entity.HistoryEntity;
import com.capstone.kelompok10.repository.HistoryRepository;
import com.capstone.kelompok10.service.interfaces.HistoryService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@AllArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    private HistoryRepository historyRepository;
    
    @Override
    public List<HistoryEntity> findAll() {
        log.info("Get all History");
        List<HistoryEntity> History = historyRepository.findAll();
        return History;
    }

    @Override
    public HistoryEntity getHistoryById(Long historyId) {
        if(historyRepository.findById(historyId) != null){
            log.info("History with id {} found", historyId);
            return historyRepository.findById(historyId).get();
        }else{
            log.info("History with id {} not found", historyId);
            throw new IllegalStateException("History not Found");
        }
    }
}
