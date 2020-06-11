package com.example.apipsia.repository;

import java.util.List;

import com.example.apipsia.model.Ville;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class VilleRepository {
    public List<Ville> findAllByPays(int idpays,JdbcTemplate jdbcTemplate) throws DataAccessException {
        String sql = "select ville.Design AS nom , ville.Code AS id ,ville.codpays from ville where ville.CodPays="+idpays;
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Ville.class));
    
    }

    public int deleteOne(int id, JdbcTemplate jdbcTemplate) {
        String sql = "DELETE FROM ville WHERE id=?";

        return jdbcTemplate.update(sql, id);
    }

    public int update(Ville ville, JdbcTemplate jdbcTemplate) throws DataAccessException {
        String sql = "UPDATE ville SET design=? ,codpays=?  WHERE id=?";

        return jdbcTemplate.update(
            sql,
            ville.getNom(),
            ville.getCodpays(),
            ville.getId()
        );
    }

    public List<Ville> save(Ville ville, JdbcTemplate jdbcTemplate) throws DataAccessException {
        String sql = "INSERT INTO ville (nom,codpays) VALUES (?,?)";

        jdbcTemplate.update(
            sql,
            ville.getNom(),
            ville.getCodpays()
        );

        return this.findAll(jdbcTemplate);
    }

    private List<Ville> findAll(JdbcTemplate jdbcTemplate) {
        String sql = "select ville.Design AS nom , ville.Code AS id,codpays from ville ";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Ville.class));
    }
}