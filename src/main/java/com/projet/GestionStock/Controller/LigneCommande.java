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

import com.projet.GestionStock.Repository.LignecommandeRepository;
import com.projet.GestionStock.exception.ResourceNotFoundException;
import com.projet.GestionStock.model.Lignecommande;

/**
 * @author Cheikh Ibrahim GUEYE
 *
 */
@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/lignecommandes")

public class LigneCommande {
	
	@Autowired
	private LignecommandeRepository repository;
	
	@GetMapping("/")
	public List <Lignecommande> getAllCommandes() {
		
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Lignecommande> getLignecommandeById(@PathVariable(value = "id") long lignecommandeId)
	        throws ResourceNotFoundException {
		Lignecommande lignecommande = repository.findById(lignecommandeId)
	          .orElseThrow(() -> new ResourceNotFoundException("Lignecommande not found for this id :: " + lignecommandeId));
	        return ResponseEntity.ok().body(lignecommande);
	    }
	
	@PostMapping("/create")
	public Lignecommande createLignecommande(@Valid @RequestBody Lignecommande lignecommande) {
		
		return repository.save(lignecommande);
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<Lignecommande> updateLignecommande(@PathVariable(value = "id") long lignecommandeId,
         @Valid @RequestBody Lignecommande lignecommandeDetails) throws ResourceNotFoundException {
		Lignecommande lignecommande = repository.findById(lignecommandeId)
        .orElseThrow(() -> new ResourceNotFoundException("Lignecommande not found for this id :: " + lignecommandeId));
        
		lignecommande.setQuantitecommande(lignecommandeDetails.getQuantitecommande());
		lignecommande.setPrixAchat(lignecommandeDetails.getPrixAchat());
		lignecommande.setProduit(lignecommandeDetails.getProduit());
		lignecommande.setCommande(lignecommandeDetails.getCommande());
        final Lignecommande updatedLignecommande = repository.save(lignecommande);
        return ResponseEntity.ok(updatedLignecommande);
    }
	
	@DeleteMapping("/{id}")
    public Map<String, Boolean> deleteLignecommande(@PathVariable(value = "id") long lignecommandeId)
         throws ResourceNotFoundException {
		Lignecommande lignecommande = repository.findById(lignecommandeId)
       .orElseThrow(() -> new ResourceNotFoundException("Lignecommande not found for this id :: " + lignecommandeId));

        repository.delete(lignecommande);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
