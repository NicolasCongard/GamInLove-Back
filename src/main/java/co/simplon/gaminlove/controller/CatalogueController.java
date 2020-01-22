package co.simplon.gaminlove.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.gaminlove.model.Catalogue;
import co.simplon.gaminlove.model.Jeu;
import co.simplon.gaminlove.repository.CatalogueRepository;

@RestController
@RequestMapping(path = "/catalogue")
@CrossOrigin("*")
public class CatalogueController {

	@Autowired
	private CatalogueRepository catalogueRepository;

	@RequestMapping(path = "/add/{jeux}")
	public Catalogue addNew(@PathVariable Collection<Jeu> jeux) {
		Catalogue newCatalogue = new Catalogue();
		newCatalogue.setJeux(jeux);
		return catalogueRepository.save(newCatalogue);
	}

	@RequestMapping("/del/{id}")
	public void delOne(@PathVariable int id) {
		Optional<Catalogue> optCatalogue = catalogueRepository.findById(id);
		if (optCatalogue.isPresent()) {
			catalogueRepository.deleteById(id);
			System.out.println("Catalogue supprimée");
		} else {
			System.out.println("Pas de catalogue à supprimer");
		}
	}
}