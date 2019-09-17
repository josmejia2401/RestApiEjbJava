package co.com.x.common.service.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name="OCC_CIUDADES")
@NamedQueries({
	@NamedQuery(name="CiudadEntity.findAll", query="SELECT c FROM CiudadEntity c"),
	@NamedQuery(name="CiudadEntity.findOne", query="SELECT c FROM CiudadEntity c WHERE c.codigo =:codigo")
})
public class CiudadEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQ_CIUDAD_ID_GENERATOR", sequenceName = "SEQ_CIUDAD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CIUDAD_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private BigDecimal codigo;
	
	private String ciudad;

	public CiudadEntity() {
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