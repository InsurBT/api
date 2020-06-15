package com.example.apipsia.controller;
import com.example.apipsia.service.DirectionRegionalService;
import com.example.apipsia.model.DirectionRegional;

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
@RequestMapping(value = "DirectionRegional")


public class DirectionRegionalController {
    @Autowired
    DirectionRegionalService directionRegionalService;

    @GetMapping(value = "getAll")
    public ResponseEntity getAll(Authentication authentication) {
        try {
            List<DirectionRegional> directionregional = directionRegionalService.getAllDirectionRegional(authentication.getPrincipal().toString());

            return new ResponseEntity<>(directionregional, HttpStatus.ACCEPTED);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage() ,HttpStatus.EXPECTATION_FAILED);
        } 

    }

    @PostMapping(value="add")
    public ResponseEntity addDirectionRegional(Authentication authentication, @RequestBody DirectionRegional directionRegional) {
        try {
            List<DirectionRegional> directionregional = directionRegionalService.addDirectionRegional(directionRegional, authentication.getPrincipal().toString());

            return new ResponseEntity<>(directionregional, HttpStatus.CREATED);
        } catch (DataAccessException exception) {
            return new ResponseEntity<>(exception.getMessage() ,HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping(value="delete")
    public ResponseEntity deleteDirectionRegional(Authentication authentication, @RequestBody String code) {
        try {
            int c = directionRegionalService.deleteDirectionRegional(Integer.parseInt(code), authentication.getPrincipal().toString());

            return new ResponseEntity<>(c > 0, HttpStatus.ACCEPTED);
        } catch (DataAccessException exception) {
            return new ResponseEntity<>(exception.getMessage() ,HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping(value="edit")
    public ResponseEntity editDirectionRegional(Authentication authentication, @RequestBody DirectionRegional directionRegional) {
        try {
            int c = directionRegionalService.editDirectionRegional(directionRegional, authentication.getPrincipal().toString());

            return new ResponseEntity<>(c > 0, HttpStatus.ACCEPTED);
        } catch (DataAccessException exception) {
            return new ResponseEntity<>(exception.getMessage() ,HttpStatus.EXPECTATION_FAILED);
        }
    }

}