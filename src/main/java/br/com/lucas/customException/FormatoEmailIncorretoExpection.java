package br.com.lucas.customException;

public class FormatoEmailIncorretoExpection extends Exception {
	
	private static final long serialVersionUID = 1L;

	public FormatoEmailIncorretoExpection(String message) {
		super(message);
	}

}
