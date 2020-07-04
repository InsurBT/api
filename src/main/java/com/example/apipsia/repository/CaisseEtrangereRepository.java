package com.example.apipsia.repository;

import java.util.List;

import com.example.apipsia.model.CaisseEtrangere.CaisseEtrangere;
import com.example.apipsia.model.CaisseEtrangere.CaisseEtrangereCrud;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CaisseEtrangereRepository {
    public List<CaisseEtrangere> list(JdbcTemplate jdbcTemplate) throws DataAccessException  {
        String sql = "SELECT CaisseEtrangere.code, CaisseEtrangere.nom, CaisseEtrangere.adresse, pays.id_pays AS idpays , pays.label AS pays ,ville.id_ville AS id , ville.label AS ville ,CaisseEtrangere.tel AS telephone ,CaisseEtrangere.fax,CaisseEtrangere.email  FROM insur.CaisseEtrangere , insur.pays , insur.ville WHERE ville.id_ville =CaisseEtrangere.id_ville AND ville.id_pays =pays.id_pays" ;
       List<CaisseEtrangere> listCaisseEtrangeres = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(CaisseEtrangere.class));
       return listCaisseEtrangeres;
   }

   public List<CaisseEtrangere> findAllByPaysVille(int idville,JdbcTemplate jdbcTemplate) throws DataAccessException {
    String sql = "SELECT CaisseEtrangere.code, CaisseEtrangere.nom, CaisseEtrangere.adresse, pays.id_pays AS idpays , pays.label AS pays ,ville.id_ville AS id , ville.label AS ville ,CaisseEtrangere.tel AS telephone ,CaisseEtrangere.fax,CaisseEtrangere.email   FROM insur.CaisseEtrangere , insur.pays , insur.ville WHERE ville.id_ville =CaisseEtrangere.id_ville  AND ville.id_pays =pays.id_pays and CaisseEtrangere.id_ville ="+idville;
    return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(CaisseEtrangere.class));
}
   
  public List<CaisseEtrangere> save(CaisseEtrangereCrud CaisseEtrangereAjout , JdbcTemplate jdbcTemplate) throws DataAccessException  {
      String sql = "INSERT INTO insur.caisseetrangere (nom,adresse,id_ville,id_pays,tel,fax,email) VALUES (?,?,?,?,?,?,?)";
      jdbcTemplate.update(sql, CaisseEtrangereAjout.getNom(),
        CaisseEtrangereAjout.getAdresse(), CaisseEtrangereAjout.getId(),CaisseEtrangereAjout.getIdpays(),CaisseEtrangereAjout.getTelephone(),CaisseEtrangereAjout.getFax(),CaisseEtrangereAjout.getEmail());
      return this.list(jdbcTemplate);
  }


  public int delete(int code , JdbcTemplate jdbcTemplate)  {

      String sql = "DELETE FROM insur.CaisseEtrangere WHERE code = ?";
      return  jdbcTemplate.update(sql, code);
  }

  public int update(CaisseEtrangereCrud caisseEtranegere , JdbcTemplate jdbcTemplate) throws DataAccessException {
       String sql = "update insur.CaisseEtrangere set nom = ?, adresse = ? , id_ville = ? ,id_pays = ? , tel = ? , fax = ? , email = ? WHERE  code = ?";
      int count = jdbcTemplate.update(sql,  caisseEtranegere.getNom(),
      caisseEtranegere.getAdresse(), caisseEtranegere.getId(),caisseEtranegere.getIdpays(),caisseEtranegere.getTelephone(),caisseEtranegere.getFax(),caisseEtranegere.getEmail(),caisseEtranegere.getCode());
      return count;
  }

  public List<CaisseEtrangere> Rechercher(String str , JdbcTemplate jdbcTemplate) {
    String sql = "CALL `filtreCaisseEtrangere` " + str;
      return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(CaisseEtrangere.class));

  }
  
}