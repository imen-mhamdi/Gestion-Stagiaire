package com.example.demo.Services;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Repositories.StageRepository;
import com.example.demo.classes.Stage;


@Service
public class StageService {

	 // le service doit appeler un repository 
	@Autowired
    private  StageRepository stageRepository;
	
    public StageService(StageRepository stageRepository) {
        this.stageRepository = stageRepository;
    }
        
    // ajouter stage
    public Stage ajouterStage(Stage stage) {
        return stageRepository.save(stage);
    }
    
    // afficher by id
    public Stage getById(Long id) {
        return stageRepository.findById(id).orElse(null);

    }

    // consulter liste des stages 
    public List<Stage> getAllStage() {
        return stageRepository.findAll();
    }
    
    
    // rechercher stage
    public List<Stage> searchStage(String keyword) {
    	return stageRepository.findAll().stream()
                .filter(c -> containsKeyword(c, keyword))
                .collect(Collectors.toList());
    }

    
 // Helper method to check if any property of the internship contains the keyword
    private boolean containsKeyword(Stage stage, String keyword) {
        return (stage.getTypeStage() != null && stage.getTypeStage().contains(keyword)) ||   
               (stage.getNiveau() != null && stage.getNiveau().contains(keyword)) ||
               (stage.getDepartement() != null && stage.getDepartement().contains(keyword)) ;

    }

    // Helper method to check if any property of the internship contains the keyword
   // private boolean containsKeyword(Stage stage, String keyword) {
     //   return stage.getType().contains(keyword) ||
       // 		stage.getSection().contains(keyword) ;
        //}
    
    // supprimer stage
    public void supprimerStage(Long stageId) {
    	stageRepository.deleteById(stageId);
    }

    
    // modifier stage
    public boolean updateStage(Stage stage, long id) {
        Optional<Stage> stageOptional = stageRepository.findById((long) id);
        
        if (stageOptional.isPresent()) {
            Stage stageToUpdate = stageOptional.get();
            // Mettre à jour les champs de stage avec les nouvelles valeurs
            stageToUpdate.setNom(stage.getNom());
      
            stageToUpdate.setTypeStage(stage.getTypeStage());
             stageToUpdate.setNiveau(stage.getNiveau());
            stageToUpdate.setDuree(stage.getDuree());
            stageToUpdate.setDescription(stage.getDescription());
            stageToUpdate.setSujet(stage.getSujet());
            stageToUpdate.setDateDebutStage(stage.getDateDebutStage());
            stageToUpdate.setDateFinStage(stage.getDateFinStage());
            
            // Enregistrer les modifications dans la base de données
            stageRepository.save(stageToUpdate);
            return true;
        } else 
        	return false ;  
    }
    

    }

