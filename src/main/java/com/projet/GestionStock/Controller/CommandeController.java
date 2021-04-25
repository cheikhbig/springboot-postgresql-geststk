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

import com.projet.GestionStock.Repository.CommandeRepository;
import com.projet.GestionStock.exception.ResourceNotFoundException;
import com.projet.GestionStock.model.Commande;

/**
 * @author Cheikh Ibrahim GUEYE
 *
 */
@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/commandes")

public class CommandeController {
	@Autowired
	private CommandeRepository repository;
	
	@GetMapping("/")
	public List <Commande> getAllCommandes() {
		
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Commande> getCommandeById(@PathVariable(value = "id") Long commandeId)
	        throws ResourceNotFoundException {
	        Commande commande = repository.findById(commandeId)
	          .orElseThrow(() -> new ResourceNotFoundException("Commande not found for this id :: " + commandeId));
	        return ResponseEntity.ok().body(commande);
	    }
	
	@PostMapping("/create")
	public Commande createCommande(@Valid @RequestBody Commande commande) {
		
		return repository.save(commande);
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<Commande> updateCommande(@PathVariable(value = "id") Long commandeId,
         @Valid @RequestBody Commande commandeDetails) throws ResourceNotFoundException {
        Commande commande = repository.findById(commandeId)
        .orElseThrow(() -> new ResourceNotFoundException("Commande not found for this id :: " + commandeId));
        
        commande.setDatecommande(commandeDetails.getDatecommande());
        commande.setClient(commandeDetails.getClient());
        commande.setTva(commandeDetails.getTva());
        final Commande updatedCommande = repository.save(commande);
        return ResponseEntity.ok(updatedCommande);
    }
	
	@DeleteMapping("/{id}")
    public Map<String, Boolean> deleteCommande(@PathVariable(value = "id") Long commandeId)
         throws ResourceNotFoundException {
        Commande commande = repository.findById(commandeId)
       .orElseThrow(() -> new ResourceNotFoundException("Commande not found for this id :: " + commandeId));

        repository.delete(commande);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }



}
