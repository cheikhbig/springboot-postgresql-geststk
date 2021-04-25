/**
 * 
 */
package com.projet.GestionStock.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Cheikh Ibrahim GUEYE
 *
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "lignecommandes"})
public class Produit {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="quantite")
	private Integer quantite;
	
	@Column(name="designation")
	private String designation;

	@Column(name="prixUnitaire")
	private Double prixUnitaire;
	
	@Column(name="seuil")
	private Integer seuil;
	
	@ManyToOne
    @JoinColumn(name="idfamille")
    private Famille famille;
	
	@Column(name="reference")
	private String reference;
	
	@Column(name="description")
	private String description;
	
	@OneToMany(mappedBy="produit")
	private Collection<Lignecommande> lignecommandes;
	

	public Produit(Integer quantite, String designation, Double prixUnitaire, Integer seuil, Famille famille,
			String reference, String description) {
		super();
		this.quantite = quantite;
		this.designation = designation;
		this.prixUnitaire = prixUnitaire;
		this.seuil = seuil;
		this.famille = famille;
		this.reference = reference;
		this.description = description;
	}


	public Produit() {
		super();
	}
	

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Double getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(Double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public Integer getSeuil() {
		return seuil;
	}

	public void setSeuil(Integer seuil) {
		this.seuil = seuil;
	}

	public Famille getFamille() {
		return famille;
	}

	public void setFamille(Famille famille) {
		this.famille = famille;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public Collection<Lignecommande> getLignecommandes() {
		return lignecommandes;
	}


	public void setLignecommandes(Collection<Lignecommande> lignecommandes) {
		this.lignecommandes = lignecommandes;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
	

}
