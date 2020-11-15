package br.com.lucas.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.lucas.customException.ValidacaoCPFException;
import br.com.lucas.customException.DatesExpection;
import br.com.lucas.customException.FormatoEmailIncorretoExpection;
import br.com.lucas.customException.ListaVaziaException;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<Erro> handle(MethodArgumentNotValidException exception) {
		List<Erro> dto = new ArrayList<>();

		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			Erro erro = new Erro(e.getField(), mensagem);
			dto.add(erro);
		});

		return dto;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(FormatoEmailIncorretoExpection.class)
	public Erro handle(FormatoEmailIncorretoExpection exception) {
		Erro erro = new Erro("email", exception.getMessage());
		return erro;

	}
	
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ValidacaoCPFException.class)
	public Erro handle(ValidacaoCPFException exception) {
		Erro erro = new Erro("cpf", exception.getMessage());
		return erro;

	}
	
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DatesExpection.class)
	public Erro handle(DatesExpection exception) {
		Erro erro = new Erro("dataNascimento", exception.getMessage());
		return erro;

	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ListaVaziaException.class)
	public Erro handle(ListaVaziaException exception) {
		Erro erro = new Erro("listaContatos", exception.getMessage());
		return erro;

	}


	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public List<Erro> handleResourceNotFoundException(ConstraintViolationException e) {
		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
		
		List<Erro> errors = new ArrayList<>();
		
		for (ConstraintViolation<?> violation : violations) {
			Erro erro = new Erro(violation.getPropertyPath() + "", violation.getMessage());
			errors.add(erro);
		}

		return errors;
	}

}
