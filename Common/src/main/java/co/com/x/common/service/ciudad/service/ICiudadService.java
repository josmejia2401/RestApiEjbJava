package co.com.x.common.service.ciudad.service;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import co.com.x.common.service.dto.CiudadDTO;
import co.com.x.common.service.exception.CommonException;

@Local
public interface ICiudadService {
	public List<CiudadDTO> getCiudades() throws CommonException;
	public CiudadDTO getCiudad(BigDecimal id) throws CommonException;
	public CiudadDTO guardar(CiudadDTO ciudad) throws CommonException;
	public CiudadDTO modificar(BigDecimal id, CiudadDTO ciudad) throws CommonException;
	public void eliminar(BigDecimal ciudad) throws CommonException;
}
