package com.example.apipsia.service;

import com.example.apipsia.repository.UtilisateurRepository;

import java.util.List;

import com.example.apipsia.model.Utilisateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService {
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    LoginService loginService;

    public List<Utilisateur> getAllUtilisateurs(String nom) throws Exception {
        JdbcTemplate jdbcTemplate = loginService.getActiveConnnections().get(nom);

        if (!jdbcTemplate.equals(null)) {
            return utilisateurRepository.findAll(jdbcTemplate);
        }

        return null;
    }

    public Utilisateur getUtilisateurByNom(String nom) throws Exception {
        JdbcTemplate jdbcTemplate = loginService.getActiveConnnections().get(nom);

        if (!jdbcTemplate.equals(null)) {
            return utilisateurRepository.getUtilsateurByNom(nom, jdbcTemplate);
        }

        return null;
    }

    public List<Utilisateur> ajouterUtilisateur(Utilisateur utilisateur, String password, String nom) throws Exception {
        JdbcTemplate jdbcTemplate = loginService.getActiveConnnections().get(nom);
        
        // String sql = "CREATE USER ? IDENTIFIED BY ?";
        // int c = jdbcTemplate.update(sql, utilisateur.getNom(), password);

        return utilisateurRepository.addUtilisateur(utilisateur, jdbcTemplate);
        
    }

    public int editUtilisateur(Utilisateur utilisateur, String nom) throws DataAccessException {
        JdbcTemplate jdbcTemplate = loginService.getActiveConnnections().get(nom);

        if (!jdbcTemplate.equals(null)) {
            return utilisateurRepository.update(utilisateur, jdbcTemplate);
        }

        return -1;
    }

}