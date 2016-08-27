package Clases;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table( name = "articulos_stock")
public class ArticuloStock extends Articulo {
	
	@OneToMany
	private List<Envase> envasesDisponibles = new ArrayList<Envase>();
	
	@SuppressWarnings("unused")
	private ArticuloStock() {};

	public ArticuloStock(String tipoArt, DimensionArticulo dimensiones, List<Envase> envases) {
		super(tipoArt, dimensiones);
		this.envasesDisponibles = envases;
	}
	
	public void add(Envase envase) {
		this.envasesDisponibles.add(envase);
	}
	public List<Envase> getEnvasesDisponibles() {
		return envasesDisponibles;
	}

}
