package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import com.capstone.kelompok10.model.dto.post.GraphDtoPost;
import com.capstone.kelompok10.model.entity.GraphEntity;

public interface GraphService {
    List<GraphEntity> findAll();
    GraphEntity getGraphById(Long graphId);
    void createGraphDto(GraphDtoPost graphDtoPost);
    void updateGraph(Long graphId, GraphDtoPost graphDtoPost);
    void deleteGraph(Long graphId);
}
