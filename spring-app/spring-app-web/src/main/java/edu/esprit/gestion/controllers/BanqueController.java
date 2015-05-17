package edu.esprit.gestion.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.esprit.gestion.entities.Compte;
import edu.esprit.gestion.entities.CompteCourant;
import edu.esprit.gestion.model.BanqueForm;
import edu.esprit.gestion.service.IBanqueMetier;

@Controller
public class BanqueController {

	@Autowired
	IBanqueMetier metier;

	@RequestMapping(value = "/index")
	public String index(Model model) {

		model.addAttribute("banqueForm", new BanqueForm());
		return "banque";
	}

	@RequestMapping(value = "/chargerCompte")
	public String chargerC(@Valid BanqueForm bf, BindingResult br, Model model) {

		if(br.hasErrors()) return "banque";

		try {

			Compte cp = metier.consulterCompte(bf.getCode());
			bf.setTypeCompte( cp instanceof CompteCourant? "Courant":"Epargne");
			bf.setOperations(metier.consulterOperationCompte(bf.getCode()));
			bf.setCompte(cp);

		} catch (Exception e) {
			bf.setException(e.getMessage());
		}

		model.addAttribute("banqueForm", bf);
		return "banque";
	}

	@RequestMapping(value = "/saveOperation")
	public String saveOp(@Valid BanqueForm bf, BindingResult br){
		
		try {
		
		if(bf.getAction() != null){


			if(!br.hasErrors()){
				
				if(bf.getTypeOperation().equals("VER")){
					metier.verser(bf.getMontant(), bf.getCode(), 1L);

				}else if(bf.getTypeOperation().equals("RET")){

					metier.retirer(bf.getMontant(), bf.getCode(), 1L);

				}else if(bf.getTypeOperation().equals("VIR")){

					metier.virement(bf.getMontant(), bf.getCode(), bf.getCodeDest() , 1L);
				}

				bf.setTypeOperation(null);

			}

		}

			Compte cp = metier.consulterCompte(bf.getCode());
			bf.setTypeCompte( cp instanceof CompteCourant? "Courant":"Epargne");
			bf.setOperations(metier.consulterOperationCompte(bf.getCode()));
			bf.setCompte(cp);

		} catch (Exception e) {
			bf.setCodeDest(e.getMessage());
		}finally{
			
			Compte cp = metier.consulterCompte(bf.getCode());
			bf.setTypeCompte( cp instanceof CompteCourant? "Courant":"Epargne");
			bf.setOperations(metier.consulterOperationCompte(bf.getCode()));
			bf.setCompte(cp);
		}

		return "banque";

	}
}
