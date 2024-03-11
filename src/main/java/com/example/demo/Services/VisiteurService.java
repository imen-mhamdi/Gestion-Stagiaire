package com.example.demo.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repositories.VisiteurRepository;
import com.example.demo.classes.Visiteur;

@Service
public class VisiteurService {
	
	@Autowired
    private  VisiteurRepository visiteurRepository ;

    public Visiteur registerVisiteur(Visiteur visiteur) {
    // Add business logic if needed
    return visiteurRepository.save(visiteur);
	}
}


/*
    
    
// afficher visiteur par id    
    public Visiteur getById(Long id) {
        return visiteurRepository.findById(id).orElse(null);
    }

    // modifier visiteur 
    public boolean updateVisiteur(Visiteur visiteur, Long id) {
        Optional<Visiteur> visiteurOptional = visiteurRepository.findById(id);

        if (visiteurOptional.isPresent()) {
            Visiteur visiteurToUpdate = visiteurOptional.get();

            // Mettre à jour les champs du visiteur avec les nouvelles valeurs
            visiteurToUpdate.setEmail(visiteur.getEmail());
            visiteurToUpdate.setUserName(visiteur.getUserName());
            visiteurToUpdate.setPassword(visiteur.getPassword());
            visiteurToUpdate.setRole(visiteur.getRole());

            // Enregistrer les modifications dans la base de données
            visiteurRepository.save(visiteurToUpdate);
            return true;
        } else {
            return false;
        }
    }

    // afficher list visiteur 
    public List<Visiteur> getAllVisiteurs() {
        return visiteurRepository.findAll();
    }

    // supprimer visiteur 
    public void deleteVisiteur(Long id) {
        visiteurRepository.deleteById(id);
    }
}
*/