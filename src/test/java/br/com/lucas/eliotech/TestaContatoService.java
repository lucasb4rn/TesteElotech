package br.com.lucas.eliotech;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.lucas.customException.DatesExpection;
import br.com.lucas.customException.FormatoEmailIncorretoExpection;
import br.com.lucas.customException.ValidacaoCPFException;
import br.com.lucas.entitys.Contato;
import br.com.lucas.entitys.Pessoa;
import br.com.lucas.services.ContatoService;
import br.com.lucas.services.PessoaService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestaContatoService {

	@Autowired
	ContatoService contatoService;

	@Test
	public void devoRetornarErroDeFormatoDeEmail() {
		Contato contato = new Contato("Frederico", "16994157664", "lucas.b4rn@gmailcom");
		try {
			contatoService.adicionaContato(contato);
		} catch (FormatoEmailIncorretoExpection e) {
			assertTrue(true);
		}

	}
	

}
