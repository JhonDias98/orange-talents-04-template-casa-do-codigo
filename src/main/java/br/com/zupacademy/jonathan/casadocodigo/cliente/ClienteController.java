package br.com.zupacademy.jonathan.casadocodigo.cliente;

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
@RequestMapping("/clientes")
public class ClienteController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private EstadoObrigatorioSePaisTemEstadoValidator validarEstado;
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(validarEstado);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ClienteResponse> cadastrarCliente(@RequestBody @Valid ClienteRequest request) {
		Cliente cliente = request.toModel(manager);
		manager.persist(cliente);
		return ResponseEntity.ok(new ClienteResponse(cliente));
	}
}
