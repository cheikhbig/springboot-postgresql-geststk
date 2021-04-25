/**
 * 
 */
package com.projet.GestionStock.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.projet.GestionStock.model.Utilisateur;

/**
 * @author Cheikh Ibrahim GUEYE
 *
 */
@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
	Optional<Utilisateur> findByUsername(String username);

}
