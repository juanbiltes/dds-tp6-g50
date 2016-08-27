package Clases;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="articulos")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class Articulo {
	
	@Id @GeneratedValue
	private int idArticulo;
	
	private String tipoArticulo; // Seria el nombre...
	
	@OneToOne
	private DimensionArticulo dimensionArticulo;	
	
	public Articulo() {};
	
	public Articulo(String tipoArticulo2, DimensionArticulo dimensiones) {
		this.tipoArticulo = tipoArticulo2;
		this.dimensionArticulo = dimensiones;
	}
	
	
	protected void setId(int id) {
		this.idArticulo = id;
	}

	public List<Envase> getEnvasesDisponibles() {
		return null;
	}

		
}
