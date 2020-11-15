package br.com.lucas.entitys;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.lucas.repository.PessoaRepository;

@Entity
@Table(name = "pes_pessoa")
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull(message = "nome não pode ser nulo") @NotBlank(message = "nome não pode ser vazio") 
	private String nome;
	@NotNull(message = "cpf não pode ser nulo") @NotBlank(message = "cpf") 
	@Column(unique=true)
	private String cpf;  
	@NotNull(message = "data não pode ser vazia")
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataNascimento;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Contato> listaContatos;
	
	
	public Pessoa() {
	}
	
	public Pessoa(@NotNull String nome, @NotNull String cpf, @NotNull Date dataNascimento,
			List<Contato> listaContatos) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.listaContatos = listaContatos;
	}
	
	public Pessoa(Integer id, @NotNull String nome, @NotNull String cpf, @NotNull Date dataNascimento,
			List<Contato> listaContatos) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.listaContatos = listaContatos;
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
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public List<Contato> getListaContatos() {
		return listaContatos;
	}
	public void setListaContatos(List<Contato> listaContatos) {
		this.listaContatos = listaContatos;
	}
	
	
	
}
	
	