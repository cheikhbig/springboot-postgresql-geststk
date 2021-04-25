/**
 * 
 */
package com.projet.GestionStock.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Cheikh Ibrahim GUEYE
 *
 */
@Entity
@Table(name="lignecommande")
public class Lignecommande {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="quantitecommande")
	private Integer quantitecommande;
	
	@Column(name="prixAchat")
	private float prixAchat;
	
	@ManyToOne
	@JoinColumn(name="idproduit")
	private Produit produit;
	
	@ManyToOne
	@JoinColumn(name="idcommande")
	private Commande commande;

	public Lignecommande(Integer quantitecommande, float prixAchat, Produit produit, Commande commande) {
		super();
		this.quantitecommande = quantitecommande;
		this.prixAchat = prixAchat;
		this.produit = produit;
		this.commande = commande;
	}

	public Lignecommande() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantitecommande() {
		return quantitecommande;
	}

	public void setQuantitecommande(Integer quantitecommande) {
		this.quantitecommande = quantitecommande;
	}

	public float getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(float prixAchat) {
		this.prixAchat = prixAchat;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}
	
	

}
