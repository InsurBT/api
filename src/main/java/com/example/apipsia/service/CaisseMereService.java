package com.example.apipsia.service;

import java.util.List;

import com.example.apipsia.model.CaisseMere.CaisseMere;
import com.example.apipsia.model.CaisseMere.CaisseMereCrud;
import com.example.apipsia.repository.CaisseMereRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CaisseMereService {
    @Autowired
    LoginService loginService;

    @Autowired
     CaisseMereRepository caisseMereRepository;

     
    public List<CaisseMere> getAllCaisseMeres(String nom) throws DataAccessException {
        JdbcTemplate jdbcTemplate = loginService.getActiveConnnections().get(nom);

        if (!jdbcTemplate.equals(null)) {
            return caisseMereRepository.list(jdbcTemplate);
        }
        return null;
    }

    public int editCaisseMere(CaisseMereCrud caisseMere, String nom) throws DataAccessException {
        JdbcTemplate jdbcTemplate = loginService.getActiveConnnections().get(nom);

        if (!jdbcTemplate.equals(null)) {
            return caisseMereRepository.update(caisseMere, jdbcTemplate);
        }
        return -1;
    }
    public int deleteCaisseMere(int id, String nom) throws DataAccessException {
        JdbcTemplate jdbcTemplate = loginService.getActiveConnnections().get(nom);

        if (!jdbcTemplate.equals(null)) {
            return caisseMereRepository.delete(id, jdbcTemplate);
        }

        return -1;
    }

    public List<CaisseMere> addCaisseMere(CaisseMereCrud caisseMere, String nom) throws DataAccessException{
        JdbcTemplate jdbcTemplate = loginService.getActiveConnnections().get(nom);

        if (!jdbcTemplate.equals(null)) {
            return caisseMereRepository.save(caisseMere, jdbcTemplate);
        }

        return null;
    }


}