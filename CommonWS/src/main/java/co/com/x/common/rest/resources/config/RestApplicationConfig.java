package co.com.x.common.rest.resources.config;

import org.glassfish.jersey.server.ResourceConfig;

import co.com.x.common.rest.filter.CommonFilter;
import co.com.x.common.rest.filter.CommonResponseFilter;

public class RestApplicationConfig extends ResourceConfig {
	
	private static final String PARAM_FILTER = "co.com.x.common.rest.filter";
	
	public RestApplicationConfig() {
        packages(PARAM_FILTER);
		register( CommonFilter.class );
		register( CommonResponseFilter.class );
	}
}
