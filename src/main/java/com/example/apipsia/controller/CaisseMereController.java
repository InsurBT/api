package com.example.apipsia.controller;

import java.util.List;

import com.example.apipsia.model.CaisseMere.CaisseMere;
import com.example.apipsia.model.CaisseMere.CaisseMereCrud;
import com.example.apipsia.service.CaisseMereService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "CaisseMere")
public class CaisseMereController {

    @Autowired
    private CaisseMereService caissemereService;

    @RequestMapping(value = "listCaisseMere")
    public ResponseEntity list(Authentication authentication) {
        try {
            List<CaisseMere> list = caissemereService.getAllCaisseMeres(authentication.getPrincipal().toString());

            return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
        } catch (Exception exception) {
            return new ResponseEntity<>("vous n'êtes pas connecté" ,HttpStatus.EXPECTATION_FAILED);
        } 

    }

    @PostMapping(value = "getAll")
    public ResponseEntity getAll(Authentication authentication, @RequestBody int idpays) {
        try{
            List<CaisseMere> CAISSEMERES = caissemereService.getAllCaissemere(idpays, authentication.getPrincipal().toString());
            return new ResponseEntity<>(CAISSEMERES, HttpStatus.ACCEPTED);
        } catch (Exception exception) {
            return new ResponseEntity<>("vous n'êtes pas connecté" ,HttpStatus.EXPECTATION_FAILED);
        } 
    }


    @PostMapping(value = "save")
    public ResponseEntity addCaisseMere(Authentication authentication, @RequestBody CaisseMereCrud caisseMere) {
        try {
            List<CaisseMere> list = caissemereService.addCaisseMere(caisseMere, authentication.getPrincipal().toString());

            return new ResponseEntity<>(list, HttpStatus.CREATED);
        } catch (DataAccessException exception) {
            return new ResponseEntity<>(exception.getMessage() ,HttpStatus.EXPECTATION_FAILED);
        }
    }



    @PostMapping(value = "update")
    public ResponseEntity editCaisseMere(Authentication authentication, @RequestBody CaisseMereCrud caisseMere) {
        try {
            int count = caissemereService.editCaisseMere(caisseMere, authentication.getPrincipal().toString());

            return new ResponseEntity<>(count > 0, HttpStatus.ACCEPTED);
        } catch (DataAccessException exception) {
            return new ResponseEntity<>(exception.getMessage() ,HttpStatus.EXPECTATION_FAILED);
        }
    }
    


    @PostMapping(value = "delete")
    public ResponseEntity deleteCaisseMere(Authentication authentication, @RequestBody String id) {
        try {
            int count = caissemereService.deleteCaisseMere(Integer.parseInt(id), authentication.getPrincipal().toString());

            return new ResponseEntity<>(count > 0, HttpStatus.ACCEPTED);
        } catch (DataAccessException exception) {
            return new ResponseEntity<>(exception.getMessage() ,HttpStatus.EXPECTATION_FAILED);
        }
    }






}