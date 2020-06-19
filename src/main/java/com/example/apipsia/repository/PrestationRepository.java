package com.example.apipsia.repository;

import java.util.List;

import com.example.apipsia.model.Prestation;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PrestationRepository {
    public List<Prestation> findAll(JdbcTemplate jdbcTemplate) throws DataAccessException {
        String sql = "SELECT * FROM insur.prestation";

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Prestation.class));
    
    }

    public int deleteOne(int id, JdbcTemplate jdbcTemplate) {
        String sql = "DELETE FROM insur.prestation WHERE id=?";

        return jdbcTemplate.update(sql, id);
    }

    public int update(Prestation prestation, JdbcTemplate jdbcTemplate) throws DataAccessException {
        String sql = "UPDATE insur.prestation SET type=?, nbrActes=?, montantPaye=?, montantEngage=? WHERE id=?";

        return jdbcTemplate.update(
            sql,
            prestation.getType(),
            prestation.getNbrActes(),
            prestation.getMontantPaye(),
            prestation.getMontantEngage(),
            prestation.getId()
        );
    }

    public List<Prestation> save(Prestation prestation, JdbcTemplate jdbcTemplate) throws DataAccessException {
        String sql = "INSERT INTO insur.prestation (type, nbrActes, montantPaye, montantEngage) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(
            sql,
            prestation.getType(),
            prestation.getNbrActes(),
            prestation.getMontantPaye(),
            prestation.getMontantEngage()
        );

        return this.findAll(jdbcTemplate);
    }
}