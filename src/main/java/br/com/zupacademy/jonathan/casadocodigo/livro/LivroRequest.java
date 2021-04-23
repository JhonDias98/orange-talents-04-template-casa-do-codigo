package br.com.zupacademy.jonathan.casadocodigo.livro;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.util.Assert;

import br.com.zupacademy.jonathan.casadocodigo.autor.Autor;
import br.com.zupacademy.jonathan.casadocodigo.categoria.Categoria;
import br.com.zupacademy.jonathan.casadocodigo.config.validation.ExistsValue;
import br.com.zupacademy.jonathan.casadocodigo.config.validation.UniqueValue;

public class LivroRequest {
	@NotBlank
	@UniqueValue(domainClass = Livro.class, fieldName = "titulo", message = "Já existe um titulo com esse nome")
	private String titulo;
	@NotBlank
	@Size(max = 500)
	private String resumo;
	@NotBlank
	private String sumario;
	@NotNull
	@Min(20)
	private BigDecimal preco;
	@NotNull
	@Min(100)
	private Integer numeroPaginas;
	@NotBlank
	@UniqueValue(domainClass = Livro.class, fieldName = "isbn", message = "O Isbn informado já existe")
	private String isbn;
	@Future
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
	private LocalDate dataPublicacao;
	@NotNull
	@ExistsValue(fieldName = "id", targetClass = Categoria.class, message = "Id da Categoria não existe")
	private Long categoriaId;
	@NotNull
	@ExistsValue(fieldName = "id", targetClass = Autor.class, message = "Id do Autor(a) não existe")
	private Long autorId;
	
	public LivroRequest(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo, @NotBlank String sumario,
			@NotNull @Min(20) BigDecimal preco, @NotNull @Min(100) Integer numeroPaginas, @NotBlank String isbn,
			@Future @NotNull LocalDate dataPublicacao, @NotNull Long categoriaId, @NotNull Long autorId) {
		this.titulo = titulo;
		this.resumo = resumo;
		this.sumario = sumario;
		this.preco = preco;
		this.numeroPaginas = numeroPaginas;
		this.isbn = isbn;
		this.dataPublicacao = dataPublicacao;
		this.categoriaId = categoriaId;
		this.autorId = autorId;
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

	public Long getCategoriaId() {
		return categoriaId;
	}

	public Long getAutorId() {
		return autorId;
	}
	
	public Livro toModel(EntityManager manager) {
		Categoria categoria = manager.find(Categoria.class, categoriaId);
        Autor autor = manager.find(Autor.class, autorId);

        Assert.state(autor!=null, "Autor(a) não encontrado(a) para o id " + autorId);
        Assert.state(categoria!=null, "Categoria não encontrado para o id " + categoriaId);
		
		return new Livro(this.titulo, this.resumo, this.sumario, this.preco, 
				this.numeroPaginas, this.isbn, this.dataPublicacao, categoria, autor);
	}
}
