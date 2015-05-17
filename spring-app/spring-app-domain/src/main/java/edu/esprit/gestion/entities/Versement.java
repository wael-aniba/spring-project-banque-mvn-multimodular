package edu.esprit.gestion.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "V")
public class Versement extends Operation {

	public Versement() {
		super();
	}

	public Versement(Date dateOperation, Double montant) {
		super(dateOperation, montant);
	}
	
	@Override
	public String toString() {
		return "versement";
	}
	
}
