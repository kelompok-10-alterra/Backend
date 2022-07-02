package com.capstone.kelompok10.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.capstone.kelompok10.model.dto.post.GraphDtoPost;
import com.capstone.kelompok10.model.entity.GraphEntity;
import com.capstone.kelompok10.repository.GraphRepository;
import com.capstone.kelompok10.service.interfaces.GraphService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@AllArgsConstructor
public class GraphServiceImpl implements GraphService {
    GraphRepository graphRepository;

    @Override
    public List<GraphEntity> findAll() {
        log.info("Get all Graph without DTO");
        List<GraphEntity> graph = new ArrayList<>();
        graphRepository.findAll().forEach(graph::add);
        return graph;
    }

    @Override
    public GraphEntity getGraphById(Long graphId) {
        if(graphRepository.findById(graphId) != null){
            log.info("Graph with id {} found", graphId);
            return graphRepository.findById(graphId).get();
        }else{
            log.info("Graph with id {} not found", graphId);
            throw new IllegalStateException("Graph not Found");
        }
    }

    @Override
    public void updateGraph(Long graphId, GraphDtoPost graphDtoPost) {
        if(graphRepository.findById(graphId) != null){
            GraphEntity graph2 = graphRepository.findById(graphId).get();
            graph2.setMonth(graphDtoPost.getMonth());
            graph2.setYear(graphDtoPost.getYear());
            graph2.setTotalUser(graphDtoPost.getTotalUser());
            graph2.setTotalMember(graphDtoPost.getTotalMember());

            graphRepository.save(graph2);
            log.info("Graph updated");
        }else{
            log.info("Graph with id {} not found", graphId);
            throw new IllegalStateException("Graph not Found");
        }
    }

    @Override
    public void deleteGraph(Long graphId) {
        if(graphRepository.findById(graphId) != null){
            graphRepository.deleteById(graphId);
            log.info("Graph with id {} successfully deleted", graphId);
        }else{
            log.info("Graph with id {} not found", graphId);
            throw new IllegalStateException("Graph not Found");
        }
    }

    @Override
    public void createGraphDto(GraphDtoPost graphDtoPost) {
        GraphEntity graphEntity = new GraphEntity();
        graphEntity.setMonth(graphDtoPost.getMonth());
        graphEntity.setYear(graphDtoPost.getYear());
        graphEntity.setTotalUser(graphDtoPost.getTotalUser());
        graphEntity.setTotalMember(graphDtoPost.getTotalMember());

        graphRepository.save(graphEntity);
        log.info("Graph created");
    }
}
