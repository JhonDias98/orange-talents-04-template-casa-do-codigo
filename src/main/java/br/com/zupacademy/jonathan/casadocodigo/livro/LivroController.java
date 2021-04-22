package br.com.zupacademy.jonathan.casadocodigo.livro;

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
@RequestMapping("/livros")
public class LivroController {

	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping
	@Transactional
	public ResponseEntity<LivroResponse> salvar(@RequestBody @Valid LivroRequest request) {
		Livro livro = request.toModel(manager);
		manager.persist(livro);
		return ResponseEntity.ok(new LivroResponse(livro));
	}
}
