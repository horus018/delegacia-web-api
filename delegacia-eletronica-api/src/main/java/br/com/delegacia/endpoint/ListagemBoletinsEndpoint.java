package br.com.delegacia.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.delegacia.dominio.BoletimFurtoVeiculo;
import br.com.delegacia.negocio.RegrasBoletim;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Component
@Path("boletins")
public class ListagemBoletinsEndpoint {

	@Autowired
	private RegrasBoletim regrasBoletim;

	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response get(@QueryParam("identificador") String identificador, @QueryParam("cidade") String cidade,
			@QueryParam("periodoOcorrencia") String periodoOcorrencia) {

		if (identificador == null && cidade == null && periodoOcorrencia == null) {
			try {
				return Response.ok(regrasBoletim.listarTodosBoletins()).build();
			} catch (Exception e) {
				return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
			}

		} else {
			try {
				return Response.ok(regrasBoletim.consultarBoletim(identificador, cidade, periodoOcorrencia)).build();
			} catch (Exception e) {
				return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
			}
		}

	}

	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public Response post(BoletimFurtoVeiculo boletim) {
		try {
			regrasBoletim.cadastrar(boletim);
			return Response.ok("Boletim cadastrado").build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	public Response put(BoletimFurtoVeiculo boletim) {
		regrasBoletim.alterar(boletim);
		return Response.ok("Boletim atualizado").build();
	}

	@DELETE
	public Response delete(@QueryParam("identificador") String identificador) {
		regrasBoletim.remover(identificador);
		return Response.ok("Boletim Deletetado").build();
	}

}
