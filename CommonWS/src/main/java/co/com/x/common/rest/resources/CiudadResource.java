package co.com.x.common.rest.resources;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;

import co.com.x.common.rest.delegate.CiudadDelegate;
import co.com.x.common.service.dto.CiudadDTO;
import co.com.x.common.service.exception.CommonException;

@Path("/ciudad-ws")
public class CiudadResource extends ResourceConfig {
	
	private static final String CHARSET = ";charset=utf-8";
	private static final String PATH_CONSULTAR = "/consultar";
	
	@EJB(beanName = "CiudadDelegate")
	CiudadDelegate ciudadDelegate;
	
	@GET
	@Path(PATH_CONSULTAR)
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	public Response get() {
		try {
			List<CiudadDTO> resultado = ciudadDelegate.getCiudades();
			GenericEntity<List<CiudadDTO>> resultadoFinal = new GenericEntity<List<CiudadDTO>>(resultado) { };
			return Response.ok(resultadoFinal).build();
		} catch (CommonException e) {
			return e.responseError();
		} catch (Exception e) {
			return new CommonException(e).responseError();
		}
	}
	/*
	@POST
	@Path("/crear")
	@RolesAllowed({Roles.ROL_CREAR})
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response crear(CiudadDTO param, @Context HttpHeaders httpheaders) {
		try {
			CiudadDTO resultado = ciudadDelegate.guardar(param);
			if (ValidatorForm.isEmpty(resultado) || ValidatorForm.isEmpty(resultado.getCodigo())) {
				throw new Exception("No se pudo crear la ciudad.");
			} else {
				return Response.ok().entity("OK").build();
			}
		} catch (CommonException e) {
			return ciudadDelegate.responseError(e);
		} catch (Exception e) {
			return ciudadDelegate.responseError(e);
		}
	}
	
	
	@PUT
	@Path("/modificar/{id}")
	@RolesAllowed({Roles.ROL_MODIFICAR})
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response modificar(@PathParam("id") BigDecimal id, CiudadDTO param, @Context HttpHeaders httpheaders) {
		try {
			CiudadDTO resultado = ciudadDelegate.modificar(id, param);
			if (ValidatorForm.isEmpty(resultado) || ValidatorForm.isEmpty(resultado.getCodigo())) {
				throw new Exception("No se pudo modificar la ciudad.");
			} else {
				return Response.ok().entity("OK").build();
			}
		} catch (CommonException e) {
			return ciudadDelegate.responseError(e);
		} catch (Exception e) {
			return ciudadDelegate.responseError(e);
		}
	}
	
	
	@DELETE
	@Path("/eliminar/{id}")
	@RolesAllowed({Roles.ROL_ELIMINAR})
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminar(@PathParam("id") BigDecimal id, @Context HttpHeaders httpheaders) {
		try {
			ciudadDelegate.eliminar(id);
			return Response.
				   status(javax.ws.rs.core.Response.Status.OK).
				   entity("OK").
				   build();
		} catch (CommonException e) {
			return ciudadDelegate.responseError(e);
		} catch (Exception e) {
			return ciudadDelegate.responseError(e);
		}
	}*/
}