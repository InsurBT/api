package com.example.apipsia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.apipsia.model.DirectionRegional;
import com.example.apipsia.repository.DirectionRegionalRepository;

@Service
public class DirectionRegionalService {
    @Autowired
    LoginService loginService;

    @Autowired
    DirectionRegionalRepository directionRegionalRepository;

    public List<DirectionRegional> getAllDirectionRegional(String nom) throws DataAccessException {
        JdbcTemplate jdbcTemplate = loginService.getActiveConnnections().get(nom);

        if (!jdbcTemplate.equals(null)) {
            return directionRegionalRepository.findAll(jdbcTemplate);
        }

        return null;
    }

    public int editDirectionRegional(DirectionRegional directionregional, String nom) throws DataAccessException {
        JdbcTemplate jdbcTemplate = loginService.getActiveConnnections().get(nom);

        if (!jdbcTemplate.equals(null)) {
            return directionRegionalRepository.update(directionregional, jdbcTemplate);
        }

        return -1;
    }

    public int deleteDirectionRegional(int code, String nom) throws DataAccessException {
        JdbcTemplate jdbcTemplate = loginService.getActiveConnnections().get(nom);

        if (!jdbcTemplate.equals(null)) {
            return directionRegionalRepository.deleteOne(code, jdbcTemplate);
        }

        return -1;
    }

    public List<DirectionRegional> addDirectionRegional(DirectionRegional directionregional, String nom) throws DataAccessException{
        JdbcTemplate jdbcTemplate = loginService.getActiveConnnections().get(nom);

        if (!jdbcTemplate.equals(null)) {
            return directionRegionalRepository.save(directionregional, jdbcTemplate);
        }

        return null;
    }
}