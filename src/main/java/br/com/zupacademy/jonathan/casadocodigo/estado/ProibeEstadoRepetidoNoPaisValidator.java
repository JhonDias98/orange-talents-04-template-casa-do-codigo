package br.com.zupacademy.jonathan.casadocodigo.estado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProibeEstadoRepetidoNoPaisValidator implements Validator {

	@Autowired
	private EstadoRepository repository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return EstadoRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return;
		}
		
		EstadoRequest request = (EstadoRequest) target;
		
		List<?> list = repository.consultarEstado(request.getNome(), request.getPaisId());
		
		if(!list.isEmpty()) {
			errors.rejectValue("nome", null, 
					"O estado: " + request.getNome() + " já existe no país informado");
		}
	}
}
