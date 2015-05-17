package edu.esprit.gestion.service;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import edu.esprit.gestion.dao.IBanqueDao;
import edu.esprit.gestion.entities.Client;
import edu.esprit.gestion.entities.Compte;
import edu.esprit.gestion.entities.Employe;
import edu.esprit.gestion.entities.Groupe;
import edu.esprit.gestion.entities.Operation;
import edu.esprit.gestion.entities.Retrait;
import edu.esprit.gestion.entities.Versement;

@Transactional
public class BanqueMetierImpl implements IBanqueMetier {

	private IBanqueDao dao;

	public void setDao(IBanqueDao dao) {
		this.dao = dao;
	}

	@Override
	public Client addClient(Client c) {

		return dao.addClient(c);
	}

	@Override
	public Employe addEmploye(Employe e, Long codeSup) {

		return dao.addEmploye(e, codeSup);
	}

	@Override
	public Groupe addGroupe(Groupe g) {

		return dao.addGroupe(g);
	}

	@Override
	public void addEmployeToGroupe(Long codeEmp, Long codeGr) {

		dao.addEmployeToGroupe(codeEmp, codeGr);

	}

	@Override
	public Compte addCompte(Compte p, Long codeCli, Long codeEmp) {

		return dao.addCompte(p, codeCli, codeEmp);
	}

	@Override
	public void verser(Double mt, String cpte, Long codeEmp) {

		dao.addOperation(new Versement(new Date(), mt), cpte, codeEmp);

		Compte cp = dao.consulterCompte(cpte);
		cp.setSolde(cp.getSolde() + mt);

	}

	@Override
	public void retirer(Double mt, String cpte, Long codeEmp) {

		dao.addOperation(new Retrait(new Date(), mt), cpte, codeEmp);

		Compte cp = dao.consulterCompte(cpte);
		cp.setSolde(cp.getSolde() - mt);
	}

	@Override
	public void virement(Double mt, String cpte1, String cpte2, Long codeEmp) {

		retirer(mt, cpte1, codeEmp);
		verser(mt, cpte2, codeEmp);
	}

	@Override
	public Compte consulterCompte(String codeCpte) {

		return dao.consulterCompte(codeCpte);
	}

	@Override
	public List<Operation> consulterOperationCompte(String codeCpte) {
		
		return dao.consulterOperationCompte(codeCpte);
	}

	@Override
	public Client consulterClient(Long codeCli) {

		return dao.consulterClient(codeCli);
	}

	@Override
	public List<Client> consulterClients(String mc) {
		
		return dao.consulterClients(mc);
	}

	@Override
	public List<Compte> getComptes(Long codeCli) {

		return dao.getComptes(codeCli);
	}

	@Override
	public List<Compte> getCompteByEmploye(Long codeEmp) {

		return dao.getCompteByEmploye(codeEmp);
	}

	@Override
	public List<Employe> getEmployes() {

		return dao.getEmployes();
	}

	@Override
	public List<Groupe> getGroupes() {

		return dao.getGroupes();
	}

	@Override
	public List<Employe> getEmployesByGroupes(Long codeGr) {

		return dao.getEmployesByGroupes(codeGr);
	}

}
