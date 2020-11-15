package br.com.lucas.rest;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucas.customException.DatesExpection;
import br.com.lucas.customException.ListaVaziaException;
import br.com.lucas.customException.UsuarioNaoEncontradoException;
import br.com.lucas.customException.ValidacaoCPFException;
import br.com.lucas.entitys.Pessoa;
import br.com.lucas.repository.PessoaRepository;
import br.com.lucas.services.PessoaService;

@RestController
public class PessoaRest {

	@Autowired
	PessoaService pessoaService;

	@RequestMapping(value = "pessoa/{idPessoa}", method = RequestMethod.GET)
	public Optional<Pessoa> getPessoa(@PathVariable(value = "idPessoa") Integer idPessoa) {
		return pessoaService.findByIdPessoa(idPessoa);
	}

	@RequestMapping(value = "pessoa/buscaTodasPessoas", method = RequestMethod.GET)
	public Page<Pessoa> readPageable(@NotNull final Pageable pageable) {
		return pessoaService.buscarTodasPessoas(pageable);
	}

	@Transactional
	@RequestMapping(value = "pessoa/adicionaPessoa", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public Pessoa addPessoa(@Valid @RequestBody Pessoa pessoa) throws ValidacaoCPFException, DatesExpection, ListaVaziaException {
		return pessoaService.adicionarPessoa(pessoa);
	}

	@RequestMapping(value = "pessoa/deletaPessoa/{idPessoa}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deletaPessoa(@PathVariable(value = "idPessoa") Integer idPessoa) {
		pessoaService.deletarPessoa(idPessoa);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "pessoa/atualizar", method = RequestMethod.PUT)
	@Transactional
	public void atualizar(@Valid @RequestBody Pessoa pessoa, PessoaRepository repository) throws UsuarioNaoEncontradoException {
		pessoaService.atualizar(pessoa);
	}

}
