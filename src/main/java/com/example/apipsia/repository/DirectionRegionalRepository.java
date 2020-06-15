package com.example.apipsia.repository;

import java.util.List;

import com.example.apipsia.model.DirectionRegional;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DirectionRegionalRepository {
    public List<DirectionRegional> findAll(final JdbcTemplate jdbcTemplate) throws DataAccessException {
        final String sql = "SELECT * FROM DirectionRegional";

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(DirectionRegional.class));
    
    }

    public int deleteOne(final int code, final JdbcTemplate jdbcTemplate) {
        final String sql = "DELETE FROM DirectionRegional WHERE code=?";

        return jdbcTemplate.update(sql, code);
    }

    public int update(final DirectionRegional directionregional, final JdbcTemplate jdbcTemplate) throws DataAccessException {
        final String sql = "UPDATE DirectionRegional SET Adresse=?, Designation=?, Ville=? WHERE code=?";

        return jdbcTemplate.update(
            sql,
            directionregional.getAdresse(),
            directionregional.getDesignation(),
            directionregional.getVille(),
            directionregional.getCode());
    }

    public List<DirectionRegional> save(final DirectionRegional directionregional, final JdbcTemplate jdbcTemplate) throws DataAccessException {
        final String sql = "INSERT INTO DirectionRegional (Adresse, Designation, Ville) VALUES (?, ?, ?)";

        jdbcTemplate.update(
            sql,
            directionregional.getAdresse(),
            directionregional.getDesignation(),
            directionregional.getVille()
           
        );

        return this.findAll(jdbcTemplate);
    }

}