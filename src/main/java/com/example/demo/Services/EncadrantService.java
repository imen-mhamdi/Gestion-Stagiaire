package com.example.demo.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.Repositories.EncadrantRepository;
import com.example.demo.classes.Encadrant;

@Service
public class EncadrantService {

    private  EncadrantRepository encadrantRepository;
	
	
    public EncadrantService(EncadrantRepository encadrantRepository) {
        this.encadrantRepository = encadrantRepository;
    }
        
    
    // ajouter encadrant
    public Encadrant ajouterEncadrant(Encadrant encadrant) {
        return encadrantRepository.save(encadrant);
    }
    
    // afficher by id
    public Encadrant getById(Long id) {
        return encadrantRepository.findById(id).orElse(null);

    }

    // consulter liste des encadrant
    public List<Encadrant> getAllEncadrant() {
        return encadrantRepository.findAll();
    }
    
    
    // rechercher encadrant
    public List<Encadrant> searchEncadrant(String keyword) {
    	return encadrantRepository.findAll().stream()
                .filter(c -> containsKeyword(c, keyword))
                .collect(Collectors.toList());
    }


 // Helper method to check if any property of the internship contains the keyword
    private boolean containsKeyword(Encadrant encadrant, String keyword) {
        return (encadrant.getSpecialite() != null && encadrant.getSpecialite().contains(keyword));
    } 		
    

    // supprimer encadrant
    public void supprimerEncadrant(Long id) {
    	encadrantRepository.deleteById(id);
    }

    
    
    // modifier encadrant
    public boolean updateEncadrant(Encadrant encadrant, long id) {
        Optional<Encadrant> encadrantOptional = encadrantRepository.findById((long) id);      
        if (encadrantOptional.isPresent()) {
            Encadrant encadrantToUpdate = encadrantOptional.get();
            // Mettre à jour les champs de l encadrant avec les nouvelles valeurs
            
            encadrantToUpdate.setDatenaissance(encadrant.getDatenaissance());            
            encadrantToUpdate.setTelephone(encadrant.getTelephone());
            encadrantToUpdate.setEmail(encadrant.getEmail());

            encadrantToUpdate.setUserName(encadrant.getUserName());
            encadrantToUpdate.setPassword(encadrant.getPassword());
            encadrantToUpdate.setCin(encadrant.getCin());
            encadrantToUpdate.setSpecialite(encadrant.getSpecialite());
            
            // Enregistrer les modifications dans la base de données
            encadrantRepository.save(encadrantToUpdate);
            return true;
        } else 
        	return false ;  
    }
    

    }

