package br.com.ws.projetoweb.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.ws.projetoweb.business.VeiculoBusiness;
import br.com.ws.projetoweb.exception.NenhumRegistroEncontradoException;
import br.com.ws.projetoweb.exception.ValidacaoException;
import br.com.ws.projetoweb.model.Veiculo;

@Path("veiculo")
public class VeiculoRS {

	@Inject
	private VeiculoBusiness veiculoBusiness;

	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public List<Veiculo> selecionarTodos() {
		return veiculoBusiness.selecionaTodos();
	}

	@GET
	@Path("{id}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Veiculo selecionarVeiculoPorId(@PathParam("id") Long id) {
		try {
			return veiculoBusiness.selecionaPorId(id);
		} catch (NenhumRegistroEncontradoException e) {
			throw new NotFoundException(e.getMessage());
		}
	}

	@POST
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response adicionarVeiculo(Veiculo veiculo) {

		Long id;
		try {
			id = veiculoBusiness.adicionaVeiculo(veiculo);
			return Response.status(Status.CREATED).entity(id).build();
		} catch (ValidacaoException e) {
			return tratarValidacaoException(e);
		}
	}

	@PUT
	@Path("{id}")
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response atualizarVeiculo(@PathParam("id") Long id, Veiculo veiculo) {
		try {

			veiculo.setId(id);

			veiculoBusiness.atualizaVeiculo(veiculo);

			return Response.status(Status.OK).entity(id).build();
		} catch (NenhumRegistroEncontradoException e) {
			throw new NotFoundException(e.getMessage());
		} catch (ValidacaoException e) {
			return tratarValidacaoException(e);
		}
	}

	@DELETE
	@Path("{id}")
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response excluirVeiculo(@PathParam("id") Long id) {
		try {
			Veiculo veiculo = veiculoBusiness.excluiVeiculo(id);
			return Response.status(Status.OK).entity(veiculo).build();
		} catch (NenhumRegistroEncontradoException e) {
			throw new NotFoundException(e.getMessage());
		}
	}

	private Response tratarValidacaoException(ValidacaoException e) {
		return Response.status(Status.NOT_ACCEPTABLE).entity(e.getErros()).build();
	}
}
