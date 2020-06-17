package com.example.apipsia.controller;

import java.util.List;

import com.example.apipsia.model.Pays;
import com.example.apipsia.service.PaysService;

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
@RequestMapping(value = "pays")
public class PaysController {
    @Autowired
    PaysService paysService;

    @GetMapping(value = "getAll")
    public ResponseEntity getAll(Authentication authentication) {
        try {
            List<Pays> pays = paysService.getAllPays(authentication.getPrincipal().toString());

            return new ResponseEntity<>(pays, HttpStatus.ACCEPTED);
        } catch (Exception exception) {
            return new ResponseEntity<>("vous n'êtes pas connecté" ,HttpStatus.EXPECTATION_FAILED);
        } 

    }

    @PostMapping(value="add")
    public ResponseEntity addPays(Authentication authentication, @RequestBody Pays paysA) {
        try {
            List<Pays> pays = paysService.addPays(paysA, authentication.getPrincipal().toString());

            return new ResponseEntity<>(pays, HttpStatus.CREATED);
        } catch (DataAccessException exception) {
            return new ResponseEntity<>(exception.getMessage() ,HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping(value="delete")
    public ResponseEntity deleteAgence(Authentication authentication, @RequestBody String id) {
        try {
            int count = paysService.deletePays(Integer.parseInt(id), authentication.getPrincipal().toString());

            return new ResponseEntity<>(count > 0, HttpStatus.ACCEPTED);
        } catch (DataAccessException exception) {
            return new ResponseEntity<>(exception.getMessage() ,HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping(value="edit")
    public ResponseEntity editPays(Authentication authentication, @RequestBody Pays paysE) {
        try {
            int count = paysService.editPays(paysE, authentication.getPrincipal().toString());

            return new ResponseEntity<>(count > 0, HttpStatus.ACCEPTED);
        } catch (DataAccessException exception) {
            return new ResponseEntity<>(exception.getMessage() ,HttpStatus.EXPECTATION_FAILED);
        }
    }
    
}