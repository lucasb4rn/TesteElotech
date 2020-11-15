package br.com.lucas.eliotech;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.lucas.entitys.Contato;
import br.com.lucas.entitys.Pessoa;
import br.com.lucas.repository.ContatoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestaContatoRepository {

	private Validator validator;
	
	@Autowired
	ContatoRepository contatoRepository;
	
	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	
	@Test
	public void deveCarregarUmaContatoAoBuscarPeloId() {
		Optional<Contato> contatoNumeroUm = contatoRepository.findById(1);
		Contato contato = contatoNumeroUm.get();
		assertNotNull(contato);
		assertEquals(contato.getNome(), "Pedro");

	}
	
	@Test
	public void deveValidarContatoSemDarErro() {
		Contato contato = new Contato("lucas", "16994157664", "lucas.b4rn@gmail.com");
		Set<ConstraintViolation<Contato>> violations = validator.validate(contato);
		assertTrue(violations.isEmpty());

	}
	
	@Test
	public void deveRetornaUmaListaDeContatosPaginadaComDoisElementos() {
		Pageable paging = PageRequest.of(0, 2);
		Page<Contato> paginado = contatoRepository.findAll(paging);
		assertEquals(2, paginado.getSize());
	}

	
	
	
	@Test
	public void deveValidarCampoNomeVazio() {
		Contato contato = new Contato("", "16994157664", "lucas.b4rn@gmail.com");
		Set<ConstraintViolation<Contato>> violations = validator.validate(contato);
		assertFalse(violations.isEmpty());

	}
	
	@Test
	public void deveValidarCampoTelefoneVazio() {
		Contato contato = new Contato("Lucas", "", "lucas.b4rn@gmail.com");
		Set<ConstraintViolation<Contato>> violations = validator.validate(contato);
		assertFalse(violations.isEmpty());

	}
	
	@Test
	public void deveValidarCampoEmailVazio() {
		Contato contato = new Contato("Lucas", "16994157664", "");
		Set<ConstraintViolation<Contato>> violations = validator.validate(contato);
		assertFalse(violations.isEmpty());

	}
	
	
	
	
}
