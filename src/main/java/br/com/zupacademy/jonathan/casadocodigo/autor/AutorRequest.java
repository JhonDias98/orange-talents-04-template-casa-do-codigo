package br.com.zupacademy.jonathan.casadocodigo.autor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.jonathan.casadocodigo.config.validation.UniqueValue;

public class AutorRequest {
	@NotBlank
    private String nome;
	@NotBlank
    @Email
    @UniqueValue(domainClass = Autor.class, fieldName = "email", message = "Já existe um(a) autor(a) com esse e-mail")
    private String email;
    @NotBlank
    @Length(max = 400)
    private String descricao;

    public AutorRequest(String nome, String email, String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
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

    public Autor toModel() {
        return new Autor(this.nome, this.email, this.descricao);
    }
}