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

import com.projet.GestionStock.Repository.FamilleRepository;
import com.projet.GestionStock.exception.ResourceNotFoundException;
import com.projet.GestionStock.model.*;

/**
 * @author Cheikh Ibrahim GUEYE
 *
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/familles")
public class FamilleController {
	
	@Autowired
	private FamilleRepository repository;
	
	@GetMapping("/")
	public List <Famille> getAllFamilles(){
		
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Famille> getFamilleById(@PathVariable(value = "id") int familleId)
        throws ResourceNotFoundException {
        Famille famille = repository.findById(familleId)
          .orElseThrow(() -> new ResourceNotFoundException("Famille not found for this id :: " + familleId));
        return ResponseEntity.ok().body(famille);
    }
	 
	@PostMapping("/create")
	public Famille createFamille(@Valid @RequestBody Famille famille) {
		
		return repository.save(famille);
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<Famille> updateFamille(@PathVariable(value = "id") int familleId,
         @Valid @RequestBody Famille familleDetails) throws ResourceNotFoundException {
        Famille famille = repository.findById(familleId)
        .orElseThrow(() -> new ResourceNotFoundException("Famille not found for this id :: " + familleId));

        famille.setNom(familleDetails.getNom());
        final Famille updatedFamille = repository.save(famille);
        return ResponseEntity.ok(updatedFamille);
    }
	
	@DeleteMapping("/{id}")
    public Map<String, Boolean> deleteFamille(@PathVariable(value = "id") int familleId)
         throws ResourceNotFoundException {
        Famille famille = repository.findById(familleId)
       .orElseThrow(() -> new ResourceNotFoundException("Famille not found for this id :: " + familleId));

        repository.delete(famille);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
	
}
