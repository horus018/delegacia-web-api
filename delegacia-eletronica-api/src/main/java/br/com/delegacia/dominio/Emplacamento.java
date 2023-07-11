package br.com.delegacia.dominio;

public class Emplacamento {
	private String placa;
	private String estado;
	private String cidade;

	public Emplacamento() {

	}

	public Emplacamento(String placa, String estado, String cidade) {
		super();
		this.placa = placa;
		this.estado = estado;
		this.cidade = cidade;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

}
