package co.com.x.common.rest.startup;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class ConfigStartup implements ServletContextListener {
	
	private static Logger LOGGER_ERROR = Logger.getLogger(ConfigStartup.class);
	
	private static final String MSG_ERROR_DESCONOCIDO = "Se ha ganerado un error desconocido al procesar la solicitud.";
	private static final String PARAM_LOG4J = "log4j.properties";
	
	public ConfigStartup() {
		super();
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		this.load();
	}

	protected void load() {
		configureLog4jLocal();
	}
	
	private void configureLog4jLocal() {
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			PropertyConfigurator.configure(loader.getResource(PARAM_LOG4J));
		} catch (Exception e) {
			LOGGER_ERROR.error(MSG_ERROR_DESCONOCIDO, e);
		}
	}
}