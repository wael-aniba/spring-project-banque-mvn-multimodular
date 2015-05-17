package edu.esprit.gestion.model;

import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import edu.esprit.gestion.entities.Compte;
import edu.esprit.gestion.entities.Operation;

/**
 * @author wael.aniba
 *
 */
public class BanqueForm {

	@NotEmpty
	@Size(min = 3, max = 10)
	private String code;
	private Compte compte;
	private String typeCompte;
	private String exception;
	private List<Operation> operations;
	private String typeOperation;
	@DecimalMin(value = "50")
	private Double montant = 50.0;
	@NotEmpty
	@Size(min = 3, max = 10)
	private String codeDest = "XXX";
	private String action;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public String getTypeCompte() {
		return typeCompte;
	}

	public void setTypeCompte(String typeCompte) {
		this.typeCompte = typeCompte;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	public String getTypeOperation() {
		return typeOperation;
	}

	public void setTypeOperation(String typeOperation) {
		this.typeOperation = typeOperation;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public String getCodeDest() {
		return codeDest;
	}

	public void setCodeDest(String codeDest) {
		this.codeDest = codeDest;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
