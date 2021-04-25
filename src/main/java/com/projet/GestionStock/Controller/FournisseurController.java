/**
 * 
 */
package com.projet.GestionStock.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.GestionStock.Repository.FournisseurRepository;
import com.projet.GestionStock.exception.ResourceNotFoundException;
import com.projet.GestionStock.model.Fournisseur;

/**
 * @author Cheikh Ibrahim GUEYE
 *
 */
@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/fournisseurs")

public class FournisseurController {
	@Autowired
	private FournisseurRepository repository;
	
	@GetMapping("/")
	public List <Fournisseur> getAllCommandes() {
		
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Fournisseur> getFournisseurById(@PathVariable(value = "id") int fournisseurId)
	        throws ResourceNotFoundException {
		Fournisseur fournisseur = repository.findById(fournisseurId)
	          .orElseThrow(() -> new ResourceNotFoundException("Fournisseur not found for this id :: " + fournisseurId));
	        return ResponseEntity.ok().body(fournisseur);
	    }
	
	@PostMapping("/create")
	public Fournisseur createFournisseur(@Valid @RequestBody Fournisseur fournisseur) {
		
		return repository.save(fournisseur);
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<Fournisseur> updateFournisseur(@PathVariable(value = "id") int fournisseurId,
         @Valid @RequestBody Fournisseur fournisseurDetails) throws ResourceNotFoundException {
		Fournisseur fournisseur = repository.findById(fournisseurId)
        .orElseThrow(() -> new ResourceNotFoundException("Fournisseur not found for this id :: " + fournisseurId));
        
		fournisseur.setNom(fournisseurDetails.getNom());
		fournisseur.setPrenom(fournisseurDetails.getPrenom());
		fournisseur.setAdresse(fournisseurDetails.getAdresse());
		fournisseur.setEmail(fournisseurDetails.getEmail());
		fournisseur.setTel(fournisseurDetails.getTel());
        final Fournisseur updatedFournisseur = repository.save(fournisseur);
        return ResponseEntity.ok(updatedFournisseur);
    }
	
	@DeleteMapping("/{id}")
    public Map<String, Boolean> deleteFournisseur(@PathVariable(value = "id") int fournisseurId)
         throws ResourceNotFoundException {
        Fournisseur fournisseur = repository.findById(fournisseurId)
       .orElseThrow(() -> new ResourceNotFoundException("Fournisseur not found for this id :: " + fournisseurId));

        repository.delete(fournisseur);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
