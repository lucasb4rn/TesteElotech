package br.com.lucas.eliotech;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import br.com.lucas.repository.PessoaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestaPessoaRepository {

	@Autowired
	private PessoaRepository pessoaRepository;

	private Validator validator;

	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void deveCarregarUmaPessoaAoBuscarPeloCPF() {
		Pessoa pessoaCPF = pessoaRepository.findByCpf("364.220.058-30");
		assertNotNull(pessoaCPF);
		assertEquals(pessoaCPF.getCpf(), "364.220.058-30");

	}

	@Test
	public void deveCarregarUmaPessoaAoBuscarPeloNome() {
		List<Pessoa> listaPessoas = pessoaRepository.findByNome("Lucas");
		assertNotNull(listaPessoas);
		assertEquals(listaPessoas.get(0).getNome(), "Lucas");

	}

	@Test
	public void deveRetornaUmaListaDePessoasPaginadaComDoisElementos() {
		Pageable paging = PageRequest.of(0, 2);
		Page<Pessoa> paginado = pessoaRepository.findAll(paging);
		assertEquals(2, paginado.getSize());
	}

	@Test
	public void deveAdicionarUmaPessoaEremovelaComSucesso() {
		List<Contato> list = new ArrayList();
		Contato contato = new Contato("Frederico", "16994157664", "lucas.b4rn@gmail.com");
		Pessoa pessoa = new Pessoa("Lucas", "128.013.580-88", new Date("30/03/1994"), list);
		pessoaRepository.save(pessoa);
		Optional<Pessoa> pessoaAdicionada = pessoaRepository.findById(999);
		assertNotNull(pessoaAdicionada);
		Pessoa pessoaEncontrada = pessoaRepository.findByCpf("128.013.580-88");
		pessoaRepository.deleteById(pessoaEncontrada.getId());
		Pessoa pessoaEncontradaAposDeletar = pessoaRepository.findByCpf("128.013.580-88");
		assertNull(pessoaEncontradaAposDeletar);

	}

	
	@Test
	public void devoRetornarErroPorNomeVazio() {
		List<Contato> list = new ArrayList();
		Contato contato = new Contato("Frederico", "16994157664", "lucas.b4rn@gmail.com");
		Pessoa pessoa = new Pessoa("", "601.145.630-80", new Date("30/03/1994"), list);
		Set<ConstraintViolation<Pessoa>> violations = validator.validate(pessoa);
		assertFalse(violations.isEmpty());

	}


}
