package br.com.delegacia.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.delegacia.negocio.RegrasBoletim;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Component
@Path("veiculos")
public class ListagemVeiculosEndpoint {

	@Autowired
	private RegrasBoletim regrasBoletim;

	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response get(@QueryParam("placa") String placa, @QueryParam("cor") String cor,
			@QueryParam("tipoVeiculo") String tipoVeiculo) {

		if (placa == null && cor == null && tipoVeiculo == null) {

			try {
				return Response.ok(regrasBoletim.listarTodosVeiculos()).build();
			} catch (Exception e) {
				return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
			}

		} else {

			try {
				return Response.ok(regrasBoletim.consultarVeiculo(placa, cor, tipoVeiculo)).build();
			} catch (Exception e) {
				return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
			}

		}
	}
}
