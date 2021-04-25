/**
 * 
 */
package com.projet.GestionStock.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.GestionStock.model.Lignecommande;

/**
 * @author Cheikh Ibrahim GUEYE
 *
 */
@Repository
public interface LignecommandeRepository extends JpaRepository<Lignecommande, Long> {

}
