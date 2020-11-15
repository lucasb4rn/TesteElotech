package br.com.lucas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.lucas.customException.ValidacaoCPFException;
import br.com.lucas.customException.FormatoEmailIncorretoExpection;
import br.com.lucas.entitys.Contato;
import br.com.lucas.repository.ContatoRepository;
import br.com.lucas.utils.ValidadorEmail;
import javassist.NotFoundException;


@Service
public class ContatoService {

	@Autowired
	ContatoRepository contatoRepository;
	
	
	public Contato salvar(Contato contato) {
		return contatoRepository.save(contato);
	}

	@Transactional
	public void deletaContato(Integer idContato) {
		contatoRepository.deleteById(idContato);
	}

	public Contato findByName(String nome) {
		return contatoRepository.findByNome(nome);
	}

	public Optional<Contato> findById(Integer id) {
		return contatoRepository.findById(id);
	}

	public Page<Contato> buscarTodosContatos(Pageable pageable) {
		return contatoRepository.findAll(pageable);
	}

	public Contato adicionaContato(Contato Contato) throws FormatoEmailIncorretoExpection {
		
		ValidadorEmail validadorEmail = new ValidadorEmail();
		boolean emailValidado = validadorEmail.valida(Contato.getEmail());
		if(emailValidado == false)  throw new FormatoEmailIncorretoExpection("Formato do Email está inválido!");
		
		Integer idContato = contatoRepository.findFirstByOrderByIdDesc().getId();
		Contato.setId(++idContato);
		return contatoRepository.save(Contato);
	}

	public Contato atualizar(Contato contato) throws NotFoundException {
		Optional<Contato> ContatoEncontradaNoBanco = contatoRepository.findById(contato.getId());
		if (ContatoEncontradaNoBanco == null)
			throw new NotFoundException("Não foi encontrado contato com o id informado");
		return contatoRepository.save(contato);

	}

}
