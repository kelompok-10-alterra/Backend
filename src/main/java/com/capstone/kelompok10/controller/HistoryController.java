package com.capstone.kelompok10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.kelompok10.model.entity.HistoryEntity;
import com.capstone.kelompok10.service.interfaces.HistoryService;

@RestController
@RequestMapping("/capstone/history")
public class HistoryController {
    HistoryService historyService;

    @Autowired
    public HistoryController(HistoryService historyService){
        this.historyService = historyService;
    }

    @GetMapping("/adminAccess/getAllHistory")
    public ResponseEntity<List<HistoryEntity>> findAll(){
        List<HistoryEntity> historys = historyService.findAll();
        return new ResponseEntity<>(historys, HttpStatus.OK);
    }

    @GetMapping("/userAccess/getHistoryById")
    public ResponseEntity<HistoryEntity> getRoleById(@RequestParam("historyId") Long historyId){
        historyService.getHistoryById(historyId);
        return new ResponseEntity<>(historyService.getHistoryById(historyId), HttpStatus.OK);
    }
}
