package co.com.x.common.rest.delegate;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import co.com.x.common.service.ciudad.facade.ICiudadFacade;
import co.com.x.common.service.dto.CiudadDTO;
import co.com.x.common.service.exception.CommonException;


@Stateless
public class CiudadDelegate{
	private static final Logger LOGGER_ERROR = Logger.getLogger(CiudadDelegate.class);
	
	private static final String MSG_INGRESAR_PARAMETROS = "Ingreso los parametros obligatorios.";
	
	@EJB(beanName = "CiudadFacadeImpl")
	ICiudadFacade iCiudadFacade;
	
	public List<CiudadDTO> getCiudades() throws CommonException {
		try{
			return iCiudadFacade.getCiudades();
		} catch (CommonException e) {
			LOGGER_ERROR.error(e);
			throw e;
		} catch (Exception e) {
			LOGGER_ERROR.error(e);
			throw new CommonException(e);
		}
	}
	
	public CiudadDTO getCiudad(BigDecimal id) throws CommonException {
		try{
			/*if (ValidatorForm.isEmpty(id)) {
				throw new CommonException(MSG_INGRESAR_PARAMETROS);
			}*/
			return iCiudadFacade.getCiudad(id);
		} catch (CommonException e) {
			LOGGER_ERROR.error(e);
			throw e;
		} catch (Exception e) {
			LOGGER_ERROR.error(e);
			throw new CommonException(e);
		}
	}
	public CiudadDTO guardar(CiudadDTO ciudad) throws CommonException {
		try{
			/*if (ValidatorForm.isEmpty(ciudad) || ValidatorForm.isEmpty(ciudad.getCiudad())) {
				throw new CommonException(MSG_INGRESAR_PARAMETROS);
			}*/
			return iCiudadFacade.guardar(ciudad);
		} catch (CommonException e) {
			LOGGER_ERROR.error(e);
			throw e;
		} catch (Exception e) {
			LOGGER_ERROR.error(e);
			throw new CommonException(e);
		}
	}
	public CiudadDTO modificar(BigDecimal id, CiudadDTO ciudad) throws CommonException {
		try{
			/*if (ValidatorForm.isEmpty(id) || ValidatorForm.isEmpty(ciudad) || ValidatorForm.isEmpty(ciudad.getCiudad())) {
				throw new CommonException(MSG_INGRESAR_PARAMETROS);
			}*/
			return iCiudadFacade.modificar(id, ciudad);
		} catch (CommonException e) {
			LOGGER_ERROR.error(e);
			throw e;
		} catch (Exception e) {
			LOGGER_ERROR.error(e);
			throw new CommonException(e);
		}
	}
	public void eliminar(BigDecimal ciudad) throws CommonException {
		try{
			/*if (ValidatorForm.isEmpty(ciudad)) {
				throw new CommonException(MSG_INGRESAR_PARAMETROS);
			}*/
			iCiudadFacade.eliminar(ciudad);
		} catch (CommonException e) {
			LOGGER_ERROR.error(e);
			throw e;
		} catch (Exception e) {
			LOGGER_ERROR.error(e);
			throw new CommonException(e);
		}
	}
}
