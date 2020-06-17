package com.example.apipsia.repository;

<<<<<<< HEAD
import java.sql.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.example.apipsia.model.Ville;


import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

@Repository
public class VilleRepository {
    public List<Ville> findAll(final JdbcTemplate jdbcTemplate) throws DataAccessException {
        final String sql = "SELECT * FROM Ville";
        // String sql = "begin getall ; end;";

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Ville.class));

        /*
         * StoredProcedure query =
         * entityManager.createStoredProcedureQuery("post_comments").
         * registerStoredProcedureParameter( 2, Class.class, ParameterMode.REF_CURSOR
         * ).setParameter(1, 1L);
         * 
         * query.execute();
         * 
         * List<Object[]> postComments = query.getResultList();
         */

    }

    public String find() {

        final String runSP = "{ begin getAll(?); end }";

        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "insur", "insurbt");
                Statement statement = conn.createStatement();
                CallableStatement cs = conn.prepareCall(runSP);

        ) {
            System.out.println("etp1");

            // alternative
            // cs.registerOutParameter(2, Types.REF_CURSOR);
            // cs.registerOutParameter(1, Types.REF_CURSOR);
            cs.registerOutParameter("C1", Types.REF_CURSOR);
            System.out.println("etp2");
            // run SP
            cs.execute();
            System.out.println("etp3");
            // get refcursor and convert it to ResultSet
            final ResultSet resultSet = (ResultSet) cs.getObject(1);
            while (resultSet.next()) {

                final int code = resultSet.getInt("code");
                final String label = resultSet.getString("label");

                final Ville obj = new Ville();
                obj.setCode(code);
                obj.setLabel(label);

                System.out.println(obj);

            }

        } catch (final SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return "test";
    }

    public  String findss()  {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:orcl", "insur", "insurbt");
        ) {
            final CallableStatement cs = conn.prepareCall("{call get_my_list(?)}");
            
            cs.registerOutParameter(1,  Types.REF_CURSOR);

            
            cs.executeQuery();
            
            final ResultSet rs=(ResultSet)cs.getObject(1);
            
            while(rs.next())
            {
                //System.out.print("code : "+rs.getString(1));
                //System.out.print(", label : "+rs.getString(2));

                final int code = rs.getInt(1);
                final String label = rs.getString(2);
               

                final Ville obj = new Ville();
                obj.setCode(code);
                obj.setLabel(label);
                

                System.out.println("sss " + obj);
            }
            System.out.println("Done");

            } catch (final SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        } catch (final Exception e) {
            e.printStackTrace();
        }
            return "test";
    }


    
   
}


/*try {
    
    Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","insur","insurbt");
    CallableStatement stmt = con.prepareCall("{begin getal(?) ; end;}");
    stmt.registerOutParameter(1, java.sql.Types.VARCHAR);
    System.out.println("etp 1");
     stmt.executeQuery() ;
     System.out.println(stmt);
     System.out.println("etp 2");
    /*List<String> al = new ArrayList<String>();
    al = Arrays.asList(result);
    
} catch (Exception e) {
    //TODO: handle exception
    System.out.println(e.getMessage());
}
         
    return "t";
    
     
    }*/
=======
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
>>>>>>> 53873ec6853ee0155c4e96eb784a6045f1f400df
