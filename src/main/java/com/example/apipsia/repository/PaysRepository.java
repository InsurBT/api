package com.example.apipsia.repository;

import java.util.List;

import com.example.apipsia.model.Pays;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PaysRepository {
    public List<Pays> findAll(JdbcTemplate jdbcTemplate) throws DataAccessException {
        String sql = "SELECT id_pays AS  id ,label  FROM insur.pays";

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Pays.class));
    
    }

    public Pays findById(long id, JdbcTemplate jdbcTemplate) throws DataAccessException {
        String sql = "SELECT * FROM insur.pays WHERE id_pays=?";

        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Pays.class), id);
    }

    public int deleteOne(int id, JdbcTemplate jdbcTemplate) {
        String sql = "DELETE FROM insur.pays WHERE id_pays=?";

        return jdbcTemplate.update(sql, id);
    }

    public int update(Pays pays, JdbcTemplate jdbcTemplate) throws DataAccessException {
        String sql = "UPDATE insur.pays SET label=? WHERE id_pays=?";

        return jdbcTemplate.update(
            sql,
            pays.getLabel(),
            pays.getId()
        );
    }

    public List<Pays> save(Pays pays, JdbcTemplate jdbcTemplate) throws DataAccessException {
        String sql = "INSERT INTO insur.pays (label) VALUES (?)";

        jdbcTemplate.update(
            sql,
            pays.getLabel()
        );

        return this.findAll(jdbcTemplate);
    }
    
}