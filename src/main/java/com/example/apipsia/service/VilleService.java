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
<<<<<<< HEAD
    VilleRepository villeRepository = new VilleRepository() ;

    public List<Ville> getAllAgences(String nom) throws DataAccessException {
        JdbcTemplate jdbcTemplate = loginService.getActiveConnnections().get(nom);

        if (!jdbcTemplate.equals(null)) {
            return villeRepository.findAll(jdbcTemplate);
=======
    VilleRepository villeRepository;

    public List<Ville> getAllVille(int idpays,String nom) throws DataAccessException {
        JdbcTemplate jdbcTemplate = loginService.getActiveConnnections().get(nom);

        if (!jdbcTemplate.equals(null)) {
            return villeRepository.findAllByPays(idpays,jdbcTemplate);
>>>>>>> 53873ec6853ee0155c4e96eb784a6045f1f400df
        }

        return null;
    }
<<<<<<< HEAD
    public String getville(String nom) throws DataAccessException {
         
        JdbcTemplate jdbcTemplate = loginService.getActiveConnnections().get(nom);

        if (!jdbcTemplate.equals(null)) {


            return villeRepository.find();  
=======

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
>>>>>>> 53873ec6853ee0155c4e96eb784a6045f1f400df
        }

        return null;
    }
    
}