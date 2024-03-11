package com.example.demo.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repositories.DemandeRepository;
import com.example.demo.classes.Demande;

@Service
public class DemandeService {
	@Autowired
    private  DemandeRepository demandeRepository;
	
    public DemandeService(DemandeRepository demandeRepository) {
        this.demandeRepository = demandeRepository;
    }
        
    // ajouter demande
    public Demande ajouterDemande(Demande demande) {
        return demandeRepository.save(demande);
    }
    
    // afficher by id
    public Demande getById(Long id) {
        return demandeRepository.findById(id).orElse(null);

    }

    // consulter liste des demandes 
    public List<Demande> getAllDemande() {
        return demandeRepository.findAll();
    }
    
    
    // rechercher demande
    public List<Demande> searchDemande(String keyword) {
    	return demandeRepository.findAll().stream()
                .filter(c -> containsKeyword(c, keyword))
                .collect(Collectors.toList());
    }

    
 // Helper method to check if any property of the internship contains the keyword
    private boolean containsKeyword(Demande demande, String keyword) {
        return (demande.getType() != null && demande.getType().contains(keyword)) ||   
               (demande.getNiveauEtude() != null && demande.getNiveauEtude().contains(keyword)) ||
               (demande.getEtat() != null && demande.getEtat().contains(keyword)) ;
    }

    // supprimer demande
    public void supprimerDemande(Long id) {
    	demandeRepository.deleteById(id);
    }

    
    // modifier demande
    public boolean updateDemande(Demande demande, long id) {
        Optional<Demande> demandeOptional = demandeRepository.findById((long) id);
        
        if (demandeOptional.isPresent()) {
            Demande demandeToUpdate = demandeOptional.get();
            // Mettre à jour les champs de stage avec les nouvelles valeurs      
            demandeToUpdate.setType(demande.getType());
            demandeToUpdate.setNiveauEtude(demande.getNiveauEtude());
            demandeToUpdate.setDate(demande.getDate());
            demandeToUpdate.setEtat(demande.getEtat());
            demandeToUpdate.setCv(demande.getCv());
            
            
            // Enregistrer les modifications dans la base de données
            demandeRepository.save(demandeToUpdate);
            return true;
        } else 
        	return false ;  
    }
    

    }


