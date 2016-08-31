package Clases;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table( name = "articulos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Articulo {
	
	@Id @GeneratedValue
	private int idArticulo;
	
	@OneToMany
	private List<Envase> envasesDisponibles = new ArrayList<Envase>();
	
	private String tipoArticulo; // Seria el nombre...
	
	@OneToOne
	private DimensionArticulo dimensionArticulo;	
	
	public Articulo() {};
	
	public Articulo(String tipoArticulo2, DimensionArticulo dimensiones) {
		this.tipoArticulo = tipoArticulo2;
		this.dimensionArticulo = dimensiones;
	}
	
	
	public Articulo(String tipoArt, DimensionArticulo dimension, List<Envase> envasesDisponibles) {
		this.tipoArticulo = tipoArt;
		this.dimensionArticulo = dimension;
		this.envasesDisponibles = envasesDisponibles;
	}
	
	public boolean tieneEnvase(Envase envase) {
		return this.envasesDisponibles.contains(envase);
	}
	
	public boolean dimensionesMenoresQue(DimensionArticulo dimensionesTolerables) {
		return this.dimensionArticulo.esMenorQue(dimensionesTolerables);
	}

	protected void setId(int id) {
		this.idArticulo = id;
	}

	public int getId() {
		return this.idArticulo;
	}
	
	public void add(Envase envase) {
		this.envasesDisponibles.add(envase);
	}
	
	public List<Envase> getEnvasesDisponibles() {
		return envasesDisponibles;
	}

	public String getTipoDeArticulo() {
		return this.tipoArticulo;
	}

	public DimensionArticulo getDimensiones() {
		return this.dimensionArticulo;
	}
	
	

		
}
