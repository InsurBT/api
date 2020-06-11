package com.example.apipsia.controller;

import java.util.List;

import com.example.apipsia.model.Ville;
import com.example.apipsia.service.VilleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "villes")
public class VilleController {
    @Autowired
    VilleService villeService;

     @PostMapping(value = "getAll")
    public ResponseEntity getAll(Authentication authentication, @RequestBody int idpays) {
        try{
            List<Ville> villes = villeService.getAllVille(idpays, authentication.getPrincipal().toString());

            return new ResponseEntity<>(villes, HttpStatus.ACCEPTED);
        } catch (Exception exception) {
            return new ResponseEntity<>("vous n'êtes pas connecté" ,HttpStatus.EXPECTATION_FAILED);
        } 


    }

    @PostMapping(value="add")
    public ResponseEntity addPays(Authentication authentication, @RequestBody Ville ville) {
        try {
            List<Ville> villes = villeService.addVille(ville, authentication.getPrincipal().toString());

            return new ResponseEntity<>(villes, HttpStatus.CREATED);
        } catch (DataAccessException exception) {
            return new ResponseEntity<>(exception.getMessage() ,HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping(value="delete")
    public ResponseEntity deleteAgence(Authentication authentication, @RequestBody String id) {
        try {
            int count = villeService.deleteVille(Integer.parseInt(id), authentication.getPrincipal().toString());

            return new ResponseEntity<>(count > 0, HttpStatus.ACCEPTED);
        } catch (DataAccessException exception) {
            return new ResponseEntity<>(exception.getMessage() ,HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping(value="edit")
    public ResponseEntity editPays(Authentication authentication, @RequestBody Ville ville) {
        try {
            int count = villeService.editVille(ville, authentication.getPrincipal().toString());

            return new ResponseEntity<>(count > 0, HttpStatus.ACCEPTED);
        } catch (DataAccessException exception) {
            return new ResponseEntity<>(exception.getMessage() ,HttpStatus.EXPECTATION_FAILED);
        }
    }
    
    
}