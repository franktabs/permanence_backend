package com.example.demo.services;


import com.example.demo.entities.Annonce;
import com.example.demo.repositories.AnnonceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnonceService {

    @Autowired
    AnnonceRepository annonceRepository;

    public Annonce create(Annonce annonce){
        return annonceRepository.save(annonce);
    }


    public Annonce update(Annonce annonceUpdate, Long id){
        Annonce annonce1 = annonceRepository.findById(id).orElse(null);
        if(annonce1 ==null) return null;
        if(!annonce1.getId().equals(annonceUpdate.getId())) return null;
        return annonceRepository.save(annonceUpdate);
    }

    public List<Annonce> getAllNotification(){
        return annonceRepository.findAll();
    }

    public Annonce getNotificationById(Long id){
        return annonceRepository.findById(id).orElse(null);
    }
}
