package com.example.apipsia.controller;

import com.example.apipsia.service.PrestationService;
import com.example.apipsia.model.Prestation;

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
@RequestMapping(value = "prestation")
public class PrestationController {
    @Autowired
    PrestationService prestationService;

    @GetMapping(value = "getAll")
    public ResponseEntity getAll(Authentication authentication) {
        try {
            List<Prestation> prestations = prestationService.getAllPrestations(authentication.getPrincipal().toString());

            return new ResponseEntity<>(prestations, HttpStatus.ACCEPTED);
        } catch (Exception exception) {
            return new ResponseEntity<>("vous n'êtes pas connecté" ,HttpStatus.EXPECTATION_FAILED);
        } 

    }

    @PostMapping(value="add")
    public ResponseEntity addPrestation(Authentication authentication, @RequestBody Prestation prestation) {
        try {
            List<Prestation> prestations = prestationService.addPrestation(prestation, authentication.getPrincipal().toString());

            return new ResponseEntity<>(prestations, HttpStatus.CREATED);
        } catch (DataAccessException exception) {
            return new ResponseEntity<>(exception.getMessage() ,HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping(value="delete")
    public ResponseEntity deletePrestation(Authentication authentication, @RequestBody String id) {
        try {
            int c = prestationService.deletePrestation(Integer.parseInt(id), authentication.getPrincipal().toString());

            return new ResponseEntity<>(c > 0, HttpStatus.ACCEPTED);
        } catch (DataAccessException exception) {
            return new ResponseEntity<>(exception.getMessage() ,HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping(value="edit")
    public ResponseEntity editPrestation(Authentication authentication, @RequestBody Prestation prestation) {
        try {
            int c = prestationService.editPrestation(prestation, authentication.getPrincipal().toString());

            return new ResponseEntity<>(c > 0, HttpStatus.ACCEPTED);
        } catch (DataAccessException exception) {
            return new ResponseEntity<>(exception.getMessage() ,HttpStatus.EXPECTATION_FAILED);
        }
    }
    
}