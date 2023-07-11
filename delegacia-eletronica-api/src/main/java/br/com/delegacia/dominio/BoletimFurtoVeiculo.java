package br.com.delegacia.dominio;

public class BoletimFurtoVeiculo {

	private String identificador;
	private String dataOcorrencia;
	private String periodoOcorrencia;
	private Parte parte;
	private LocalOcorrencia localOcorrencia;
	private VeiculoFurtado veiculoFurtado;

	public BoletimFurtoVeiculo() {
	}

	public BoletimFurtoVeiculo(String identificador, String dataOcorrencia, String periodoOcorrencia, Parte parte,
			LocalOcorrencia localOcorrencia, VeiculoFurtado veiculoFurtado) {
		this.identificador = identificador;
		this.dataOcorrencia = dataOcorrencia;
		this.periodoOcorrencia = periodoOcorrencia;
		this.parte = parte;
		this.localOcorrencia = localOcorrencia;
		this.veiculoFurtado = veiculoFurtado;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getDataOcorrencia() {
		return dataOcorrencia;
	}

	public void setDataOcorrencia(String dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}

	public String getPeriodoOcorrencia() {
		return periodoOcorrencia;
	}

	public void setPeriodoOcorrencia(String periodoOcorrencia) {
		this.periodoOcorrencia = periodoOcorrencia;
	}

	public Parte getParte() {
		return parte;
	}

	public void setParte(Parte parte) {
		this.parte = parte;
	}

	public LocalOcorrencia getLocalOcorrencia() {
		return localOcorrencia;
	}

	public void setLocalOcorrencia(LocalOcorrencia localOcorrencia) {
		this.localOcorrencia = localOcorrencia;
	}

	public VeiculoFurtado getVeiculoFurtado() {
		return veiculoFurtado;
	}

	public void setVeiculoFurtado(VeiculoFurtado veiculoFurtado) {
		this.veiculoFurtado = veiculoFurtado;
	}
}
