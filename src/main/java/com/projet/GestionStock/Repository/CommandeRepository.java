/**
 * 
 */
package com.projet.GestionStock.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.GestionStock.model.Commande;

/**
 * @author Cheikh Ibrahim GUEYE
 *
 */

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {

}
