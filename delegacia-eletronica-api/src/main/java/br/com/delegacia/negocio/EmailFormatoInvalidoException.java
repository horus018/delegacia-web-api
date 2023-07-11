package br.com.delegacia.negocio;

public class EmailFormatoInvalidoException extends RuntimeException{
	public EmailFormatoInvalidoException(String msg) {
		super(msg);
	}
}
