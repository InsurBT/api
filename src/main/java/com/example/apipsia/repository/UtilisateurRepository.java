package com.example.apipsia.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;

import com.example.apipsia.model.Utilisateur;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

@Repository
public class UtilisateurRepository {
    public List<Utilisateur> findAll(JdbcTemplate jdbcTemplate) throws DataAccessException {
        String sql = "SELECT utilisateur.*, agence.label AS agence, role.label as role FROM insur.utilisateur, insur.agence, insur.role WHERE code_agence=code and utilisateur.id_role = role.id_role";

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Utilisateur.class));

    }

    public int update(Utilisateur utilisateur, JdbcTemplate jdbcTemplate) throws DataAccessException {
        String sql = "UPDATE insur.utilisateur SET nom=?, nomComplet=?, code_agence=? WHERE cod=?";

        return jdbcTemplate.update(sql, utilisateur.getNom(), utilisateur.getNomComplet(), utilisateur.getCode_agence(),
                utilisateur.getCod());
    }

    public List<Utilisateur> addUtilisateur(Utilisateur utilisateur, JdbcTemplate jdbcTemplate) throws Exception {
        String sql = "INSERT INTO insur.utilisateur (nom, nomComplet, code_agence) VALUES (?, ?,?)";

        int count = jdbcTemplate.update(sql, utilisateur.getNom(), utilisateur.getNomComplet(),
                utilisateur.getCode_agence());

        if (count > 0) {
            return this.findAll(jdbcTemplate);
        } else {
            throw new Exception("l'utilisateur n'a pas ete ajoute");
        }
    }

    public Utilisateur getUtilsateurByNom(String nom, JdbcTemplate jdbcTemplate) {
       // String sql = "SELECT * FROM insur.utilisateur WHERE nom=?";
       String sql = "SELECT utilisateur.*, agence.label AS agence, role.label as role FROM insur.utilisateur, insur.agence, insur.role WHERE code_agence=code and utilisateur.id_role = role.id_role and nom=?";

        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Utilisateur.class), nom);
    }

    public List<Utilisateur> addUser(Utilisateur utilisateur, JdbcTemplate jdbcTemplate) throws SQLException {

        List<SqlParameter> parameters = Arrays.asList(
            new SqlParameter(Types.VARCHAR),  new SqlParameter(Types.VARCHAR),  new SqlParameter(Types.INTEGER),  new SqlParameter(Types.VARCHAR));
            
        jdbcTemplate.call(new CallableStatementCreator(){
        
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                CallableStatement callableStatement = con.prepareCall("{call add_user(?,?,?,?)}");
                callableStatement.setString(1, utilisateur.getNom());
                callableStatement.setString(2, utilisateur.getNomComplet());
                callableStatement.setInt(3, utilisateur.getCode_agence());
                callableStatement.setString(4, utilisateur.getPassword());

                return callableStatement;
            }
        }, parameters);
        return this.findAll(jdbcTemplate);

        
    }
    
    
}