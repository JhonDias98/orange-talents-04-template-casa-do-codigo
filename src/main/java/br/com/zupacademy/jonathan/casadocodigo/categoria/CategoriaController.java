package br.com.zupacademy.jonathan.casadocodigo.categoria;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@PersistenceContext
	private EntityManager manager;
	
	//classe com validação e método init não é mais necessário.
	//annotation customizada já está fazendo a validação.
//	@Autowired
//	private ProibeNomeCategoriaDuplicadoValidator proibeNomeCategoriaDuplicadoValidator;
//	
//	/**
//     * método com a anotação @InitBinder o código é executado no primeiro request
//     */
//	@InitBinder
//	public void init(WebDataBinder binder) {
//		binder.addValidators(proibeNomeCategoriaDuplicadoValidator);
//	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<CategoriaResponse> salvarCategoria(@RequestBody @Valid CategoriaRequest categoriaRequest) {
		Categoria categoria = categoriaRequest.toModel();
		manager.persist(categoria);
		
		return ResponseEntity.ok(new CategoriaResponse(categoria));
	}
}
