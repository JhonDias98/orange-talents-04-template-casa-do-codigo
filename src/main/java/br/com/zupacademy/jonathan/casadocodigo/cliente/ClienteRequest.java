package br.com.zupacademy.jonathan.casadocodigo.cliente;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.jonathan.casadocodigo.config.validation.CPForCNPJ;
import br.com.zupacademy.jonathan.casadocodigo.config.validation.ExistsValue;
import br.com.zupacademy.jonathan.casadocodigo.config.validation.UniqueValue;
import br.com.zupacademy.jonathan.casadocodigo.estado.Estado;
import br.com.zupacademy.jonathan.casadocodigo.pais.Pais;

public class ClienteRequest {

	@NotBlank
	@Email
	@UniqueValue(domainClass = Cliente.class, fieldName = "email", message = "Já existe um cliente com esse e-mail")
	private String email;
	@NotBlank
	private String nome;
	@NotBlank
	private String sobrenome;
	@NotBlank
	@UniqueValue(domainClass = Cliente.class, fieldName = "documento", message = "Já existe um cliente com esse documento")
	@CPForCNPJ
	private String documento;
	@NotBlank
	private String endereco;
	@NotBlank
	private String complemento;
	@NotBlank
	private String cidade;
	@NotNull
	@ExistsValue(fieldName = "id", targetClass = Pais.class, message = "Id do País não existe")
	private Long paisId;
	private Long estadoId;
	@NotBlank
	private String telefone;
	@NotBlank
	private String cep;

	public ClienteRequest(@NotBlank @Email String email, @NotBlank String nome, @NotBlank String sobrenome,
			@NotBlank String documento, @NotBlank String endereco, @NotBlank String complemento,
			@NotBlank String cidade, @NotNull Long paisId, Long estadoId, @NotBlank String telefone,
			@NotBlank String cep) {
		this.email = email;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.documento = documento;
		this.endereco = endereco;
		this.complemento = complemento;
		this.cidade = cidade;
		this.paisId = paisId;
		this.estadoId = estadoId;
		this.telefone = telefone;
		this.cep = cep;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public String getDocumento() {
		return documento;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public Long getPaisId() {
		return paisId;
	}

	public Long getEstadoId() {
		return estadoId;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getCep() {
		return cep;
	}

	public Cliente toModel(EntityManager manager) {
		Cliente cliente = new Cliente(email, nome, sobrenome, documento,
				endereco, complemento, cidade,
				manager.find(Pais.class, paisId), telefone, cep);
		if(estadoId != null) {
			cliente.setEstado(manager.find(Estado.class, estadoId));
		}
		return cliente;
	}
}
