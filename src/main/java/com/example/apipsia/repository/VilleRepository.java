package com.example.apipsia.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import com.example.apipsia.model.Ville;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class VilleRepository {
    public List<Ville> findAllByPays(int idpays,JdbcTemplate jdbcTemplate) throws DataAccessException {
        String sql = "select ville.label AS nom , ville.id_ville AS id ,ville.id_pays As codpays from insur.ville where ville.id_pays="+idpays;
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Ville.class));
    
    }

    public int deleteOne(int id, JdbcTemplate jdbcTemplate) {
        String sql = "DELETE FROM insur.ville WHERE id_ville=?";

        return jdbcTemplate.update(sql, id);
    }

    public int update(Ville ville, JdbcTemplate jdbcTemplate) throws DataAccessException {
        String sql = "UPDATE insur.ville SET label=? ,id_pays=?  WHERE id_ville=?";

        return jdbcTemplate.update(
            sql,
            ville.getNom(),
            ville.getCodpays(),
            ville.getId()
        );
    }

    public List<Ville> save(Ville ville, JdbcTemplate jdbcTemplate) throws DataAccessException {
        String sql = "INSERT INTO insur.ville (label,id_pays) VALUES (?,?)";

        jdbcTemplate.update(
            sql,
            ville.getNom(),
            ville.getCodpays()
        );

        return this.findAll(jdbcTemplate);
    }

    private List<Ville> findAll(JdbcTemplate jdbcTemplate) {
        String sql = "select ville.label AS nom , ville.id_ville AS id,id_pays from insur.ville ";
        return
        
        jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Ville.class));
    }

    /*private List<Ville> getville(JdbcTemplate jdbcTemplate) {
        Connection conn;
        CallableStatement cs = conn.prepareCall("{call get_my_list(?)}");
        cs.registerOutParameter(1,  Types.REF_CURSOR);
        cs.executeQuery();
        ResultSet rs=(ResultSet)cs.getObject(1);
        
        while(rs.next())
        {
            System.out.print("code : "+rs.getString(1));
            System.out.print(", label : "+rs.getString(2));
        }
        System.out.println("Done");
        
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Ville.class));
    }*/

    
}
