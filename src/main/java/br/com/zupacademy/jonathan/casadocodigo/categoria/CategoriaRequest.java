package br.com.zupacademy.jonathan.casadocodigo.categoria;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.jonathan.casadocodigo.config.validation.UniqueValue;

public class CategoriaRequest {

	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome", message = "Já existe uma categoria com esse nome")
	private String nome;
	
	public CategoriaRequest(@NotBlank String nome) {
		this.nome = nome;
	}
	
	@Deprecated
	public CategoriaRequest() {}

	public String getNome() {
		return nome;
	}

	public Categoria toModel() {
		return new Categoria(this.nome);
	}
}
