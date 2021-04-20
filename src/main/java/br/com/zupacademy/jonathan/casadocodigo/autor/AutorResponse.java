package br.com.zupacademy.jonathan.casadocodigo.autor;

public class AutorResponse {
	private Long id;
	
	private String nome;
	
	public AutorResponse(Autor autor) {
		this.id = autor.getId();
		this.nome = autor.getNome();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
}