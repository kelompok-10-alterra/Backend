package com.capstone.kelompok10.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capstone.kelompok10.model.dto.post.GraphDtoPost;
import com.capstone.kelompok10.model.entity.GraphEntity;
import com.capstone.kelompok10.repository.GraphRepository;
import com.capstone.kelompok10.service.implementation.GraphServiceImpl;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class GraphServiceTest {
    private final EasyRandom EASY_RANDOM = new EasyRandom();

    @InjectMocks
    private GraphServiceImpl service;

    @Mock
    private GraphRepository repository;

    @Test
    void findAll(){
        List<GraphEntity> graph = EASY_RANDOM.objects(GraphEntity.class, 2)
        .collect(Collectors.toList());

        when(repository.findAll()).thenReturn(graph);
        service.findAll();
        verify(repository, times(1)).findAll();
    }

    @Test
    void getGraphById(){
        GraphEntity graph = EASY_RANDOM.nextObject(GraphEntity.class);

        when(repository.findById(graph.getGraphId())).thenReturn(Optional.of(graph));
        service.getGraphById(graph.getGraphId());
        verify(repository, times(2)).findById(graph.getGraphId());
    }

    @Test
    public void updateGraph() {
        GraphEntity graph = EASY_RANDOM.nextObject(GraphEntity.class);
        GraphDtoPost newGraph = new GraphDtoPost();
        newGraph.setMonth("Baru");

        when(repository.findById(graph.getGraphId())).thenReturn(Optional.of(graph));
        service.updateGraph(graph.getGraphId(), newGraph);
        verify(repository).save(graph);
        verify(repository, times(2)).findById(graph.getGraphId());
    }

    @Test
    public void createGraphDto(){
        GraphEntity graphEntity = new GraphEntity();
        GraphDtoPost graphDtoPost = EASY_RANDOM.nextObject(GraphDtoPost.class);
        graphEntity.setMonth(graphDtoPost.getMonth());
        graphEntity.setTotalMember(graphDtoPost.getTotalMember());
        graphEntity.setTotalUser(graphDtoPost.getTotalUser());
        graphEntity.setYear(graphDtoPost.getYear());

        service.createGraphDto(graphDtoPost);
        verify(repository).save(graphEntity);
    }

    @Test
    public void deleteGraph(){
        GraphEntity graphEntity = EASY_RANDOM.nextObject(GraphEntity.class);

        when(repository.findById(graphEntity.getGraphId())).thenReturn(Optional.of(graphEntity));
        service.deleteGraph(graphEntity.getGraphId());
        verify(repository, times(1)).deleteById(graphEntity.getGraphId());
        verify(repository, times(1)).findById(graphEntity.getGraphId());
    }
}
