package com.example.apipsia.service;

import java.util.List;

import com.example.apipsia.model.Pays;
import com.example.apipsia.repository.PaysRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaysService {
    @Autowired
    LoginService loginService;

    @Autowired
    PaysRepository paysRepository;

    public List<Pays> getAllPays(String nom) throws DataAccessException {
        JdbcTemplate jdbcTemplate = loginService.getActiveConnnections().get(nom);

        if (!jdbcTemplate.equals(null)) {
            return paysRepository.findAll(jdbcTemplate);
        }

        return null;
    }

    public int editPays(Pays pays, String nom) throws DataAccessException {
        JdbcTemplate jdbcTemplate = loginService.getActiveConnnections().get(nom);

        if (!jdbcTemplate.equals(null)) {
            return paysRepository.update(pays, jdbcTemplate);
        }

        return -1;
    }

    public int deletePays(int id, String nom) throws DataAccessException {
        JdbcTemplate jdbcTemplate = loginService.getActiveConnnections().get(nom);

        if (!jdbcTemplate.equals(null)) {
            return paysRepository.deleteOne(id, jdbcTemplate);
        }

        return -1;
    }

    public List<Pays> addPays(Pays pays, String nom) throws DataAccessException{
        JdbcTemplate jdbcTemplate = loginService.getActiveConnnections().get(nom);

        if (!jdbcTemplate.equals(null)) {
            return paysRepository.save(pays, jdbcTemplate);
        }

        return null;
    }
    
}