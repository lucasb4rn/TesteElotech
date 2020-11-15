package br.com.lucas.customException;

public class ListaVaziaException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ListaVaziaException(String message) {
		super(message);
	}


}
