package com.example.apipsia.service;

import java.util.List;

import com.example.apipsia.repository.AgenceRepository;
import com.example.apipsia.model.Agence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class AgenceService {
    @Autowired
    LoginService loginService;

    @Autowired
    AgenceRepository agenceRepository;

    public List<Agence> getAllAgences(String nom) throws DataAccessException {
        JdbcTemplate jdbcTemplate = loginService.getActiveConnnections().get(nom);

        if (!jdbcTemplate.equals(null)) {
            return agenceRepository.findAll(jdbcTemplate);
        }

        return null;
    }

    public int editAgence(Agence agence, String nom) throws DataAccessException {
        JdbcTemplate jdbcTemplate = loginService.getActiveConnnections().get(nom);

        if (!jdbcTemplate.equals(null)) {
            return agenceRepository.update(agence, jdbcTemplate);
        }

        return -1;
    }

    public int deleteAgence(int id, String nom) throws DataAccessException {
        JdbcTemplate jdbcTemplate = loginService.getActiveConnnections().get(nom);

        if (!jdbcTemplate.equals(null)) {
            return agenceRepository.deleteOne(id, jdbcTemplate);
        }

        return -1;
    }

    public List<Agence> addAgence(Agence agence, String nom) throws DataAccessException{
        JdbcTemplate jdbcTemplate = loginService.getActiveConnnections().get(nom);

        if (!jdbcTemplate.equals(null)) {
            return agenceRepository.save(agence, jdbcTemplate);
        }

        return null;
    }
}