package br.com.lucas.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.lucas.customException.DatesExpection;
import br.com.lucas.customException.ListaVaziaException;
import br.com.lucas.customException.UsuarioNaoEncontradoException;
import br.com.lucas.customException.ValidacaoCPFException;
import br.com.lucas.entitys.Pessoa;
import br.com.lucas.repository.PessoaRepository;
import br.com.lucas.utils.ValidaDocumentos;

@Service
public class PessoaService {

	@Autowired
	PessoaRepository pessoaRepository;

	public Pessoa salvar(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

	@Transactional
	public void deletarPessoa(Integer idPessoa) {
		pessoaRepository.deleteById(idPessoa);
	}

	public List<Pessoa> findOneByNome(String nome) {
		return pessoaRepository.findByNome(nome);
	}

	public Optional<Pessoa> findByIdPessoa(Integer id) {
		return pessoaRepository.findById(id);
	}

	public Page<Pessoa> buscarTodasPessoas(Pageable pageable) {
		return pessoaRepository.findAll(pageable);
	}

	public Pessoa adicionarPessoa(Pessoa pessoa) throws ValidacaoCPFException, DatesExpection, ListaVaziaException {

		boolean cpfValido = ValidaDocumentos.isValidoCPF(pessoa.getCpf());

		if (cpfValido == false)
			throw new ValidacaoCPFException("Cpf com formato inválido!");

		if (pessoa.getDataNascimento().getTime() > new Date().getTime())
			throw new DatesExpection("Data de nascimento não pode ser uma data Futura!.");

		if (pessoa.getListaContatos().isEmpty())
			throw new ListaVaziaException("Lista de Contatos não pode estar Vazia!");

		Pessoa pessoaEncontradaNoBanco = pessoaRepository.findByCpf(pessoa.getCpf());
		if (pessoaEncontradaNoBanco != null) {
			if(pessoaEncontradaNoBanco.getId() != pessoa.getId()) 
			throw new ValidacaoCPFException("Cpf já cadastrado para outra pessoa.");
		}
			
		Pessoa ultimaPessoaAdicionada = pessoaRepository.findFirstByOrderByIdDesc();

		if (ultimaPessoaAdicionada != null) {
			Integer idPessoa = ultimaPessoaAdicionada.getId();
			pessoa.setId(++idPessoa);
		} else {
			pessoa.setId(1);
		}

		return pessoaRepository.save(pessoa);
	}

	public Pessoa atualizar(Pessoa pessoa) throws UsuarioNaoEncontradoException {

		boolean cpfValido = ValidaDocumentos.isValidoCPF(pessoa.getCpf());
		if (cpfValido == false)
			throw new ValidacaoCPFException("Cpf com formato inválido!");

		Pessoa pessoaEncontradaNoBanco = pessoaRepository.findByCpf(pessoa.getCpf());
		Optional<Pessoa> pessoaASerAtualizada = pessoaRepository.findById(pessoa.getId());
		if (pessoaEncontradaNoBanco != null) {
			if (pessoaEncontradaNoBanco.getId() != pessoa.getId())
				throw new ValidacaoCPFException("Cpf já cadastrado para outra pessoa, não é possivel atualizar!");
		}

		if (pessoaASerAtualizada == null)
			throw new UsuarioNaoEncontradoException("A pessoa fornecida não existe para atualizar!");

		return pessoaRepository.save(pessoa);

	}

}
