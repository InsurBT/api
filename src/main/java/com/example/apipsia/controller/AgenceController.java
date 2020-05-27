package com.example.apipsia.controller;

import com.example.apipsia.service.AgenceService;
import com.example.apipsia.model.Agence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
@RequestMapping(value = "agence")
public class AgenceController {
    @Autowired
    AgenceService agenceService;

    @GetMapping(value = "getAll")
    public ResponseEntity getAll(Authentication authentication) {
        try {
            List<Agence> agences = agenceService.getAllAgences(authentication.getPrincipal().toString());

            return new ResponseEntity<>(agences, HttpStatus.ACCEPTED);
        } catch (Exception exception) {
            return new ResponseEntity<>("vous n'êtes pas connecté" ,HttpStatus.EXPECTATION_FAILED);
        } 

    }

    @PostMapping(value="add")
    public ResponseEntity addAgence(Authentication authentication, @RequestBody Agence agence) {
        try {
            List<Agence> agences = agenceService.addAgence(agence, authentication.getPrincipal().toString());

            return new ResponseEntity<>(agences, HttpStatus.CREATED);
        } catch (DataAccessException exception) {
            return new ResponseEntity<>(exception.getMessage() ,HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping(value="delete")
    public ResponseEntity deleteAgence(Authentication authentication, @RequestBody String id) {
        try {
            int c = agenceService.deleteAgence(Integer.parseInt(id), authentication.getPrincipal().toString());

            return new ResponseEntity<>(c > 0, HttpStatus.ACCEPTED);
        } catch (DataAccessException exception) {
            return new ResponseEntity<>(exception.getMessage() ,HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping(value="edit")
    public ResponseEntity editAgence(Authentication authentication, @RequestBody Agence agence) {
        try {
            int c = agenceService.editAgence(agence, authentication.getPrincipal().toString());

            return new ResponseEntity<>(c > 0, HttpStatus.ACCEPTED);
        } catch (DataAccessException exception) {
            return new ResponseEntity<>(exception.getMessage() ,HttpStatus.EXPECTATION_FAILED);
        }
    }
    
}