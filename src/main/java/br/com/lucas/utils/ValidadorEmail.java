package br.com.lucas.utils;

public class ValidadorEmail {
	
	public boolean valida(String email) {
		return email.matches("^([\\w\\.\\-]+)@([\\w\\-]+)((\\.(\\w){2,3})+)$");
	}
	

}
