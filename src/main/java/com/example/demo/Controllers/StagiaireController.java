package com.example.demo.Controllers;

import com.example.demo.Services.StagiaireService;
import com.example.demo.classes.Stagiaire;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stagiaire")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class StagiaireController {

    @Autowired
    private final StagiaireService stagiaireService;

    @GetMapping
    public List<Stagiaire> getStagiaires() {
        return stagiaireService.getStagiaires();
    }

    @GetMapping("{id}")
    public Stagiaire getStagiaire(Long id) {
        return stagiaireService.getStagiaire(id);
    }
    @PostMapping
    public void addStagiaire(Stagiaire stagiaire) {
        stagiaireService.addStagiaire(stagiaire);
    }
    @PutMapping("{id}")
    public ResponseEntity<Stagiaire> updateStagiaire (@PathVariable Long id , @RequestBody Stagiaire stagiaire) {
        return stagiaireService.updateStagiaire(id,stagiaire)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("{id}")
    public void deleteStagiaire(@PathVariable Long id) {
        stagiaireService.deleteStagiaire(id);
    }
    @PutMapping("{Stagaire-id}/addStage/{Stage-id}")
    public void addStage(@PathVariable Long StagaireId, @PathVariable Long StageId) {
        stagiaireService.addStage(StagaireId, StageId);
    }


}
