package br.com.zupacademy.jonathan.casadocodigo.categoria;

import javax.validation.constraints.NotBlank;

public class CategoriaRequest {

	@NotBlank
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
