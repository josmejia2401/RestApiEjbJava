package co.com.x.common.service.ciudad.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.com.x.common.service.dto.CiudadDTO;
import co.com.x.common.service.dto.CiudadEntity;
import co.com.x.common.service.exception.CommonException;



@Stateless
public class CiudadServiceImpl implements ICiudadService {
	
	private static final String MSG_NO_RESULT_EXCEPTION = "No se encontraron resultados.";
	private static final String MSG_EXCEPTION = "Se generó un error desconocido al procesar la solicitud.";
	
	private static final String PARAM_CODIGO= "codigo";
	private static final String NAMED_QUERY_BY_FIND_ONE = "CiudadEntity.findOne";
	private static final String NAMED_QUERY_BY_FIND_ALL = "CiudadEntity.findAll";
	
	public CiudadServiceImpl() {}
	
	@PersistenceContext(unitName = "FormulariosPersistence")
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<CiudadDTO> getCiudades() throws CommonException {
		try {
			Query nameQuery = em.createNamedQuery(NAMED_QUERY_BY_FIND_ALL);
			List<CiudadEntity> entityResult = nameQuery.getResultList();
			List<CiudadDTO> resultado = null;
			if (entityResult != null && !entityResult.isEmpty()) {
				resultado = new ArrayList<>();
				CiudadDTO dto = null;
				for (CiudadEntity e : entityResult) {
					dto = new CiudadDTO();
					dto.setCodigo(e.getCodigo());
					dto.setCiudad(e.getCiudad());
					resultado.add(dto);
				}
			}
			
			return resultado;
		} catch (NoResultException e) {
			throw new CommonException(MSG_NO_RESULT_EXCEPTION, e);
		} catch (Exception e) {
			throw new CommonException(MSG_EXCEPTION, e);
		}
	}

	@Override
	public CiudadDTO getCiudad(BigDecimal codigo) throws CommonException {
		try {
			Query nameQuery = em.createNamedQuery(NAMED_QUERY_BY_FIND_ONE);
			nameQuery.setParameter(PARAM_CODIGO, codigo);
			CiudadEntity entityResult = (CiudadEntity) nameQuery.getSingleResult();
			CiudadDTO dto = new CiudadDTO();
			dto.setCodigo(entityResult.getCodigo());
			dto.setCiudad(entityResult.getCiudad());
			return dto;
		} catch (NoResultException e) {
			throw new CommonException(MSG_NO_RESULT_EXCEPTION, e);
		} catch (Exception e) {
			throw new CommonException(MSG_EXCEPTION, e);
		}
	}

	@Override
	public CiudadDTO guardar(CiudadDTO ciudad) throws CommonException {
		try {
			CiudadEntity entity = new CiudadEntity();
			entity.setCiudad(ciudad.getCiudad());
			CiudadEntity entityResult = em.merge(entity);
			
			ciudad.setCodigo(entityResult.getCodigo());
			return ciudad;
		} catch (NoResultException e) {
			throw new CommonException(MSG_NO_RESULT_EXCEPTION, e);
		} catch (Exception e) {
			throw new CommonException(MSG_EXCEPTION, e);
		}
	}

	@Override
	public CiudadDTO modificar(BigDecimal id, CiudadDTO ciudad) throws CommonException {
		try {
			CiudadEntity entity = new CiudadEntity();
			entity.setCiudad(ciudad.getCiudad());
			entity.setCodigo(id);
			CiudadEntity entityResult = em.merge(entity);
			em.flush();
			ciudad.setCodigo(entityResult.getCodigo());
			ciudad.setCiudad(entityResult.getCiudad());
			return ciudad;
		} catch (NoResultException e) {
			throw new CommonException(MSG_NO_RESULT_EXCEPTION, e);
		} catch (Exception e) {
			throw new CommonException(MSG_EXCEPTION, e);
		}
	}

	@Override
	public void eliminar(BigDecimal idCiudad) throws CommonException {
		try {
			CiudadEntity e = em.find(CiudadEntity.class, idCiudad);
			if (e != null) {
				em.remove(e);
				em.flush();
			}
		} catch (NoResultException e) {
			throw new CommonException(MSG_NO_RESULT_EXCEPTION, e);
		} catch (Exception e) {
			throw new CommonException(MSG_EXCEPTION, e);
		}
	}
}
