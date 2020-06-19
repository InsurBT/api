package com.example.apipsia.repository;

import java.util.List;

import com.example.apipsia.model.Agence;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AgenceRepository {
    public List<Agence> findAll(JdbcTemplate jdbcTemplate) throws DataAccessException {
        String sql = "SELECT * FROM insur.agence";

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Agence.class));
    
    }

    public Agence findById(long id, JdbcTemplate jdbcTemplate) throws DataAccessException {
        String sql = "SELECT * FROM insur.agence WHERE id=?";

        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Agence.class), id);
    }

    public int deleteOne(int id, JdbcTemplate jdbcTemplate) {
        String sql = "DELETE FROM insur.agence WHERE id=?";

        return jdbcTemplate.update(sql, id);
    }

    public int update(Agence agence, JdbcTemplate jdbcTemplate) throws DataAccessException {
        String sql = "UPDATE insur.agence SET label=? WHERE id=?";

        return jdbcTemplate.update(
            sql,
            agence.getLabel(),
            agence.getCode()
        );
    }

    public List<Agence> save(Agence agence, JdbcTemplate jdbcTemplate) throws DataAccessException {
        String sql = "INSERT INTO insur.agence (label) VALUES (?)";

        jdbcTemplate.update(
            sql,
            agence.getLabel()
        );

        return this.findAll(jdbcTemplate);
    }
}