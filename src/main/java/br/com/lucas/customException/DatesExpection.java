package br.com.lucas.customException;

public class DatesExpection extends Exception {

	private static final long serialVersionUID = 1L;

	public DatesExpection(String message) {
		super(message);
	}

}
