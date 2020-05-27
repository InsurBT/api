package com.example.apipsia.repository;

import java.util.List;

import com.example.apipsia.model.Utilisateur;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UtilisateurRepository {
    public List<Utilisateur> findAll(JdbcTemplate jdbcTemplate) throws DataAccessException {
        String sql = "SELECT utilisateur.*, label AS agence FROM utilisateur, agence WHERE code_agence=code";

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Utilisateur.class));
    
    }

    public int update(Utilisateur utilisateur, JdbcTemplate jdbcTemplate) throws DataAccessException {
        String sql = "UPDATE utilisateur SET nom=?, nomComplet=?, code_agence=? WHERE cod=?";

        return jdbcTemplate.update(
            sql,
            utilisateur.getNom(),
            utilisateur.getNomComplet(),
            utilisateur.getCode_agence(),
            utilisateur.getCod()
        );
    }

    public List<Utilisateur> addUtilisateur(Utilisateur utilisateur, JdbcTemplate jdbcTemplate) throws Exception {
        String sql = "INSERT INTO utilisateur (nom, nomComplet, code_agence) VALUES (?, ?,?)";

        int count = jdbcTemplate.update(sql,
                utilisateur.getNom(),
                utilisateur.getNomComplet(),
                utilisateur.getCode_agence()
            );

        if (count > 0) {
            return this.findAll(jdbcTemplate);
        } else {
            throw new Exception("l'utilisateur n'a pas ete ajoute");
        }
    }

    public Utilisateur getUtilsateurByNom(String nom, JdbcTemplate jdbcTemplate) {
        String sql = "SELECT * FROM utilisateur WHERE nom=?";

        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Utilisateur.class), nom);
    }
}