package com.example.demo.Services;

import com.example.demo.Repositories.StageRepository;
import com.example.demo.Repositories.StagiaireRepository;
import com.example.demo.classes.Stagiaire;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor

public class StagiaireService {
    @Autowired
    private StagiaireRepository stagiaireRepository;
    private StageRepository stageRepository;


    public void deleteStagiaire(Long id) {
        stagiaireRepository.deleteById(id);
    }

    public Optional<Stagiaire > updateStagiaire(Long Id , Stagiaire stagiaire) {
        if (stagiaireRepository.existsById(Id) ) {
            stagiaire.setId(Id);
            return Optional.of(stagiaireRepository.save(stagiaire));
        }
            return Optional.empty();
    }

    public void addStagiaire(Stagiaire stagiaire) {
        stagiaireRepository.save(stagiaire);
    }

    public Stagiaire getStagiaire(Long id) {
        return stagiaireRepository.findById(id).get();
    }


    public List <Stagiaire> getStagiaires() {
        return stagiaireRepository.findAll();

    }
    public void addStage(Long StagaireId, Long StageId) {
        Stagiaire stagiaire = stagiaireRepository.findById(StagaireId).get();
        stagiaire.getStagiaire_stage().add(stageRepository.findById(StageId).get());

        stagiaireRepository.save(stagiaire);
    }


}
