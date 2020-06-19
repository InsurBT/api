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
    VilleRepository villeRepository;

    public List<Ville> getAllVille(int idpays,String nom) throws DataAccessException {
        JdbcTemplate jdbcTemplate = loginService.getActiveConnnections().get(nom);

        if (!jdbcTemplate.equals(null)) {
            return villeRepository.findAllByPays(idpays,jdbcTemplate);
        }

        return null;
    }
    

    public int editVille(Ville ville, String nom) throws DataAccessException {
        JdbcTemplate jdbcTemplate = loginService.getActiveConnnections().get(nom);

        if (!jdbcTemplate.equals(null)) {
            return villeRepository.update(ville, jdbcTemplate);
        }

        return -1;
    }

    public int deleteVille(int id, String nom) throws DataAccessException {
        JdbcTemplate jdbcTemplate = loginService.getActiveConnnections().get(nom);

        if (!jdbcTemplate.equals(null)) {
            return villeRepository.deleteOne(id, jdbcTemplate);
        }

        return -1;
    }

    public List<Ville> addVille(Ville ville, String nom) throws DataAccessException{
        JdbcTemplate jdbcTemplate = loginService.getActiveConnnections().get(nom);

        if (!jdbcTemplate.equals(null)) {
            return villeRepository.save(ville,jdbcTemplate);
        }

        return null;
    }
    
}