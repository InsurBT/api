package com.example.apipsia.service;

import java.util.List;

import com.example.apipsia.model.CaisseEtrangere.CaisseEtrangere;
import com.example.apipsia.model.CaisseEtrangere.CaisseEtrangereCrud;
import com.example.apipsia.repository.CaisseEtrangereRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CaisseEtrangereService {
    @Autowired
    LoginService loginService;

    @Autowired
     CaisseEtrangereRepository caisseEtrangereRepository;

     public List<CaisseEtrangere> getAllCaisseEtrangeres(String nom) throws DataAccessException {
        JdbcTemplate jdbcTemplate = loginService.getActiveConnnections().get(nom);

        if (!jdbcTemplate.equals(null)) {
            return caisseEtrangereRepository.list(jdbcTemplate);
        }
        return null;
    }

    public int editCaisseEtrangere(CaisseEtrangereCrud caisseEtrangere, String nom) throws DataAccessException {
        JdbcTemplate jdbcTemplate = loginService.getActiveConnnections().get(nom);

        if (!jdbcTemplate.equals(null)) {
            return caisseEtrangereRepository.update(caisseEtrangere, jdbcTemplate);
        }
        return -1;
    }
    public int deleteCaisseEtrangere(int id, String nom) throws DataAccessException {
        JdbcTemplate jdbcTemplate = loginService.getActiveConnnections().get(nom);

        if (!jdbcTemplate.equals(null)) {
            return caisseEtrangereRepository.delete(id, jdbcTemplate);
        }

        return -1;
    }

    public List<CaisseEtrangere> addCaisseEtrangere(CaisseEtrangereCrud caisseEtrangere, String nom) throws DataAccessException{
        JdbcTemplate jdbcTemplate = loginService.getActiveConnnections().get(nom);

        if (!jdbcTemplate.equals(null)) {
            return caisseEtrangereRepository.save(caisseEtrangere, jdbcTemplate);
        }

        return null;
    }

    
}