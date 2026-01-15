package com.ipi.jva320.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.ipi.jva320.exception.SalarieException;
import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.service.SalarieAideADomicileService;

import jakarta.persistence.EntityExistsException;

@org.springframework.stereotype.Controller
public class SalarieController {
	
	@Autowired
	private 	SalarieAideADomicileService salarieAideADomicileService;
	
	@GetMapping("/salaries/{id}") 
	public String getDetailSalarie(@PathVariable(value = "id") Long id, final ModelMap model) {
		SalarieAideADomicile unSalarie = salarieAideADomicileService.getSalarie(id);
		model.put("salarie", unSalarie);

		return "detail_Salarie";
	}
	
	@GetMapping("/salaries/aide/new")
	public String nouveauSalarie(final ModelMap model) {
		SalarieAideADomicile unSalarie = new SalarieAideADomicile();
		model.put("salarie", unSalarie);
		
		return "nouveau_Salarie";
	}
	
	@PostMapping("/salaries/nouveauSalarie")
	public String newSalarie(
			SalarieAideADomicile newSalarie) throws SalarieException {
		
		///SalarieAideADomicile nouveau = new SalarieAideADomicile();
		//nouveau.setId(bindingResult.id);
		//nouveau.setNom(nom);
		//nouveau.setMoisEnCours(moisEnCours);
		//nouveau.setMoisDebutContrat(moisDebutContrat);
		//nouveau.setJoursTravaillesAnneeN(joursTravaillesAnneeN);
		//nouveau.setCongesPayesAcquisAnneeN(congesPayesAcquisAnneeN);
		//nouveau.setJoursTravaillesAnneeNMoins1(joursTravaillesAnneeNMoins1);
		//nouveau.setCongesPayesAcquisAnneeNMoins1(congesPayesAcquisAnneeNMoins1);
		//nouveau.setCongesPayesPrisAnneeNMoins1(congesPayesPrisAnneeNMoins1);
		
		
		salarieAideADomicileService.creerSalarieAideADomicile(newSalarie);
		
		return "redirect:/salaries/" + newSalarie.getId();
	}
	
	@GetMapping("/salaries/{id}/delete")
	public String deleteSalarie(@PathVariable(value = "id") Long id, final ModelMap model) throws EntityExistsException, SalarieException {
		salarieAideADomicileService.deleteSalarieAideADomicile(id);
		model.put("salaries", salarieAideADomicileService.getSalaries());
		return "list";
	}
	
	@PostMapping("/salaries/{id}")
	public String updateSalarie(@PathVariable(value = "id") Long id, final ModelMap model,
			SalarieAideADomicile updatedSalarie) throws EntityExistsException, SalarieException {
		salarieAideADomicileService.updateSalarieAideADomicile(updatedSalarie);
		model.put("salaries", salarieAideADomicileService.getSalaries());
		return "list";
	}
	
	@GetMapping("/salaries")
    public String searchSalarie(@RequestParam(value = "nom", required = false) String nom, 
                                final ModelMap model) {
        
        // Si le paramètre "nom" est présent dans l'URL
        if (nom != null && !nom.isEmpty()) {
            
            // 1. On appelle le service pour chercher
            List<SalarieAideADomicile> salaries = salarieAideADomicileService.getSalaries(nom);
            
            // 2. Gestion de l'erreur 404 si la liste est vide
            if (salaries.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun salarié trouvé avec le nom : " + nom);
            }
            
            // 3. Si on a trouvé, on prend le premier résultat (car la vue détail attend un seul objet 'salarie')
            model.put("salarie", salaries.get(0));
            
            // 4. On renvoie directement vers la vue de DÉTAIL
            return "detail_Salarie"; 
        }

        // Si aucun nom n'est fourni, on affiche la liste complète (comportement par défaut)
        model.put("salaries", salarieAideADomicileService.getSalaries());
        return "list";
    }
	
}
