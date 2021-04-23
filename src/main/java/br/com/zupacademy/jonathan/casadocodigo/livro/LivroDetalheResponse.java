package br.com.zupacademy.jonathan.casadocodigo.livro;

import java.math.BigDecimal;
import java.time.LocalDate;
import br.com.zupacademy.jonathan.casadocodigo.autor.AutorResponse;
import br.com.zupacademy.jonathan.casadocodigo.categoria.CategoriaResponse;

public class LivroDetalheResponse {

	private String titulo;

	private String resumo;
	
	private String sumario;
	
	private BigDecimal preco;
	
	private Integer numeroPaginas;
	
	private String isbn;
	
	private LocalDate dataPublicacao;
	
	private CategoriaResponse categoria;
	
	private AutorResponse autor;
	
	public LivroDetalheResponse(Livro livro) {
		this.titulo = livro.getTitulo();
		this.resumo = livro.getResumo();
		this.sumario = livro.getSumario();
		this.preco = livro.getPreco();
		this.numeroPaginas = livro.getNumeroPaginas();
		this.isbn = livro.getIsbn();
		this.dataPublicacao = livro.getDataPublicacao();
		this.categoria = new CategoriaResponse(livro.getCategoria());
		this.autor = new AutorResponse(livro.getAutor());
	}

	public String getTitulo() {
		return titulo;
	}

	public String getResumo() {
		return resumo;
	}

	public String getSumario() {
		return sumario;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public Integer getNumeroPaginas() {
		return numeroPaginas;
	}

	public String getIsbn() {
		return isbn;
	}

	public LocalDate getDataPublicacao() {
		return dataPublicacao;
	}

	public CategoriaResponse getCategoria() {
		return categoria;
	}

	public AutorResponse getAutor() {
		return autor;
	}
}
