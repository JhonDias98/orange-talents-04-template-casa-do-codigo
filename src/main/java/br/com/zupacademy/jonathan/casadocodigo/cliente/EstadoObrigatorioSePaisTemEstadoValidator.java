package br.com.zupacademy.jonathan.casadocodigo.cliente;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zupacademy.jonathan.casadocodigo.pais.Pais;

@Component
public class EstadoObrigatorioSePaisTemEstadoValidator implements Validator{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ClienteRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return;
		}
		
		ClienteRequest request = (ClienteRequest) target;
		
		Pais pais = manager.find(Pais.class, request.getPaisId());
		
		boolean naoPossuiEstado = pais.getEstados().isEmpty();
		
		if(!naoPossuiEstado && request.getEstadoId() == null) {
			errors.rejectValue("paisId", null, 
					"O país informado possuí estados cadastrados, selecione um");
		}
		
		List<Long> idEstados = pais.getEstados().stream()
				.map(estados -> {return estados.getId();
				}).collect(Collectors.toList());
		
		if (!idEstados.contains(request.getEstadoId())) {
			errors.rejectValue("paisId", null, "O estado informado não possui cadastro no país");
		}
	}

}
