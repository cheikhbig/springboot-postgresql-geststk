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

import com.projet.GestionStock.Repository.ClientRepository;
import com.projet.GestionStock.exception.ResourceNotFoundException;
import com.projet.GestionStock.model.Client;

/**
 * @author Cheikh Ibrahim GUEYE
 *
 */
@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/clients")

public class ClientController {

	@Autowired
	private ClientRepository repository;
	
	@GetMapping("/")
	public List <Client> getAllClients() {
		
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Client> getClientById(@PathVariable(value = "id") Long clientId)
	        throws ResourceNotFoundException {
	        Client client = repository.findById(clientId)
	          .orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + clientId));
	        return ResponseEntity.ok().body(client);
	    }
	
	@PostMapping("/create")
	public Client createClient(@Valid @RequestBody Client client) {
		
		return repository.save(client);
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable(value = "id") Long clientId,
         @Valid @RequestBody Client clientDetails) throws ResourceNotFoundException {
        Client client = repository.findById(clientId)
        .orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + clientId));

        client.setNom(clientDetails.getNom());
        client.setPrenom(clientDetails.getPrenom());
        client.setAdresse(clientDetails.getAdresse());
        client.setTel(clientDetails.getTel());
        client.setCommandes(clientDetails.getCommandes());
        client.setEmail(clientDetails.getEmail());
        final Client updatedClient = repository.save(client);
        return ResponseEntity.ok(updatedClient);
    }
	
	@DeleteMapping("/{id}")
    public Map<String, Boolean> deleteClient(@PathVariable(value = "id") Long clientId)
         throws ResourceNotFoundException {
        Client client = repository.findById(clientId)
       .orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + clientId));

        repository.delete(client);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
