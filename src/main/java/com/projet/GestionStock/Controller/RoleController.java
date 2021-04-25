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

import com.projet.GestionStock.Repository.RoleRepository;
import com.projet.GestionStock.exception.ResourceNotFoundException;
import com.projet.GestionStock.model.Role;

/**
 * @author Cheikh Ibrahim GUEYE
 *
 */
@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/roles")

public class RoleController {
	
	@Autowired
	private RoleRepository repository;
	
	@GetMapping("/")
	public List <Role> getAllCommandes() {
		
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Role> getRoleById(@PathVariable(value = "id") int roleId)
	        throws ResourceNotFoundException {
		Role role = repository.findById(roleId)
	          .orElseThrow(() -> new ResourceNotFoundException("Role not found for this id :: " + roleId));
	        return ResponseEntity.ok().body(role);
	    }
	
	@PostMapping("/create")
	public Role createRole(@Valid @RequestBody Role role) {
		
		return repository.save(role);
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable(value = "id") int roleId,
         @Valid @RequestBody Role roleDetails) throws ResourceNotFoundException {
		Role role = repository.findById(roleId)
        .orElseThrow(() -> new ResourceNotFoundException("Role not found for this id :: " + roleId));
        
		role.setName(roleDetails.getName());
        final Role updatedRole = repository.save(role);
        return ResponseEntity.ok(updatedRole);
    }
	
	@DeleteMapping("/{id}")
    public Map<String, Boolean> deleteRole(@PathVariable(value = "id") int roleId)
         throws ResourceNotFoundException {
		Role role = repository.findById(roleId)
       .orElseThrow(() -> new ResourceNotFoundException("Role not found for this id :: " + roleId));

        repository.delete(role);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
