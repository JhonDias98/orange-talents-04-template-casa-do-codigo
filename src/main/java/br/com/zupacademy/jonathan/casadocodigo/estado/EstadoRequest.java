package br.com.zupacademy.jonathan.casadocodigo.estado;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.jonathan.casadocodigo.config.validation.ExistsValue;
import br.com.zupacademy.jonathan.casadocodigo.pais.Pais;

public class EstadoRequest {

	@NotBlank
	private String nome;
	@NotNull
	@ExistsValue(fieldName = "id", targetClass = Pais.class, message = "Id do País não existe")
	private Long paisId;

	public EstadoRequest(@NotBlank String nome, @NotNull Long paisId) {
		this.nome = nome;
		this.paisId = paisId;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Long getPaisId() {
		return paisId;
	}

	public Estado toModel(EntityManager manager) {
		return new Estado(this.nome, manager.find(Pais.class, paisId));
	}
}