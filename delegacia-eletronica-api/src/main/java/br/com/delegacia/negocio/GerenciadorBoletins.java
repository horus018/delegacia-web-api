package br.com.delegacia.negocio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.delegacia.dao.BoletimFurtoVeiculoDao;
import br.com.delegacia.dominio.BoletimFurtoVeiculo;
import br.com.delegacia.dominio.VeiculoFurtado;

@Component
public class GerenciadorBoletins implements RegrasBoletim {

	@Autowired
	Validadores validadores;

	@Autowired
	BoletimFurtoVeiculoDao boletimDao;

	@Override
	public void cadastrar(BoletimFurtoVeiculo boletim) throws IOException {

		if (validadores.dadosObrigatorios(boletim)) {
			System.out.println("Todos os valores são obrigatórios.");
			String msgErro = "Todos os valores são obrigatórios.";
			throw new DadosObrigatoriosException(msgErro);

		} else if (!validadores.emailValido(boletim.getParte().getEmail())) {
			System.out.println("E-mail com formato inválido.");
			String msgErro = "E-mail com formato inválido.";
			throw new EmailFormatoInvalidoException(msgErro);

		} else if (!validadores.placaValida(boletim.getVeiculoFurtado().getEmplacamento().getPlaca())) {
			System.out.println("Placa com formato inválido");
			String msgErro = "Placa com formato inválido";
			throw new PlacaFormatoInvalidaException(msgErro);

		} else if (!validadores.dataValida(boletim.getDataOcorrencia())) {
			System.out.println("Data com formato inválida.");
			String msgErro = "Data com formato inválida.";
			throw new DataFormatoInvalidoException(msgErro);

		} else if (!validadores.telefoneValido(boletim.getParte().getTelefone())) {
			System.out.println("Número de telefone com formato inválido.");
			String msgErro = "Número de telefone com formato inválido.";
			throw new TelefoneFormatoInvalidoException(msgErro);

		} else {
			boletimDao.cadastrarBoletim(boletim);
		}
	}

	@Override
	public List<BoletimFurtoVeiculo> listarTodosBoletins() throws IOException {

		ArrayList<BoletimFurtoVeiculo> boletinsCopia = new ArrayList<>();
		boletinsCopia.addAll(boletimDao.listarTodosBoletins());

		for (BoletimFurtoVeiculo boletim : boletinsCopia) {
			boletim.setParte(null);
		}

		if (boletinsCopia.isEmpty()) {
			throw new IOException("Boletim não encontrado");
		} else {
			return boletinsCopia;
		}

	}

	@Override
	public List<BoletimFurtoVeiculo> consultarBoletim(String identificador, String cidade, String periodoOcorrencia)
			throws IOException {

		ArrayList<BoletimFurtoVeiculo> boletinsCopia = new ArrayList<>();
		boletinsCopia.addAll(boletimDao.consultarBoletim(identificador, cidade, periodoOcorrencia));

		for (BoletimFurtoVeiculo boletim : boletinsCopia) {
			boletim.setParte(null);
		}
		
		if (boletinsCopia.isEmpty()) {
			throw new IOException("Boletim não encontrado");
		} else {
			return boletinsCopia;
		}
	}

	@Override
	public void alterar(BoletimFurtoVeiculo boletim) {
		boletimDao.alterarBoletim(boletim);
	}

	@Override
	public void remover(String identificador) {
		boletimDao.removerBoletim(identificador);
	}

	@Override
	public List<VeiculoFurtado> listarTodosVeiculos() throws IOException {

		if (boletimDao.listarTodosVeiculos().isEmpty()) {
			throw new IOException("Boletim não encontrado");
		} else {
			return boletimDao.listarTodosVeiculos();
		}

	}

	@Override
	public List<VeiculoFurtado> consultarVeiculo(String placa, String cor, String tipoVeiculo)
			throws IOException {

		if (boletimDao.consultarVeiculo(placa, cor, tipoVeiculo).isEmpty()) {
			throw new IOException("Veiculo não encontrado");
		} else {
			return boletimDao.consultarVeiculo(placa, cor, tipoVeiculo);
		}

	}

}
