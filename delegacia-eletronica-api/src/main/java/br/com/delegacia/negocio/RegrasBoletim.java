package br.com.delegacia.negocio;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.delegacia.dominio.BoletimFurtoVeiculo;
import br.com.delegacia.dominio.VeiculoFurtado;

@Component
public interface RegrasBoletim {
	void cadastrar(BoletimFurtoVeiculo boletim) throws IOException;

	List<BoletimFurtoVeiculo> listarTodosBoletins() throws IOException;
	List<BoletimFurtoVeiculo> consultarBoletim(String identificador, String cidade, String periodoOcorrencia) throws IOException;
	public void alterar(BoletimFurtoVeiculo boletim);
	public void remover(String identificador);

	List<VeiculoFurtado> listarTodosVeiculos() throws IOException;
	List<VeiculoFurtado> consultarVeiculo(String placa, String cor, String tipoVeiculo) throws IOException;
}
