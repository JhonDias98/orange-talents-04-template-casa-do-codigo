package br.com.zupacademy.jonathan.casadocodigo.autor;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class Autor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nome;
	@NotBlank
	@Email
	@Column(unique = true)
	private String email;
	@NotBlank
	@Length(max = 400)
	private String descricao;
	@NotNull
	private LocalDateTime instanteRegistro;

	public Autor(@NotBlank String nome, 
			@NotBlank @Email String email, 
			@NotBlank @Length(max = 400) String descricao) {
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
		this.instanteRegistro = LocalDateTime.now();
	}
	
	@Deprecated
	public Autor() {}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getDescricao() {
		return descricao;
	}

	public LocalDateTime getInstanteRegistro() {
		return instanteRegistro;
	}
}