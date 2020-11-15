package br.com.lucas.entitys;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pes_contato")
public class Contato {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	@NotNull
	@NotBlank
	private String nome;
	@NotNull(message = "nome não pode ser nulo")
	@NotBlank(message = "nome não pode ser vazio")
	private String telefone;
	@NotNull
	@NotBlank
	@Email(message = "Email inválido")
	private String email;

	public Contato() {
	}

	public Contato(String nome, @NotNull String telefone, @NotNull String email) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
	}

	public Contato(Integer id, @NotNull String nome, @NotNull String telefone, @NotNull String email) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
