package edu.esprit.gestion.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CE")
public class CompteEpargne extends Compte {

	private Double taux;

	public Double getTaux() {
		return taux;
	}

	public void setTaux(Double taux) {
		this.taux = taux;
	}

	public CompteEpargne(String codeCompte, Date dateCreation, Double solde,
			Double taux) {
		super(codeCompte, dateCreation, solde);
		this.taux = taux;
	}

	public CompteEpargne() {
		super();
	}

}
