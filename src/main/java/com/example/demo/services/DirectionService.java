package com.example.demo.services;

import com.example.demo.controllers.DepartementController;
import com.example.demo.controllers.DirectionController;
import com.example.demo.dto.DirectionDto;
import com.example.demo.entities.Departement;
import com.example.demo.entities.Direction;
import com.example.demo.enumeration.Config;
import com.example.demo.dto.interfaces.OrganisationDto;
import com.example.demo.repositories.DepartementRepository;
import com.example.demo.repositories.DirectionRepository;
import com.example.demo.services.abstracts.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DirectionService extends BaseService<Direction, DirectionRepository> {


    @Autowired
    public DirectionRepository directionRepository;

    @Autowired
    private DepartementRepository departementRepository;

    public Direction findMinOrganizationId(){
        return directionRepository.findMinOrganizationId();
    }

    public List<OrganisationDto> configDirection(List<DirectionDto> directionDtos, Config config) {
        Set<Long> organizationIdDirections = new HashSet<>();
        List<OrganisationDto> organisations = new ArrayList<>();
        List<Long> idListDirection = new ArrayList<>();
        List<Long> idListDepartement = new ArrayList<>();

        for (DirectionDto directionDto : directionDtos) {
            System.out.println("organisationId est " + directionDto.getOrganizationId());
            directionDto.setId(null);
            if (directionDto.getName().toUpperCase().contains("DIRECTION")) {

                directionDto.setDepartements(null);
                directionDto.setParameters(null);

                Direction newDirection = DirectionController.convertDtoToDirection(directionDto);
                organizationIdDirections.add(directionDto.getOrganizationId());
                System.out.println("Valeur de l'organisation" + organizationIdDirections);
                Direction direction = directionRepository.findByOrganizationId(directionDto.getOrganizationId());
                if (direction == null) {
                    Direction register = this.create(newDirection);
                    idListDirection.add(register.getId());
                    organisations.add(DirectionController.convertDirectionToDto(register, 1, 0));
                } else {
                    newDirection.setId(direction.getId());
                    newDirection.setDepartements(direction.getDepartements());
                    newDirection.setParameters(direction.getParameters());

                    Direction register = this.create(newDirection);
                    idListDirection.add(register.getId());
                    organisations.add(DirectionController.convertDirectionToDto(register, 1, 0));
                }

            } else if (organizationIdDirections.contains(directionDto.getParentorganizationId())) {
                System.out.println("Creation departement");

                Departement departementBD = departementRepository.findByOrganizationId(directionDto.getOrganizationId());
                if (departementBD == null) {

                    Direction direction = directionRepository.findByOrganizationId(directionDto.getParentorganizationId());

                    Departement departement = new Departement(
                            directionDto.getId(),
                            directionDto.getOrganizationId(),
                            directionDto.getLevel(),
                            directionDto.getType_(),
                            directionDto.getTreePath(),
                            directionDto.getParentorganizationId(),
                            directionDto.getName()
                    );
                    departement.setDirection(direction);

                    Departement register = departementRepository.save(departement);
                    idListDepartement.add(register.getId());
                    organisations.add(DepartementController.convertDepartementToDto(register, 1, 1));
                }
                else {
                    departementBD.setOrganizationId(directionDto.getOrganizationId());
                    departementBD.setLevel(directionDto.getLevel());
                    departementBD.setType(directionDto.getType_());
                    departementBD.setTreepath(directionDto.getTreePath());
                    departementBD.setParentorganizationId(directionDto.getParentorganizationId());
                    departementBD.setName(directionDto.getName());

                    Departement register = departementRepository.save(departementBD);
                    idListDepartement.add(register.getId());
                    organisations.add(DepartementController.convertDepartementToDto(register, 1, 1));
                }
            }

        }
        if (config == Config.RECREATE) {
            List<Direction> directionList = directionRepository.findAll();
            List<Departement> departementList = departementRepository.findAll();
            for (Direction direction : directionList) {
                if (!idListDirection.contains(direction.getId())) {
                    directionRepository.deleteModel(direction.getId());
                }
            }
            for (Departement departement : departementList) {
                if (!idListDepartement.contains(departement.getId())) {
                    departementRepository.deleteModel(departement.getId());
                }
            }
        }
        return organisations;
    }


/*    public List<Direction> getAllDirection() {
        List<Direction> directions = directionRepository.findAll();
        return directions;
    }*/

    /*public Direction sauvegarder(Direction direction) {
        return directionRepository.save(direction);
    }*/

/*    public List<Direction> sauvergarderListe(List<Direction> directions) {
        List<Direction> directions1 = new ArrayList<>();

        for (Direction direction : directions) {
            directions1.add(this.directionRepository.save(direction));
        }
        return directions1;
    }*/




/*    public Direction creer(Direction direction) {
        return this.directionRepository.save(direction);
    }*/
}
