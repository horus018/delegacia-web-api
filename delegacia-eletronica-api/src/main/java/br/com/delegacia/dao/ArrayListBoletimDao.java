package br.com.delegacia.dao;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import br.com.delegacia.dominio.BoletimFurtoVeiculo;
import br.com.delegacia.dominio.Emplacamento;
import br.com.delegacia.dominio.LocalOcorrencia;
import br.com.delegacia.dominio.VeiculoFurtado;
import jakarta.annotation.PostConstruct;

@Component
public class ArrayListBoletimDao implements BoletimFurtoVeiculoDao {

	ArrayList<BoletimFurtoVeiculo> listaBoletins = new ArrayList<>();
	ArrayList<VeiculoFurtado> listaVeiculos = new ArrayList<>();
	private ArrayList<BoletimFurtoVeiculo> listaBoletinsCsv = new ArrayList<>();
	int posicao;
	

	@PostConstruct
	public void init() {
		inserirCsvListaBoletins();
	}

	@Override
	public void cadastrarBoletim(BoletimFurtoVeiculo boletim) {
		listaBoletins.add(boletim);
	}

	@Override
	public void alterarBoletim(BoletimFurtoVeiculo boletim) {
		for (BoletimFurtoVeiculo boletimFurtoVeiculo : listaBoletins) {
			if (boletimFurtoVeiculo.getIdentificador().equals(boletim.getIdentificador())) {
				posicao = listaBoletins.indexOf(boletimFurtoVeiculo);
				listaBoletins.set(posicao, boletim);
			}
		}
	}

	@Override
	public void removerBoletim(String identificador) {
		ArrayList<BoletimFurtoVeiculo> deletarBoletins = new ArrayList<>();

		if (identificador != null) {
			for (BoletimFurtoVeiculo boletim : listaBoletins) {
				if (boletim.getIdentificador().equals(identificador)) {
					deletarBoletins.add(boletim);
				}
			}

			listaBoletins.removeAll(deletarBoletins);
		}
	}

	@Override
	public List<BoletimFurtoVeiculo> consultarBoletim(String identificador, String cidade, String periodoOcorrencia) {
	    ArrayList<BoletimFurtoVeiculo> boletinsFiltrados = new ArrayList<>();

	    for (BoletimFurtoVeiculo boletim : listaBoletins) {
	        if ((identificador == null || identificador.isEmpty() || boletim.getIdentificador().equalsIgnoreCase(identificador))
	                && (cidade == null || cidade.isEmpty() || boletim.getLocalOcorrencia().getCidade().equalsIgnoreCase(cidade))
	                && (periodoOcorrencia == null || periodoOcorrencia.isEmpty() || boletim.getPeriodoOcorrencia().equalsIgnoreCase(periodoOcorrencia))) {
	            boletinsFiltrados.add(boletim);
	        }
	    }
	    
	    return boletinsFiltrados;
	}

	@Override
	public List<BoletimFurtoVeiculo> listarTodosBoletins() {
		return listaBoletins;
	}

	@Override
	public List<VeiculoFurtado> consultarVeiculo(String placa, String cor, String tipoVeiculo) {
		ArrayList<VeiculoFurtado> veiculosFiltrados = new ArrayList<>();

		 for (BoletimFurtoVeiculo boletim : listaBoletins) {
		        if ((placa == null || placa.isEmpty() || boletim.getVeiculoFurtado().getEmplacamento().getPlaca().equalsIgnoreCase(placa))
		                && (cor == null || cor.isEmpty() || boletim.getVeiculoFurtado().getCor().equalsIgnoreCase(cor))
		                && (tipoVeiculo == null || tipoVeiculo.isEmpty() || boletim.getVeiculoFurtado().getTipoVeiculo().equalsIgnoreCase(tipoVeiculo))) {
		            veiculosFiltrados.add(boletim.getVeiculoFurtado());
		        }
		    }

		return veiculosFiltrados;
	}

	@Override
	public List<VeiculoFurtado> listarTodosVeiculos() {
		ArrayList<VeiculoFurtado> veiculos = new ArrayList<>();
		
		for (BoletimFurtoVeiculo boletim : listaBoletins) {
			veiculos.add(boletim.getVeiculoFurtado());
		}
		
		return veiculos;
	}

	public ArrayList<BoletimFurtoVeiculo> getListaBoletinsCsv() {
		return listaBoletinsCsv;
	}

	public void inserirCsvListaBoletins() {
	    try {
	        Reader leitorArquivo = new FileReader("furtos.csv");
	        @SuppressWarnings("deprecation")
			CSVFormat configCSV = CSVFormat.Builder.create().setDelimiter('\t').setSkipHeaderRecord(true).build().withFirstRecordAsHeader();
	        try (CSVParser interpretadorCSV = new CSVParser(leitorArquivo, configCSV)) {
				for (CSVRecord record : interpretadorCSV) {
					Emplacamento emplacamento = new Emplacamento(record.get(44), record.get(45), record.get(46));
					VeiculoFurtado veiculo = new VeiculoFurtado(record.get(49), record.get(47), record.get(48), record.get(51), emplacamento);        	
				    LocalOcorrencia local = new LocalOcorrencia(record.get(13), record.get(14), record.get(15), record.get(16), record.get(17));
				    BoletimFurtoVeiculo boletim = new BoletimFurtoVeiculo(record.get(2), record.get(5), record.get(7), null, local, veiculo);
				    listaBoletins.add(boletim);
				}
			}

	    } catch (IOException e) {
	        e.printStackTrace();
	        System.out.println("Erro ao ler o arquivo csv");
	    }
	}

}
