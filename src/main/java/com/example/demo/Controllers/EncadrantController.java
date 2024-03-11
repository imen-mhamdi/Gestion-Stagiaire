package com.example.demo.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Services.EncadrantService;
import com.example.demo.classes.Encadrant;

@RestController
@RequestMapping("/api/enc")
 @CrossOrigin(origins = "*", maxAge = 3600)
public class EncadrantController {
	
	EncadrantService encadrantService;

    public EncadrantController(EncadrantService encadrantService) {
        this.encadrantService = encadrantService;
    }

    

    // Ajouter un encad
    @PostMapping("/ajouterEnc")
    public void ajouterEnc(@RequestBody Encadrant enc) {
        encadrantService.ajouterEncadrant(enc);
    }

    
    // Consulter tous les encads
    @GetMapping("/consulterEnc")
    public List<Encadrant> consulterEncadrant() {
        return encadrantService.getAllEncadrant();
    }

    
    // Rechercher des encads par mot-clé
    @GetMapping("/rechercherEnc")
    public List<Encadrant> searchEncadrant(@RequestParam String keyword) {
        return encadrantService.searchEncadrant(keyword);
    }
 
    // Supprimer un Encadrant
    @DeleteMapping("/supprimerEnc/{id}")
    public ResponseEntity<Map<String, String>> supprimerEncadrant(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            // Assurez-vous que la méthode supprimer Encadrant dans le service gère correctement les exceptions
        	encadrantService.supprimerEncadrant(id);
            response.put("message", "Encadrant supprimé avec succès");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            // Si l'élément n'est pas trouvé, renvoyer une réponse 404 Not Found
            response.put("message", "Encadrant non trouvé");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Pour toutes les autres exceptions, renvoyer une réponse 500 Internal Server Error
            response.put("message", "Échec de la suppression de l Encadrant");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

     
    // Consulter un encadrant par ID
    @GetMapping("/{id}")
    public ResponseEntity<Encadrant> consulterParId(@PathVariable Long id) {
        Encadrant entity = encadrantService.getById(id);
        if (entity != null) {
            return new ResponseEntity<>(entity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Modifier un encadrant
    @PutMapping("/modifierEnc/{id}")
    public void modifierEnc(@RequestBody Encadrant encadrant, @PathVariable long id) {
        encadrantService.updateEncadrant(encadrant, id);
    }

}

    

