package com.example.apipsia.controller;

import java.util.List;

import com.example.apipsia.model.CaisseEtrangere.CaisseEtrangere;
import com.example.apipsia.model.CaisseEtrangere.CaisseEtrangereCrud;
import com.example.apipsia.service.CaisseEtrangereService;

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
@RequestMapping(value = "CaisseEtrangere")
public class CaisseEtrangereController {
    @Autowired
    private CaisseEtrangereService caisseEtrangereService;

    @RequestMapping(value = "listCaisseEtrangere")
    public ResponseEntity list(Authentication authentication) {
        try {
            List<CaisseEtrangere> list = caisseEtrangereService.getAllCaisseEtrangeres(authentication.getPrincipal().toString());

            return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
        } catch (Exception exception) {
            return new ResponseEntity<>("vous n'êtes pas connecté" ,HttpStatus.EXPECTATION_FAILED);
        } 

    }



    @PostMapping(value = "save")
    public ResponseEntity addCaisseEtrangere(Authentication authentication, @RequestBody CaisseEtrangereCrud caisseEtrangere) {
        try {
            List<CaisseEtrangere> list = caisseEtrangereService.addCaisseEtrangere(caisseEtrangere, authentication.getPrincipal().toString());

            return new ResponseEntity<>(list, HttpStatus.CREATED);
        } catch (DataAccessException exception) {
            return new ResponseEntity<>(exception.getMessage() ,HttpStatus.EXPECTATION_FAILED);
        }
    }



    @PostMapping(value = "update")
    public ResponseEntity editCaisseEtrangere(Authentication authentication, @RequestBody CaisseEtrangereCrud caisseEtrangere) {
        try {
            int count = caisseEtrangereService.editCaisseEtrangere(caisseEtrangere, authentication.getPrincipal().toString());

            return new ResponseEntity<>(count > 0, HttpStatus.ACCEPTED);
        } catch (DataAccessException exception) {
            return new ResponseEntity<>(exception.getMessage() ,HttpStatus.EXPECTATION_FAILED);
        }
    }
    


    @PostMapping(value = "delete")
    public ResponseEntity deleteCaisseEtrangere(Authentication authentication, @RequestBody String id) {
        try {
            int count = caisseEtrangereService.deleteCaisseEtrangere(Integer.parseInt(id), authentication.getPrincipal().toString());

            return new ResponseEntity<>(count > 0, HttpStatus.ACCEPTED);
        } catch (DataAccessException exception) {
            return new ResponseEntity<>(exception.getMessage() ,HttpStatus.EXPECTATION_FAILED);
        }
    }


    
}