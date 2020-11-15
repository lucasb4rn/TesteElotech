package br.com.lucas.eliotech;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.lucas.customException.DatesExpection;
import br.com.lucas.customException.ListaVaziaException;
import br.com.lucas.customException.ValidacaoCPFException;
import br.com.lucas.entitys.Contato;
import br.com.lucas.entitys.Pessoa;
import br.com.lucas.services.PessoaService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestaPessoaService {

	@Autowired
	PessoaService pessoaService;

	@Test
	public void devoRetornarErroPorDataNascimentoMaiorQueAtual() throws ValidacaoCPFException, ListaVaziaException {
		List<Contato> list = new ArrayList();
		Contato contato = new Contato("Frederico", "16994157664", "lucas.b4rn@gmail.com");
		Pessoa pessoa = new Pessoa("Lucas", "601.145.630-80", new Date("30/03/2222"), list);
		try {
			pessoaService.adicionarPessoa(pessoa);
		} catch (DatesExpection e) {
			assertTrue(true);
		}

	}
	
	
	@Test
	public void devoRetornarErroPorCpfInvalido() throws DatesExpection, ListaVaziaException {
		List<Contato> list = new ArrayList();
		Contato contato = new Contato("Frederico", "16994157664", "lucas.b4rn@gmail.com");
		list.add(contato);
		Pessoa pessoa = new Pessoa("Lucas", "601.145.630-99", new Date("30/03/1994"), list);
		try {
			pessoaService.adicionarPessoa(pessoa);
			
		} catch (ValidacaoCPFException e) {
			assertTrue(true);
		}

	}
	
	
	@Test
	public void deveDarErroAoAdicionarUmaPessoaSemUmContato() throws ValidacaoCPFException, DatesExpection, ListaVaziaException {
		Pessoa pessoa = new Pessoa("Lucas", "128.013.580-88", new Date("30/03/1994"), new ArrayList());
		try {
			pessoaService.adicionarPessoa(pessoa);
		} catch (ListaVaziaException e) {
			assertTrue(true);
		}
	
	}

	

}
