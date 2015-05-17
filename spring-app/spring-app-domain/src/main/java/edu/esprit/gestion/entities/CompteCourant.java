package edu.esprit.gestion.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CC")
public class CompteCourant extends Compte {

	private Double decouvert;

	public Double getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(Double decouvert) {
		this.decouvert = decouvert;
	}

	public CompteCourant() {
		super();
	}

	public CompteCourant(String codeCompte, Date dateCreation, Double solde,
			Double decouvert) {
		super(codeCompte, dateCreation, solde);
		this.decouvert = decouvert;
	}

}
