package br.com.delegacia.negocio;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import br.com.delegacia.dominio.BoletimFurtoVeiculo;

@Component
public class ValidadorBoletimVeiculo implements Validadores {

	@Override
	public boolean dadosObrigatorios(BoletimFurtoVeiculo boletim) {
		boolean dadosVazios = false;

		String[] dados = { boletim.getIdentificador(), boletim.getDataOcorrencia(), boletim.getPeriodoOcorrencia(),

				boletim.getVeiculoFurtado() != null ? (boletim.getVeiculoFurtado().getEmplacamento() != null
						? boletim.getVeiculoFurtado().getEmplacamento().getPlaca()
						: null) : null,
				boletim.getVeiculoFurtado() != null ? (boletim.getVeiculoFurtado().getEmplacamento() != null
						? boletim.getVeiculoFurtado().getEmplacamento().getEstado()
						: null) : null,
				boletim.getVeiculoFurtado() != null ? (boletim.getVeiculoFurtado().getEmplacamento() != null
						? boletim.getVeiculoFurtado().getEmplacamento().getCidade()
						: null) : null,

				boletim.getLocalOcorrencia() != null ? boletim.getLocalOcorrencia().getLogradouro() : null,
				boletim.getLocalOcorrencia() != null ? boletim.getLocalOcorrencia().getBairro() : null,
				boletim.getLocalOcorrencia() != null ? boletim.getLocalOcorrencia().getCidade() : null,
				boletim.getLocalOcorrencia() != null ? boletim.getLocalOcorrencia().getEstado() : null,
				boletim.getLocalOcorrencia() != null ? boletim.getLocalOcorrencia().getNumero() : null,

				boletim.getVeiculoFurtado() != null ? boletim.getVeiculoFurtado().getAnoFabricacao() : null,
				boletim.getVeiculoFurtado() != null ? boletim.getVeiculoFurtado().getCor() : null,
				boletim.getVeiculoFurtado() != null ? boletim.getVeiculoFurtado().getMarca() : null,
				boletim.getVeiculoFurtado() != null ? boletim.getVeiculoFurtado().getTipoVeiculo() : null,

				boletim.getParte() != null ? boletim.getParte().getNome() : null,
				boletim.getParte() != null ? boletim.getParte().getEmail() : null,
				boletim.getParte() != null ? boletim.getParte().getTelefone() : null };

		for (String dado : dados) {
			if (dado == null || dado.isEmpty()) {
				dadosVazios = true;
				break;
			}
		}
		return dadosVazios;
	}

	@Override
	public boolean emailValido(String email) {
		String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
		return Pattern.matches(emailRegex, email);
	}

	@Override
	public boolean dataValida(String data) {
		String dataRegex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$";
		return Pattern.matches(dataRegex, data);
	}

	@Override
	public boolean placaValida(String placa) {
		String placaRegex = "^[A-Za-z]{3}[-]?\\d{4}$";
		Pattern pattern = Pattern.compile(placaRegex);
		java.util.regex.Matcher matcher = pattern.matcher(placa);
		return matcher.matches();
	}

	@Override
	public boolean telefoneValido(String numeroTelefone) {
		String telefoneRegex = "^\\(\\d{2}\\)\\s?\\d{4,5}[-]?\\d{4}$";
		Pattern pattern = Pattern.compile(telefoneRegex);
		java.util.regex.Matcher matcher = pattern.matcher(numeroTelefone);
		return matcher.matches();
	}

}
