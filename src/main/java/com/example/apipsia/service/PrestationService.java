package com.example.apipsia.service;

import java.util.List;

import com.example.apipsia.repository.PrestationRepository;
import com.example.apipsia.model.Prestation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class PrestationService {
    @Autowired
    LoginService loginService;

    @Autowired
    PrestationRepository prestationRepository;

    public List<Prestation> getAllPrestations(String nom) throws DataAccessException {
        JdbcTemplate jdbcTemplate = loginService.getActiveConnnections().get(nom);

        if (!jdbcTemplate.equals(null)) {
            return prestationRepository.findAll(jdbcTemplate);
        }

        return null;
    }

    public int editPrestation(Prestation prestation, String nom) throws DataAccessException {
        JdbcTemplate jdbcTemplate = loginService.getActiveConnnections().get(nom);

        if (!jdbcTemplate.equals(null)) {
            return prestationRepository.update(prestation, jdbcTemplate);
        }

        return -1;
    }

    public int deletePrestation(int id, String nom) throws DataAccessException {
        JdbcTemplate jdbcTemplate = loginService.getActiveConnnections().get(nom);

        if (!jdbcTemplate.equals(null)) {
            return prestationRepository.deleteOne(id, jdbcTemplate);
        }

        return -1;
    }

    public List<Prestation> addPrestation(Prestation prestation, String nom) throws DataAccessException{
        JdbcTemplate jdbcTemplate = loginService.getActiveConnnections().get(nom);

        if (!jdbcTemplate.equals(null)) {
            return prestationRepository.save(prestation, jdbcTemplate);
        }

        return null;
    }
}