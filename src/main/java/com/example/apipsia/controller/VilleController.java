package com.example.apipsia.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import com.example.apipsia.model.Ville;
import com.example.apipsia.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;



@CrossOrigin
@RestController
@RequestMapping(value = "ville")
public class VilleController {
    @Autowired
    VilleService villeService;
    private String label;

    @GetMapping(value = "getAll")
    public ResponseEntity getAll(Authentication authentication) {
        try {
            List<Ville> agences = villeService.getAllAgences(authentication.getPrincipal().toString());

            return new ResponseEntity<>(agences, HttpStatus.ACCEPTED);
        } catch (Exception exception) {
            return new ResponseEntity<>("vous n'êtes pas connecté" ,HttpStatus.EXPECTATION_FAILED);
        } 

    }

    @GetMapping(value = "getAl")
    public ResponseEntity getAl(Authentication authentication) {
        try {
            String ville = villeService.getville(authentication.getPrincipal().toString());

            return new ResponseEntity<>(ville, HttpStatus.ACCEPTED);
        } catch (Exception exception) {
            return new ResponseEntity<>("vous n'êtes pas connecté" ,HttpStatus.EXPECTATION_FAILED);
        } 

    }

    @GetMapping(value = "getville")
    public  String finds()  {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:orcl", "insur", "insurbt");
        ) {
            /*CallableStatement cs = conn.prepareCall("{call insertville(?)}"); 
            cs.setString(1, "Rabat");
            cs.execute();
            System.out.println("Done");*/

            CallableStatement cs = conn.prepareCall("{call get_ville(?,?)}"); 
            cs.setInt(1, 1);
            cs.registerOutParameter(2, Types.NVARCHAR);
            cs.execute();
            label = cs.getString(2);

            System.out.println("Done");
            System.out.println("output ville=" +label);


            //cs.registerOutParameter("code", Types.INTEGER);
            //cs.registerOutParameter("label", Types.VARCHAR);

                        /*
                        cs.registerOutParameter("list", Types.REF_CURSOR);
                        System.out.println("Now executing....");
                        cs.execute();
                        System.out.println("etp2");

                        String param1 = cs.getString("list");   
                        System.out.println("output param1=" + param1);
                        */
            
            /*String param1 = cs.getString("code");
            System.out.println("output param1=" + param1);
            String param2 = cs.getString("label");
            System.out.println("output param2=" + param2);*/
                
            } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
            return "output ville=" + label;
    }
     @GetMapping(value = "getvilles")
    public  String findss()  {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:orcl", "insur", "insurbt");
        ) {

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

            } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
            return "test";
    }
}