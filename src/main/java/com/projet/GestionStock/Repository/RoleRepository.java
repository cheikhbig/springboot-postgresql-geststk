/**
 * 
 */
package com.projet.GestionStock.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.GestionStock.model.Role;

/**
 * @author Cheikh Ibrahim GUEYE
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
