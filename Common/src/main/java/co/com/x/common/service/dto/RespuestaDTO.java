package co.com.x.common.service.dto;


public class RespuestaDTO<T> {
	private int codigo;
	private String mensaje;
	private T respuesta;
	
	
	public RespuestaDTO() {
		super();
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public T getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(T respuesta) {
		this.respuesta = respuesta;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
