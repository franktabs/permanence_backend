package com.example.demo.services;

import com.example.demo.controllers.DirectionController;
import com.example.demo.dto.DepartementDto;
import com.example.demo.dto.DirectionDto;
import com.example.demo.entities.Departement;
import com.example.demo.entities.Direction;
import com.example.demo.enumeration.Config;
import com.example.demo.repositories.DepartementRepository;
import com.example.demo.repositories.DirectionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DirectionService {


    private DirectionRepository directionRepository;
    private DepartementRepository departementRepository;

    public DirectionService(DirectionRepository directionRepository) {
        this.directionRepository = directionRepository;
    }

    public List<Direction> getAllDirection() {
        List<Direction> directions = directionRepository.findAll();
        return directions;
    }

    public Direction sauvegarder(Direction direction) {
        return directionRepository.save(direction);
    }

    public List<Direction> sauvergarderListe(List<Direction> directions) {
        List<Direction> directions1 = new ArrayList<>();

        for (Direction direction : directions) {
            directions1.add(this.directionRepository.save(direction));
        }
        return directions1;
    }

    public void configDirection(List<DirectionDto> directionDtos, Config config) {
        Set<Long> OrganizationIdDirections = new HashSet<>();
        if (config == Config.RECREATE) {
            directionRepository.deleteAll();
        }
        for (DirectionDto directionDto : directionDtos) {
            if (directionDto.getName().toUpperCase().contains("DIRECTION")) {
                Direction newDirection = DirectionController.convertDtoToDirection(directionDto);
                OrganizationIdDirections.add(directionDto.getOrganizationId());
                Direction direction = directionRepository.findByOrganizationId(directionDto.getOrganizationId());
                if (direction == null) {
                    this.creer(newDirection);
                } else {
                    newDirection.setId(direction.getId());
                    newDirection.setDepartements(direction.getDepartements());
                    newDirection.setParameters(direction.getParameters());
                    this.creer(newDirection);
                }

            } else if (OrganizationIdDirections.contains(directionDto.getParentorganizationId())) {
                Direction direction = directionRepository.findByOrganizationId(directionDto.getParentorganizationId());
                Departement departement = new Departement(
                        directionDto.getId(),
                        directionDto.getOrganizationId(),
                        directionDto.getLevel(),
                        directionDto.getType_(),
                        directionDto.getTreepath(),
                        directionDto.getParentorganizationId(),
                        directionDto.getName()
                );
                departement.setDirection(direction);
                departementRepository.save(departement);
            }
        }

    }


    public void creer(Direction direction) {
        this.directionRepository.save(direction);
    }
}
