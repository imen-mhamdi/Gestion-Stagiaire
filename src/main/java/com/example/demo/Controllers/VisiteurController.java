package com.example.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Services.VisiteurService;
import com.example.demo.classes.Visiteur;


//@AllArgsConstructor
//@Slf4j 
@RestController
@RequestMapping("/api/visiteurs")
@CrossOrigin(origins = "*", maxAge = 3600)
public class VisiteurController {

    @Autowired
    private VisiteurService visiteurService;

    @PostMapping("/register")
    public Visiteur registerVisiteur(@RequestBody Visiteur visiteur) {
        return visiteurService.registerVisiteur(visiteur);
    }
}



	    /*
     // get liste des visiteurs 
    @GetMapping(path = "/visiteurs")
    public List<Visiteur> getAllVisiteurs() {
        List<Visiteur> visiteurs = visiteurService.getAllVisiteurs();
        return visiteurs;
    }

    
    // supprimer visiteur
    @DeleteMapping(path = "/visiteur/{id}")
    public void deleteVisiteur(@PathVariable Long id) {
    	visiteurService.deleteVisiteur(id);
    }

    
    // get visiteur by id
    @GetMapping("/{id}")
    public ResponseEntity<Visiteur> getById(@PathVariable Long id) {
        Visiteur visiteur = visiteurService.getById(id);

        if (visiteur != null) {
            return new ResponseEntity<>(visiteur, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
    // modifier visiteur 
    @PutMapping("/modifierVisiteur/{id}")
    public ResponseEntity<Void> updateVisiteur(@RequestBody Visiteur visiteur, @PathVariable Long id) {
        boolean updated = visiteurService.updateVisiteur(visiteur, id);

        if (updated) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
*/