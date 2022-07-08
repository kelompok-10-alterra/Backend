package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import com.capstone.kelompok10.model.entity.HistoryEntity;

public interface HistoryService {
    List<HistoryEntity> findAll();
    HistoryEntity getHistoryById(Long historyId);
}
