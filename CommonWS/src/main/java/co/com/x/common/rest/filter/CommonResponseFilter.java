package co.com.x.common.rest.filter;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

@Provider
public class CommonResponseFilter extends Filter implements javax.ws.rs.container.ContainerResponseFilter {

	private static final String PARAM_ORIGIN  = "Origin";
	private static final String PARAM_OPTIONS  = "OPTIONS";
	private static final String PARAM_MAX_AGE  = "7200";
	private static final String PARAM_ORACLE_RID  = "x-oracle-dms-rid";
	private static final String PARAM_ORACLE_CID  = "x-oracle-dms-ecid";
	private static final String PARAM_X_POWER  = "X-Powered-By";
	private static final String PARAM_X_POWER_WITH  = "X-Requested-With";
	private static final String PARAM_SERVER  = "Server";
	private static final String PARAM_ALLOW_ORIGIN  = "Access-Control-Allow-Origin";
	private static final String PARAM_ALLOW_HEADER  = "Access-Control-Allow-Headers";
	private static final String PARAM_ALLOW_MAX_AGE  = "Access-Control-Max-Age";
	private static final String PARAM_ALLOW_METHODS  = "Access-Control-Allow-Methods";
	private static final String PARAM_XSS  = "X-XSS-Protection";
	private static final String PARAM_ALLOW_ORIGIN_VALUE  = "*";
	private static final String PARAM_ALLOW_HEADER_VALUE  = "Content-Type,Origin,X-XSS-Protection,Access-Control-Allow-Origin,Access-Control-Max-Age,Access-Control-Allow-Methods,Access-Control-Allow-Headers";
	private static final String PARAM_ALLOW_METHODS_VALUE  = "GET,POST,OPTIONS";
	private static final String PARAM_XSS_VALUE  = "1; mode=block";
	
	@Context
	private ResourceInfo resourceInfo;

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		if (requestContext.getHeaderString(PARAM_ORIGIN) == null) {
            return;
        }
		final String ACCESS_CONTROL_MAX_AGE_IN_SECONDS = PARAM_MAX_AGE;
		agregarCabeceras(requestContext, responseContext, ACCESS_CONTROL_MAX_AGE_IN_SECONDS);
		eliminarCabeceras(requestContext, responseContext);
	}

	private void eliminarCabeceras(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
		String xoracledmsrid = 	responseContext.getHeaders().keySet().stream().filter(i -> i.equalsIgnoreCase(PARAM_ORACLE_RID)).findAny().orElse(PARAM_ORACLE_RID);
		String xoracledmsecid = responseContext.getHeaders().keySet().stream().filter(i -> i.equalsIgnoreCase(PARAM_ORACLE_CID)).findAny().orElse(PARAM_ORACLE_CID);
		String xPoweredBy = responseContext.getHeaders().keySet().stream().filter(i -> i.equalsIgnoreCase(PARAM_X_POWER)).findAny().orElse(PARAM_X_POWER);
		responseContext.getHeaders().remove(xoracledmsrid);
		responseContext.getHeaders().remove(xoracledmsecid);
		responseContext.getHeaders().remove(xPoweredBy);
		responseContext.getHeaders().remove(PARAM_SERVER);
		requestContext.getHeaders().remove(xoracledmsrid);
		requestContext.getHeaders().remove(xoracledmsecid);
		requestContext.getHeaders().remove(xPoweredBy);
		requestContext.getHeaders().remove(PARAM_SERVER);
		requestContext.getHeaders().remove(PARAM_X_POWER_WITH);
	}
	private void agregarCabeceras(ContainerRequestContext requestContext,ContainerResponseContext responseContext,final String ACCESS_CONTROL_MAX_AGE_IN_SECONDS) {
		if (isPreflightRequest(requestContext)) {
			responseContext.getHeaders().putSingle(PARAM_ALLOW_ORIGIN, PARAM_ALLOW_ORIGIN_VALUE);
			responseContext.getHeaders().putSingle(PARAM_ALLOW_HEADER, PARAM_ALLOW_HEADER_VALUE);
			responseContext.getHeaders().putSingle(PARAM_ALLOW_MAX_AGE, ACCESS_CONTROL_MAX_AGE_IN_SECONDS);
			responseContext.getHeaders().putSingle(PARAM_XSS, PARAM_XSS_VALUE);
			responseContext.getHeaders().putSingle(PARAM_ALLOW_METHODS, PARAM_ALLOW_METHODS_VALUE);
        } else {
        	responseContext.getHeaders().putSingle(PARAM_ALLOW_ORIGIN, PARAM_ALLOW_ORIGIN_VALUE);
        }
	}	
	private static boolean isPreflightRequest(ContainerRequestContext request) {
        return request.getHeaderString(PARAM_ORIGIN) != null && request.getMethod().equalsIgnoreCase(PARAM_OPTIONS);
    }

}