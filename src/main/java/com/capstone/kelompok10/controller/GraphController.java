package com.capstone.kelompok10.controller;

import java.util.List;

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

import com.capstone.kelompok10.model.dto.post.GraphDtoPost;
import com.capstone.kelompok10.model.entity.GraphEntity;
import com.capstone.kelompok10.service.interfaces.GraphService;

@RestController
@RequestMapping("/capstone/graph")
public class GraphController {
    GraphService graphService;

    @Autowired
    public GraphController(GraphService graphService){
        this.graphService = graphService;
    }

    @GetMapping("/adminAccess/getAllGraph")
    public ResponseEntity<List<GraphEntity>> findAll(){
        List<GraphEntity> graphs = graphService.findAll();
        return new ResponseEntity<>(graphs, HttpStatus.OK);
    }

    @GetMapping("/adminAccess/getGraphById")
    public ResponseEntity<GraphEntity> getGraphById(@RequestParam("graphId") Long graphId){
        return new ResponseEntity<>(graphService.getGraphById(graphId), HttpStatus.OK);
    }

    @PostMapping("/adminAccess/createNewGraph")
    public ResponseEntity<GraphDtoPost> createGraphDto(@RequestBody GraphDtoPost graphDtoPost){
        graphService.createGraphDto(graphDtoPost);
        return new ResponseEntity<>(graphDtoPost, HttpStatus.OK);
    }

    @PutMapping("/adminAccess/updateGraph/{graphId}")
    public ResponseEntity<GraphEntity> updateGraph(@PathVariable("graphId") Long graphId, @RequestBody GraphDtoPost graphDtoPost){
        graphService.updateGraph(graphId, graphDtoPost);
        return new ResponseEntity<>(graphService.getGraphById(graphId), HttpStatus.OK);
    }

    @DeleteMapping("/adminAccess/deleteGraph/{graphId}")
    public ResponseEntity<GraphEntity> deleteGraph(@PathVariable("graphId") Long graphId){
        graphService.deleteGraph(graphId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
