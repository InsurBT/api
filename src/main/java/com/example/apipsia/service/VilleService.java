package com.example.apipsia.service;

import java.util.List;

import com.example.apipsia.model.Ville;
import com.example.apipsia.repository.VilleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class VilleService {
    @Autowired
    LoginService loginService;

    @Autowired
    VilleRepository villeRepository = new VilleRepository() ;

    public List<Ville> getAllAgences(String nom) throws DataAccessException {
        JdbcTemplate jdbcTemplate = loginService.getActiveConnnections().get(nom);

        if (!jdbcTemplate.equals(null)) {
            return villeRepository.findAll(jdbcTemplate);
        }

        return null;
    }
    public String getville(String nom) throws DataAccessException {
         
        JdbcTemplate jdbcTemplate = loginService.getActiveConnnections().get(nom);

        if (!jdbcTemplate.equals(null)) {


            return villeRepository.find();  
        }

        return null;
    }
    
}