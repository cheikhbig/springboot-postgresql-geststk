/**
 * 
 */
package com.projet.GestionStock.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Cheikh Ibrahim GUEYE
 *
 */

@Entity
@Table(name="Famille")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "produits"})
public class Famille {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@NotBlank(message="Ce champ est obligatoire")
	private String nom;
	
	@OneToMany(mappedBy="famille")
	private Collection<Produit>produits;
	
	
	public Famille() {}
	public Famille(String nom) {
		super();
		this.nom = nom;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Collection<Produit> getProduits() {
		return produits;
	}
	public void setProduits(Collection<Produit> produits) {
		this.produits = produits;
	}
	
	
}
