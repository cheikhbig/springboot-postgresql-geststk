/**
 * 
 */
package com.projet.GestionStock.model;

import java.sql.Date;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Cheikh Ibrahim GUEYE
 *
 */
@Entity
@Table(name="commande")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "lignecommandes"})
public class Commande {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="datecommande")
	private Date datecommande;
	
	@ManyToOne
	@JoinColumn(name="id_client")
	private Client client;

	@Column(name="tva")
	private float tva;
	 
	@ManyToOne
    @JoinColumn(name="id_fournisseur")
	private Fournisseur fournisseur;
	
	@OneToMany(mappedBy="commande")
	private Collection<Lignecommande> lignecommandes;
	
	public Commande(Date datecommande, Client client, float tva) {
		super();
		this.datecommande = datecommande;
		this.client = client;
		this.tva = tva;
	}
	

	public Commande() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDatecommande() {
		return datecommande;
	}

	public void setDatecommande(Date datecommande) {
		this.datecommande = datecommande;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public float getTva() {
		return tva;
	}

	public void setTva(float tva) {
		this.tva = tva;
	}


	public Collection<Lignecommande> getLignecommandes() {
		return lignecommandes;
	}


	public void setLignecommandes(Collection<Lignecommande> lignecommandes) {
		this.lignecommandes = lignecommandes;
	}


	public Fournisseur getFournisseur() {
		return fournisseur;
	}


	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}
	
	
}
