package co.com.x.common.service.exception;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import co.com.x.common.service.dto.RespuestaDTO;

public class CommonException extends Exception {

	private static final long serialVersionUID = 1L;
	private String codigo;
	private String descripcion;

	public CommonException() {
		super();
	}

	public CommonException(Throwable exception) {
		super(exception);
	}
	
	public CommonException(String mensaje) {
		super(mensaje);
	}
	
	public CommonException(String mensaje, Throwable exception) {
		super(mensaje, exception);
	}

	public CommonException(String mensaje, String descripcion, String codigo) {
		super(mensaje);
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public CommonException(String codigo, String descripcion) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Response responseError() {
		RespuestaDTO<String> respuesta = new RespuestaDTO<String>();
		respuesta.setCodigo(99);
		if (getMessage() != null) {
			respuesta.setRespuesta(getMessage());
		} else {
			respuesta.setRespuesta("Se ha generado un error desconocido procesando la solicitud.");
		}
		GenericEntity<RespuestaDTO<String>> respuestaGenerica = new GenericEntity<RespuestaDTO<String>>(respuesta) {
		};
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuestaGenerica).build();
	}

}
