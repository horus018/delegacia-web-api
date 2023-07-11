package br.com.delegacia.dao;

import java.util.List;

import br.com.delegacia.dominio.BoletimFurtoVeiculo;
import br.com.delegacia.dominio.VeiculoFurtado;

public interface BoletimFurtoVeiculoDao {
	void cadastrarBoletim(BoletimFurtoVeiculo boletim);
	void alterarBoletim(BoletimFurtoVeiculo boletim);
	void removerBoletim(String identificador);
	List<BoletimFurtoVeiculo> consultarBoletim(String identificador, String localOcorrencia, String periodoOcorrencia);
	List<BoletimFurtoVeiculo> listarTodosBoletins();
	List<VeiculoFurtado> consultarVeiculo(String placa, String cor, String tipoVeiculo);
	List<VeiculoFurtado> listarTodosVeiculos();
}
