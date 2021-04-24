package br.com.zupacademy.jonathan.casadocodigo.pais;

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
@RequestMapping("/pais")
public class PaisController {

	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping
	@Transactional
	public ResponseEntity<PaisResponse> cadastrarPais(@RequestBody @Valid PaisRequest request) {
		Pais pais = request.toModel();
		manager.persist(pais);
		return ResponseEntity.ok(new PaisResponse(pais));
	}
}
