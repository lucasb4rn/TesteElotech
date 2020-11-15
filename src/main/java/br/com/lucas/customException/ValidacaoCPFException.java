package br.com.lucas.customException;



public class ValidacaoCPFException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValidacaoCPFException(String message) {
		super(message);
	}


}
