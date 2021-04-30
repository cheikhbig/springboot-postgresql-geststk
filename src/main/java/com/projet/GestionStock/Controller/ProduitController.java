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

import com.projet.GestionStock.Repository.ProduitRepository;
import com.projet.GestionStock.exception.ResourceNotFoundException;
import com.projet.GestionStock.model.Produit;

/**
 * @author Cheikh Ibrahim GUEYE
 *
 */
@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/produits")

public class ProduitController {
	
	@Autowired
	private ProduitRepository repository;
	
	@GetMapping("/")
	public List <Produit> getAllCommandes() {
		
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produit> getProduitById(@PathVariable(value = "id") Long produitId)
	        throws ResourceNotFoundException {
		Produit produit = repository.findById(produitId)
	          .orElseThrow(() -> new ResourceNotFoundException("Produit not found for this id :: " + produitId));
	        return ResponseEntity.ok().body(produit);
	    }
	
	@PostMapping("/create")
	public Produit createProduit(@Valid @RequestBody Produit produit) {
		
		return repository.save(produit);
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable(value = "id") Long produitId,
         @Valid @RequestBody Produit produitDetails) throws ResourceNotFoundException {
		Produit produit = repository.findById(produitId)
        .orElseThrow(() -> new ResourceNotFoundException("Produit not found for this id :: " + produitId));
        
		produit.setQuantite(produitDetails.getQuantite());
		produit.setDesignation(produitDetails.getDesignation());
		produit.setPrixUnitaire(produitDetails.getPrixUnitaire());
		produit.setSeuil(produitDetails.getSeuil());
		produit.setFamille(produitDetails.getFamille());
		produit.setReference(produitDetails.getReference());
		produit.setDescription(produitDetails.getDescription());
		
        final Produit updatedProduit = repository.save(produit);
        return ResponseEntity.ok(updatedProduit);
    }
	
	@DeleteMapping("/{id}")
    public Map<String, Boolean> deleteProduit(@PathVariable(value = "id") Long produitId)
         throws ResourceNotFoundException {
		Produit produit = repository.findById(produitId)
       .orElseThrow(() -> new ResourceNotFoundException("Produit not found for this id :: " + produitId));

        repository.delete(produit);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
