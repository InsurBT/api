package com.example.apipsia.repository;

import java.util.List;

import com.example.apipsia.model.CaisseMere.CaisseMere;
import com.example.apipsia.model.CaisseMere.CaisseMereCrud;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CaisseMereRepository {

    public List<CaisseMere> list(JdbcTemplate jdbcTemplate) throws DataAccessException  {
          String sql = "SELECT CaisseMere.code , CaisseMere.nom, CaisseMere.adresse, pays.id as  id , pays.nom as  pays FROM insur.CaisseMere , insur.pays WHERE pays.id = CaisseMere.pays" ;
         List<CaisseMere> listCaisseMere = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(CaisseMere.class));
         return listCaisseMere;
     }

     
    public List<CaisseMere> save(CaisseMereCrud CaisseMereAjout , JdbcTemplate jdbcTemplate) throws DataAccessException  {
        String sql = "INSERT INTO insur.caissemere (nom, adresse, pays) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,CaisseMereAjout.getNom(),
                CaisseMereAjout.getAdresse(), CaisseMereAjout.getId());

        return this.list(jdbcTemplate);
    }

    public int delete(int code , JdbcTemplate jdbcTemplate)  {

        String sql = "DELETE FROM insur.caissemere WHERE code = ?";
        return  jdbcTemplate.update(sql, code);
    }

    public int update(CaisseMereCrud caisseMere , JdbcTemplate jdbcTemplate) throws DataAccessException {
         String sql = "update insur.caissemere set nom = ?, adresse = ? , pays = ? WHERE  code = ?";
        int count = jdbcTemplate.update(sql, caisseMere.getNom(), caisseMere.getAdresse(), caisseMere.getId(),
                caisseMere.getCode());
        return count;
    }

    public List<CaisseMere> Rechercher(String str , JdbcTemplate jdbcTemplate) {
        String sql = "Call `Search3`" + str;
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(CaisseMere.class));

    }
}