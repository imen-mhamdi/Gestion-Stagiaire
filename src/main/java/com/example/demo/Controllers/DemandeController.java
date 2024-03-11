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

import com.example.demo.Services.DemandeService;
import com.example.demo.classes.Demande;
 
@RestController
@RequestMapping("/api/demande")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DemandeController {
	 DemandeService demandeService;

	    public DemandeController(DemandeService demandeService) {
	        this.demandeService = demandeService;
	    }

	    // Ajouter une demande
	    @PostMapping("/ajouterDemande")
	    public void ajouterDemande(@RequestBody Demande demande) {
	    	demandeService.ajouterDemande(demande);
	    }

	    
	    // Consulter tous les demandes
	    @GetMapping("/consulterDemande")
	    public List<Demande> consulterDemande() {
	        return demandeService.getAllDemande();
	    }

	    // Rechercher des demandes par mot-clé
	    @GetMapping("/rechercherDemande")
	    public List<Demande> rechercherDemande(@RequestParam String keyword) {
	        return demandeService.searchDemande(keyword);
	    }
	 
	    // Supprimer une demande
	    @DeleteMapping("/supprimerDemande/{id}")
	    public ResponseEntity<Map<String, String>> supprimerDemande(@PathVariable Long id) {
	        Map<String, String> response = new HashMap<>();
	        try {
	            // Assurez-vous que la méthode supprimerStage dans le service gère correctement les exceptions
	        	demandeService.supprimerDemande(id);
	            response.put("message", "demande supprimé avec succès");
	            return new ResponseEntity<>(response, HttpStatus.OK);
	        } catch (NoSuchElementException e) {
	            // Si l'élément n'est pas trouvé, renvoyer une réponse 404 Not Found
	            response.put("message", "Stage non trouvé");
	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	        } catch (Exception e) {
	            // Pour toutes les autres exceptions, renvoyer une réponse 500 Internal Server Error
	            response.put("message", "Échec de la suppression de la demande");
	            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    // Consulter une demande par ID
	    @GetMapping("/{id}")
	    public ResponseEntity<Demande> consulterParId(@PathVariable Long id) {
	        Demande entity =demandeService.getById(id);
	        if (entity != null) {
	            return new ResponseEntity<>(entity, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    // Modifier une demande
	    @PutMapping("/modifierDemande/{id}")
	    public void modifierDemande(@RequestBody Demande demande, @PathVariable long id) {
	    	demandeService.updateDemande(demande ,id);
	    }

}
