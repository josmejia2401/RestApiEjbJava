package co.com.x.common.rest.filter;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class CommonFilter extends Filter implements javax.ws.rs.container.ContainerRequestFilter {
	private static final Logger LOGGER = Logger.getLogger(CommonFilter.class);

	private static final String MSG_URI_INFO = "LLEGA LA PETICION";
	private static final String PARAM_ORIGIN  = "Origin";
	private static final String PARAM_OPTIONS  = "OPTIONS";
	
	@Context
	private ResourceInfo resourceInfo;

	public CommonFilter() {
		super();
	}

	@Override
	public void filter(ContainerRequestContext requestContext) {
		LOGGER.info(MSG_URI_INFO);
		LOGGER.info(requestContext.getUriInfo().getPath());
		LOGGER.info(requestContext.getMethod());
		if (isPreflightRequest(requestContext)) {
			requestContext.abortWith(Response.ok().build());
            return;
        }
	}
	private static boolean isPreflightRequest(ContainerRequestContext request) {
	  return request.getHeaderString(PARAM_ORIGIN) != null && request.getMethod().equalsIgnoreCase(PARAM_OPTIONS);
	}
}