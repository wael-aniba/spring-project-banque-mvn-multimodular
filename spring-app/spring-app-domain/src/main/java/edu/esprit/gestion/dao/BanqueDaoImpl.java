package edu.esprit.gestion.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import edu.esprit.gestion.entities.Client;
import edu.esprit.gestion.entities.Compte;
import edu.esprit.gestion.entities.Employe;
import edu.esprit.gestion.entities.Groupe;
import edu.esprit.gestion.entities.Operation;


public class BanqueDaoImpl implements IBanqueDao{
	
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Client addClient(Client c) {
		em.persist(c);
		return c;
	}

	@Override
	public Employe addEmploye(Employe e, Long codeSup) {
		if(codeSup != null){
			Employe sup = em.find(Employe.class, codeSup);
			e.setEmployeSup(sup);
		}
		em.persist(e);
		return e;
	}

	@Override
	public Groupe addGroupe(Groupe g) {
		em.persist(g);
		return g;
	}

	@Override
	public void addEmployeToGroupe(Long codeEmp, Long codeGr) {
		
		Employe e = em.find(Employe.class, codeEmp);
		Groupe g = em.find(Groupe.class, codeGr);
		
		e.getGroupes().add(g);
		g.getEmployes().add(e);
		
	}

	@Override
	public Compte addCompte(Compte cp, Long codeCli, Long codeEmp) {
		
		Client cli = em.find(Client.class, codeCli);
		Employe emp =  em.find(Employe.class, codeEmp);
		
		cp.setClient(cli);
		cp.setEmploye(emp);
		//cli.getComptes().add(cp);
		
		em.persist(cp);
		return cp;
	}

	@Override
	public Operation addOperation(Operation o, String codeCpte, Long codeEmp) {
		
		Compte cp = em.find(Compte.class, codeCpte);
		Employe emp = em.find(Employe.class, codeEmp);
		
		o.setCompte(cp);
		o.setEmploye(emp);
		//cp.getOperations().add(o);

		em.persist(o);
		return o;
	}

	@Override
	public Compte consulterCompte(String codeCpte) {
		
		Compte cp = em.find(Compte.class, codeCpte);
		
		if(null == cp)
			throw new RuntimeException("Compte introuvable");
		
		return cp;
	}

	@Override
	public List<Operation> consulterOperationCompte(String codeCpte) {
		
		StringBuilder request = new StringBuilder("select o from Operation o where o.compte.codeCompte=:param");
		TypedQuery<Operation> tQuery = em.createQuery(request.toString(), Operation.class);
		tQuery.setParameter("param", codeCpte);
		
		return tQuery.getResultList();
	}

	@Override
	public Client consulterClient(Long codeCli) {

		Client client = em.find(Client.class, codeCli);
		if(client == null)
			throw new RuntimeException("Client Introuvable!");
		
		return client;
	}

	@Override
	public List<Client> consulterClients(String mc) {
		
		StringBuilder request = new StringBuilder("select c from Client c where c.nomClient=:param");
		TypedQuery<Client> tQuery = em.createQuery(request.toString(), Client.class);
		tQuery.setParameter("param", "%"+mc+"%");
		
		return tQuery.getResultList();
	}

	@Override
	public List<Compte> getComptes(Long codeCli) {
		
		StringBuilder request = new StringBuilder("select c from Compte c where c.client.codeclient=:param");
		TypedQuery<Compte> tQuery = em.createQuery(request.toString(), Compte.class);
		tQuery.setParameter("param", codeCli);
		
		return tQuery.getResultList();
	}

	@Override
	public List<Compte> getCompteByEmploye(Long codeEmp) {

		StringBuilder request = new StringBuilder("select c from Compte c where c.employe.codeEmploye=:param");
		TypedQuery<Compte> tQuery = em.createQuery(request.toString(), Compte.class);
		tQuery.setParameter("param", codeEmp);
		
		return tQuery.getResultList();
		
	}

	@Override
	public List<Employe> getEmployes() {
		
		StringBuilder request = new StringBuilder("select e from Employe e");
		TypedQuery<Employe> tQuery = em.createQuery(request.toString(), Employe.class);
		
		return tQuery.getResultList();
	}

	@Override
	public List<Groupe> getGroupes() {

		StringBuilder request = new StringBuilder("select g from groupe g");
		TypedQuery<Groupe> tQuery = em.createQuery(request.toString(), Groupe.class);
		
		return tQuery.getResultList();
	}

	@Override
	public List<Employe> getEmployesByGroupes(Long codeGr) {

		StringBuilder request = new StringBuilder("select e from Employe e where e.groupes.codeGroupe=:param");
		TypedQuery<Employe> tQuery = em.createQuery(request.toString(), Employe.class);
		tQuery.setParameter("param", codeGr);
		
		return tQuery.getResultList();
		
	}
	
	

}
