package com.example.demo.services;

import com.example.demo.entities.Direction;
import com.example.demo.repositories.DirectionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DirectionService {

    private DirectionRepository directionRepository;

    public DirectionService(DirectionRepository directionRepository) {
        this.directionRepository = directionRepository;
    }

    public List<Direction> getAllDirection(){
        List<Direction> directions =  directionRepository.findAll();
        return  directions;
    }

    public Direction sauvegarder(Direction direction){
        return  directionRepository.save(direction);
    }

    public List<Direction> sauvergarderListe(List<Direction> directions){
        List<Direction> directions1 = new ArrayList<>();

        for(Direction direction:directions){
            directions1.add(this.directionRepository.save(direction));
        }
        return  directions1;
    }
    public void creer(Direction direction){
        this.directionRepository.save(direction);
    }
}
