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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Cheikh Ibrahim GUEYE
 *
 */

@Entity
@Table(name="fournisseur")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "commandes"})
public class Fournisseur {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="nom")
	private String nom;
	
	@Column(name="prenom")
	private String prenom;
	
	@Column(name="adresse")
	private String adresse;
	
	@Email
	@Column(name="email", unique=true )
	private String Email;
	
	@Column(name="tel")
	private Integer tel;
	
	@OneToMany(mappedBy="fournisseur")
	private Collection<Commande> commandes;

	public Fournisseur(String nom, String prenom, String adresse, @javax.validation.constraints.Email String email,
			Integer tel) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		Email = email;
		this.tel = tel;
	}

	public Fournisseur() {
		super();
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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public Integer getTel() {
		return tel;
	}

	public void setTel(Integer tel) {
		this.tel = tel;
	}

	public Collection<Commande> getCommandes() {
		return commandes;
	}

	public void setCommandes(Collection<Commande> commandes) {
		this.commandes = commandes;
	}
	
	
	

}
