package com.example.apipsia.controller;

import java.util.List;
import java.util.Map;

import com.example.apipsia.model.Utilisateur;
import com.example.apipsia.service.LoginService;
import com.example.apipsia.service.UtilisateurService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties.Credential;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "utilisateur")
public class UtilisateurController {
    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping(value = "login")
    public ResponseEntity login(Authentication auth) {
        
        try {
            Utilisateur utilisateur = utilisateurService.getUtilisateurByNom(auth.getPrincipal().toString());

            return new ResponseEntity<>(utilisateur, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "inscription")
    public ResponseEntity inscription(Authentication auth, @RequestBody Map<String, Object> utilisateur) {
        try {
            Utilisateur user = new Utilisateur();

            user.setNom((String)utilisateur.get("nom"));
            user.setNomComplet((String)utilisateur.get("nomComplet"));
            user.setCode_agence((int)utilisateur.get("code_agence"));
            
            List<Utilisateur> utilisateurs = utilisateurService.ajouterUtilisateur(user, (String)utilisateur.get("password"), auth.getPrincipal().toString());

            return new ResponseEntity<>(utilisateurs, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping(value = "getAll")
    public ResponseEntity getAll(Authentication auth) {
        try {
            List<Utilisateur> utilisateurs = utilisateurService.getAllUtilisateurs(auth.getPrincipal().toString());

            return new ResponseEntity<>(utilisateurs, HttpStatus.ACCEPTED);
        } catch (Exception exception) {
            return new ResponseEntity<>("vous n'êtes pas connecté" ,HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping(value="edit")
    public ResponseEntity editUtilisateur(Authentication auth, @RequestBody Utilisateur utilisateur) {
        try {
            int c = utilisateurService.editUtilisateur(utilisateur, auth.getPrincipal().toString());

            return new ResponseEntity<>(c > 0, HttpStatus.ACCEPTED);
        } catch (DataAccessException exception) {
            return new ResponseEntity<>(exception.getMessage() ,HttpStatus.EXPECTATION_FAILED);
        }
    }
}