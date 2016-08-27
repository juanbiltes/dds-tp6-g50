package Clases;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table( name = "articulos_solicitados")
public class ArticuloSolicitado extends Articulo{
	
	@OneToOne
	private Envase envaseSeleccionado;

	private int cantRequeridaDelArticuloParaEntregar;
	
	private ArticuloSolicitado() {
		super();
	}
	
	public ArticuloSolicitado(String tipoArticulo, DimensionArticulo dimensiones) {
		super(tipoArticulo, dimensiones);
	}
	
	public void seleccionarEnvase(Envase envase) {
		this.envaseSeleccionado = envase;
	}

	public int getCantDemandadaDelArticulo() {
		return this.cantRequeridaDelArticuloParaEntregar;
	}
	
}
