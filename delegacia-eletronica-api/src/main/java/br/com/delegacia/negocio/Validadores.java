package br.com.delegacia.negocio;

import org.springframework.stereotype.Component;

import br.com.delegacia.dominio.BoletimFurtoVeiculo;

@Component
public interface Validadores {
	public boolean dadosObrigatorios(BoletimFurtoVeiculo boletim);
	public boolean emailValido(String email);
	public boolean dataValida(String data);
	public boolean placaValida(String placa);
	public boolean telefoneValido(String numeroTelefone);
}
