package com.example.demo.controllers;

import com.example.demo.dto.AnnonceDto;
import com.example.demo.entities.Annonce;
import com.example.demo.services.AnnonceService;
import com.example.demo.utils.StringExtract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(path = "annonce")
public class AnnonceController {

    @Autowired
    AnnonceService annonceService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AnnonceDto>> allAnnonce() {
        List<Annonce> annonces = annonceService.getAllAnnonce();
        List<AnnonceDto> annonceDtos = new ArrayList<>();
        for (Annonce annonce : annonces) {
            annonceDtos.add(convertAnnonceToDto(annonce, 1, 1));
        }
        return ResponseEntity.status(HttpStatus.OK).body(annonceDtos);
    }

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AnnonceDto> getOneAnnonce(@PathVariable Long id){
        Annonce annonce = annonceService.getAnnonceById(id);
        if(annonce==null){
            return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(convertAnnonceToDto(annonce, 1, 1));
    }

    @PostMapping()
    public ResponseEntity<?> creer(@Valid @RequestBody AnnonceDto annonceDto, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> mapErrors = new HashMap<>();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    mapErrors.put(error.getField(), error.getDefaultMessage());
                }
                return ResponseEntity.badRequest().body(mapErrors);
            }
            Annonce annonce = convertDtoToAnnonce(annonceDto);

            return ResponseEntity.status(HttpStatus.CREATED).body(convertAnnonceToDto(annonceService.create(annonce), 1, 1));

        } catch (DataIntegrityViolationException e) {
            Map<String, String> message = StringExtract.keyValueError(e.getMostSpecificCause().getMessage());
            System.out.println("\n\nerreur ici" + message + "\n\n");
            if (message.isEmpty()) {
                message.put("errors", e.getMostSpecificCause().getMessage());
            }
            return ResponseEntity.badRequest().body(message);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur au niveau du serveur c'est produit");
        }
    }

    public static AnnonceDto convertAnnonceToDto(Annonce annonce, int depthRecepteur, int depthNotification){

        AnnonceDto annonceDto = new AnnonceDto(
                annonce.getId(),
                annonce.getIsViewed(),
                annonce.getIsDeleted()
        );

        if(depthRecepteur > 0){
            if(annonce.getRecepteur()!=null){
                annonceDto.setRecepteur(PersonnelController.convertPersonnelToDto(annonce.getRecepteur(), 0, 0, 0, 0, 0, 0, 0, 0, depthRecepteur-1));
            }
        }

        if(depthNotification>0){
            if(annonce.getNotification()!=null){
                annonceDto.setNotification(NotificationController.convertNotificationToDto(annonce.getNotification(), 0, depthNotification-1));
            }
        }

        return annonceDto;
    }


    public static Annonce convertDtoToAnnonce(AnnonceDto annonceDto){
        Annonce annonce = new Annonce(
                annonceDto.getId(),
                annonceDto.getIsViewed(),
                annonceDto.getIsDeleted()
        );

        if(annonceDto.getRecepteur()!=null){
            annonce.setRecepteur(PersonnelController.convertDtoToPersonnel(annonceDto.getRecepteur()));
        }

        if(annonceDto.getNotification()!=null){
            annonce.setNotification(NotificationController.convertDtoToNotification(annonceDto.getNotification()));
        }

        return annonce;

    }
}
