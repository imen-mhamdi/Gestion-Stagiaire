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
import com.example.demo.Services.StageService;
import com.example.demo.classes.Stage;


@RestController
@RequestMapping("/api/stage")
@CrossOrigin(origins = "*", maxAge = 3600)
public class StageController {

    StageService stageService;

    public StageController(StageService stageService) {
        this.stageService = stageService;
    }

    // Ajouter un stage
    @PostMapping("/ajouterStage")
    public void ajouterStage(@RequestBody Stage stage) {
        stageService.ajouterStage(stage);
    }

    // Consulter tous les stages
    @GetMapping("/consulterStage")
    public List<Stage> consulterStage() {
        return stageService.getAllStage();
    }

    // Rechercher des stages par mot-clé
    @GetMapping("/rechercherStage")
    public List<Stage> rechercherStage(@RequestParam String keyword) {
        return stageService.searchStage(keyword);
    }
 
    // Supprimer un stage
    @DeleteMapping("/supprimerStage/{id}")
    public ResponseEntity<Map<String, String>> supprimerStage(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            // Assurez-vous que la méthode supprimerStage dans le service gère correctement les exceptions
            stageService.supprimerStage(id);
            response.put("message", "Stage supprimé avec succès");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            // Si l'élément n'est pas trouvé, renvoyer une réponse 404 Not Found
            response.put("message", "Stage non trouvé");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Pour toutes les autres exceptions, renvoyer une réponse 500 Internal Server Error
            response.put("message", "Échec de la suppression du stage");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Consulter un stage par ID
    @GetMapping("/{id}")
    public ResponseEntity<Stage> consulterParId(@PathVariable Long id) {
        Stage entity = stageService.getById(id);
        if (entity != null) {
            return new ResponseEntity<>(entity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Modifier un stage
    @PutMapping("/modifierStage/{id}")
    public void modifierStage(@RequestBody Stage stage, @PathVariable long id) {
        stageService.updateStage(stage, id);
    }
}
