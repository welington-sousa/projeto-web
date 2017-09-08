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

import br.com.ws.projetoweb.business.EstadoBusiness;
import br.com.ws.projetoweb.exception.NenhumRegistroEncontradoException;
import br.com.ws.projetoweb.exception.ValidacaoException;
import br.com.ws.projetoweb.model.Estado;

@Path("estado")
public class EstadoRS {

	@Inject
	private EstadoBusiness estadoBusiness;

	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public List<Estado> selecionarTodos() {
		return estadoBusiness.selecionarTodos();
	}

	@GET
	@Path("{id}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Estado selecionarEstadoPorId(@PathParam("id") Long id) {
		try {
			return estadoBusiness.selecionarPorId(id);
		} catch (NenhumRegistroEncontradoException e) {
			throw new NotFoundException(e.getMessage());
		}
	}

	@POST
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response adicionarEstado(Estado estado) {

		Long id;
		try {
			id = estadoBusiness.adicionarEstado(estado);
			return Response.status(Status.CREATED).entity(id).build();
		} catch (ValidacaoException e) {
			return tratarValidacaoException(e);
		}
	}

	@PUT
	@Path("{id}")
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response atualizarEstado(@PathParam("id") Long id, Estado estado) {
		try {

			estado.setId(id);

			estadoBusiness.atualizarEstado(estado);

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
	public Response excluirEstado(@PathParam("id") Long id) {
		try {
			Estado estado = estadoBusiness.excluirEstado(id);
			return Response.status(Status.OK).entity(estado).build();
		} catch (NenhumRegistroEncontradoException e) {
			throw new NotFoundException(e.getMessage());
		}
	}

	private Response tratarValidacaoException(ValidacaoException e) {
		return Response.status(Status.NOT_ACCEPTABLE).entity(e.getErros()).build();
	}
}
