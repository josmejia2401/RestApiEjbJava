package co.com.x.common.service.ciudad.facade;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import co.com.x.common.service.ciudad.service.ICiudadService;
import co.com.x.common.service.dto.CiudadDTO;
import co.com.x.common.service.exception.CommonException;

@Stateless
public class CiudadFacadeImpl implements ICiudadFacade{

	@EJB(beanName = "CiudadServiceImpl")
	private ICiudadService iCiudadService;

	@Override
	public List<CiudadDTO> getCiudades() throws CommonException {
		return iCiudadService.getCiudades();
	}

	@Override
	public CiudadDTO getCiudad(BigDecimal id) throws CommonException {
		return iCiudadService.getCiudad(id);
	}

	@Override
	public CiudadDTO guardar(CiudadDTO ciudad) throws CommonException {
		return iCiudadService.guardar(ciudad);
	}

	@Override
	public CiudadDTO modificar(BigDecimal id, CiudadDTO ciudad) throws CommonException {
		return iCiudadService.modificar(id, ciudad);
	}

	@Override
	public void eliminar(BigDecimal ciudad) throws CommonException {
		iCiudadService.eliminar(ciudad);
	}
}
