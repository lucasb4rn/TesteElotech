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

import br.com.lucas.customException.FormatoEmailIncorretoExpection;
import br.com.lucas.entitys.Contato;
import br.com.lucas.repository.PessoaRepository;
import br.com.lucas.services.ContatoService;
import javassist.NotFoundException;


@RestController
public class ContatoRest {
	

	@Autowired
	ContatoService contatoService;

	@RequestMapping(value = "contato/{idContato}", method = RequestMethod.GET)
	public Optional<Contato> buscaContatos(@PathVariable(value = "idContato") Integer idContato) {
		return contatoService.findById(idContato);
	}

	@RequestMapping(value = "contato/buscaTodosContatos", method = RequestMethod.GET)
	public Page<Contato> readPageable(@NotNull final Pageable pageable) {
		return contatoService.buscarTodosContatos(pageable);
	}

	@Transactional
	@RequestMapping(value = "contato/adicionaContato", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public Contato addContato(@Valid @RequestBody Contato contato) throws FormatoEmailIncorretoExpection {
		return contatoService.adicionaContato(contato);
	}

	@RequestMapping(value = "contato/deletaContato/{idContato}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deletaPessoa(@PathVariable(value = "idContato") Integer idContato) {
		contatoService.deletaContato(idContato);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "contato/atualizar", method = RequestMethod.PUT)
	@Transactional
	public void atualizar(@Valid @RequestBody Contato contato, PessoaRepository repository) throws NotFoundException {
		contatoService.atualizar(contato);
	}

	

}
