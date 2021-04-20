package br.com.zupacademy.jonathan.casadocodigo.autor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autores")
public class AutorController {

	@PersistenceContext
	EntityManager manager;
	
	@Autowired
	private ProibeEmailDuplicadoAutorValidator proibeEmailDuplicadoAutorValidator;
	
	/**
     * método com a anotação @InitBinder o código é executado no primeiro request
     */
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(proibeEmailDuplicadoAutorValidator);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<AutorResponse> salvarAutor(@RequestBody @Valid AutorRequest autorRequest) {
		Autor autor = autorRequest.toModel();
		manager.persist(autor);
		return ResponseEntity.ok(new AutorResponse(autor));
	}
}
