package br.com.zupacademy.jonathan.casadocodigo.pais;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import br.com.zupacademy.jonathan.casadocodigo.estado.Estado;

@Entity
public class Pais {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nome;
	@OneToMany(mappedBy = "pais", cascade = CascadeType.ALL)
	private List<Estado> estados;
	
	public Pais(@NotBlank String nome) {
		this.nome = nome;
	}

	@Deprecated
	public Pais() {}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public List<Estado> getEstados() {
		return estados;
	}
}
