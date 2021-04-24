package br.com.zupacademy.jonathan.casadocodigo.pais;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.zupacademy.jonathan.casadocodigo.config.validation.UniqueValue;

public class PaisRequest {

	@NotBlank
	@UniqueValue(domainClass = Pais.class, fieldName = "nome", message = "Já existe um País com esse nome")
	private String nome;

	@JsonCreator //annotation para construtor só com um argumento
	public PaisRequest(@NotBlank String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	
	public Pais toModel() {
		return new Pais(this.nome);
	}
}
