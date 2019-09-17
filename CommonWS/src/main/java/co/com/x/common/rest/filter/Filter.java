package co.com.x.common.rest.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;


public class Filter {
	private static final Logger LOGGER_ERROR = Logger.getLogger(Filter.class);
	private static final String MSG_ABORT = "SE ABORTA LA OPERACION";
	private static final String MSG_NO_ACCESO = "No tienes acceso a este recurso.";
	
	protected Filter() {}

	protected void abortWithUnauthorized(ContainerRequestContext requestContext, Exception e) {
		LOGGER_ERROR.error(MSG_ABORT, e);
		StringBuffer sb = new StringBuffer();
		sb.append(e.getMessage()).append(".").append(MSG_NO_ACCESO);
		requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(sb.toString()).build());
	}
}
