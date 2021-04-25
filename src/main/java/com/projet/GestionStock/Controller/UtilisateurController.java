/**
 * 
 */
package com.projet.GestionStock.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.GestionStock.Repository.UtilisateurRepository;
import com.projet.GestionStock.exception.ResourceNotFoundException;
import com.projet.GestionStock.model.Utilisateur;
import com.projet.GestionStock.security.ServiceUtilisateur;
/**
 * @author Cheikh Ibrahim GUEYE
 *
 */
@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/utilisateurs")

public class UtilisateurController {
	
	@Autowired
	private UtilisateurRepository repository;
	@Autowired
	private ServiceUtilisateur serviceUtilisateur;
	
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	
	@GetMapping("/")
	public List <Utilisateur> getAllCommandes() {
		
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable(value = "id") int utilisateurId)
	        throws ResourceNotFoundException {
		Utilisateur utilisateur = repository.findById(utilisateurId)
	          .orElseThrow(() -> new ResourceNotFoundException("Utilisateur not found for this id :: " + utilisateurId));
	        return ResponseEntity.ok().body(utilisateur);
	    }
	
	@PostMapping("/create")
	public Utilisateur createUtilisateur(@Valid @RequestBody Utilisateur utilisateur) {
		String encodedPassword = this.passwordEncoder.encode(utilisateur.getPassword());
		utilisateur.setPassword(encodedPassword);
		utilisateur.setAccountNonExpired(true);
		utilisateur.setAccountNonLocked(true);
		utilisateur.setCredentialsNonExpired(true);
		utilisateur.setEnabled(true);
		
		return repository.save(utilisateur);
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable(value = "id") int utilisateurId,
         @Valid @RequestBody Utilisateur utilisateurDetails) throws ResourceNotFoundException {
		Utilisateur utilisateur = repository.findById(utilisateurId)
        .orElseThrow(() -> new ResourceNotFoundException("Utilisateur not found for this id :: " + utilisateurId));
        
		utilisateur.setNom(utilisateurDetails.getNom());
		utilisateur.setPrenom(utilisateurDetails.getPrenom());
		utilisateur.setUsername(utilisateurDetails.getUsername());
		utilisateur.setPassword(utilisateurDetails.getPassword());
        final Utilisateur updatedUtilisateur = repository.save(utilisateur);
        return ResponseEntity.ok(updatedUtilisateur);
    }
	
	@DeleteMapping("/{id}")
    public Map<String, Boolean> deleteUtilisateur(@PathVariable(value = "id") int utilisateurId)
         throws ResourceNotFoundException {
		Utilisateur utilisateur = repository.findById(utilisateurId)
       .orElseThrow(() -> new ResourceNotFoundException("Utilisateur not found for this id :: " + utilisateurId));

        repository.delete(utilisateur);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
	
	@GetMapping("/authenticated-user")
    public ResponseEntity<Utilisateur> getAuthenticatedUser() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Utilisateur utilisateur = (Utilisateur) this.serviceUtilisateur.loadUserByUsername(username);
		utilisateur.setPassword(null);
        return new ResponseEntity<Utilisateur>(utilisateur, HttpStatus.OK);
    }

}
