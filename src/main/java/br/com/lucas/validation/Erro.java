package br.com.lucas.validation;

public class Erro {
	
	private String campo;
	private String erro;
	
	public Erro(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}
	
	

}
