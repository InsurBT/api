package com.example.apipsia.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.example.apipsia.repository.UtilisateurRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoginService implements AuthenticationProvider {
    private final String DBURL = "jdbc:oracle:thin:@//localhost:1521/orcl";

    @Autowired
    UtilisateurRepository utilisateurRepository;

    private Map<String, JdbcTemplate> jdbcTemplates = new HashMap<>();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            String nom = authentication.getName();
            String password = (String) authentication.getCredentials();

            log.info("trying to authenticate user {}", nom );
            
            DataSource ds = DataSourceBuilder.create().url(DBURL).username(nom).password(password).build();

            ds.getConnection();

            JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

            this.jdbcTemplates.putIfAbsent(nom, jdbcTemplate);

            // Utilisateur utilisateur = utilisateurRepository.getUtilsateurByNom(nom, jdbcTemplate);
            
            Authentication grantedAuthentication = new UsernamePasswordAuthenticationToken(nom, password, new ArrayList<GrantedAuthority>());

            log.info("successefully authenticated user {}", nom);
            
            return grantedAuthentication;
        } catch (Exception e) {
            log.error("failed to athenticate the user {}", authentication.getName(), e);
            throw new AuthenticationCredentialsNotFoundException("Login ou mot de passe invalide");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public Map<String, JdbcTemplate> getActiveConnnections() {
        return this.jdbcTemplates;
    }
}