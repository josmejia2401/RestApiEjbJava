package co.com.x.common.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class CiudadDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private BigDecimal codigo;
	private String ciudad;

	public CiudadDTO() {
		super();
	}

	public CiudadDTO(BigDecimal codigo, String ciudad) {
		super();
		this.codigo = codigo;
		this.ciudad = ciudad;
	}

	public BigDecimal getCodigo() {
		return codigo;
	}

	public void setCodigo(BigDecimal codigo) {
		this.codigo = codigo;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	
}
