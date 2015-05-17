package edu.esprit.gestion.dao;

import java.util.List;

import edu.esprit.gestion.entities.Client;
import edu.esprit.gestion.entities.Compte;
import edu.esprit.gestion.entities.Employe;
import edu.esprit.gestion.entities.Groupe;
import edu.esprit.gestion.entities.Operation;

public interface IBanqueDao {

	public Client addClient(Client c);
	public Employe addEmploye(Employe e, Long codeSup);
	public Groupe addGroupe(Groupe g);
	public void addEmployeToGroupe(Long codeEmp, Long codeGr);
	public Compte addCompte(Compte p, Long codeCli, Long codeEmp);
	public Operation addOperation(Operation o, String codeCpte, Long codeEmp);
	public Compte consulterCompte(String codeCpte);
	public List<Operation> consulterOperationCompte(String codeCpte);
	public Client consulterClient(Long codeCli);
	public List<Client> consulterClients(String mc);
	public List<Compte> getComptes(Long codeCli);
	public List<Compte> getCompteByEmploye(Long codeEmp);
	public List<Employe> getEmployes();
	public List<Groupe> getGroupes();
	public List<Employe> getEmployesByGroupes(Long codeGr);
	
}
