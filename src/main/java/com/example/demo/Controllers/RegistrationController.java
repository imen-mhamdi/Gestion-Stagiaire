package com.example.demo.Controllers;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Services.RegistrationService;
import com.example.demo.classes.Visiteur;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class RegistrationController {
    @Autowired
    RegistrationService registrationService;

    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @PostMapping(path = "/register")
    public Visiteur registerVisiteur(@RequestBody Visiteur visiteur) throws Exception {
        String tempEmail = visiteur.getEmail();
        if (tempEmail != null && !"".equals(tempEmail)) {
            Visiteur visiteurObj = registrationService.fetchByEmail(tempEmail);
            if (visiteurObj != null) {
                throw new Exception("This visiteur with email " + tempEmail + " already exists");
            }
        }
        

        logger.info("Test: " + visiteur.getEmail());
        Visiteur visiteurObj = registrationService.register(visiteur);
        return visiteurObj;
    }
    @GetMapping(path = "/visiteurs")
    public List<Visiteur> getAllVisiteurs() {
        List<Visiteur> visiteurs = registrationService.getAllVisiteurs();
        return visiteurs;
    }
    @DeleteMapping(path = "/visiteur/{id}")
    public void deleteVisiteur(@PathVariable int id) {
        registrationService.deleteVisiteur(id);
    }
   
    
    @PostMapping(path = "/login")
    public Visiteur login(@RequestBody Visiteur visiteur) throws Exception {
        String tempEmail = visiteur.getEmail();
        String tempPass = visiteur.getPassword();
        Visiteur visiteurObj = null;

        if (tempEmail != null && tempPass != null) {
            visiteurObj = registrationService.fetchByEmailAndPassword(tempEmail, tempPass);
        }
        if (visiteurObj == null) {
            throw new Exception("Bad credentials");
        }
        return visiteurObj;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Visiteur> getById(@PathVariable int id) {
        Visiteur visiteur = registrationService.getById(id);

        if (visiteur != null) {
            return new ResponseEntity<>(visiteur, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/ModifierVisiteur/{id}")
    public ResponseEntity<Void> updateVisiteur(@RequestBody Visiteur visiteur, @PathVariable int id) {
        boolean updated = registrationService.updateVisiteur(visiteur, id);

        if (updated) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}