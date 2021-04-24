package br.com.zupacademy.jonathan.casadocodigo.estado;

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
@RequestMapping("/estados")
public class EstadoController {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private ProibeEstadoRepetidoNoPaisValidator proibeEstadoRepetidoNoPaisValidator;
	

	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(proibeEstadoRepetidoNoPaisValidator);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<EstadoResponse> cadastrarEstado(@RequestBody @Valid EstadoRequest request) {
		Estado estado = request.toModel(manager);
		manager.persist(estado);
		return ResponseEntity.ok(new EstadoResponse(estado));
	}
}
