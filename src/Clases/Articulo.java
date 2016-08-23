package Clases;
import java.util.ArrayList;
import java.util.List;

public class Articulo {
	private int idArticulo;
	private DimensionArticulo dimensionesMaximas;
	private int cantRequeridaDelArticuloParaEntregar;
	private List<String> envasesDisponibles = new ArrayList<String>();
	private String envaseSeleccionado;
	
	private String nombreArticulo;
	
	public Articulo(int idArt, 
					String nombreArticulo,
					DimensionArticulo dimensiones,
					List<String> envasesDisponibles) {
		// Constructor para la Empresa
		this.idArticulo = idArt;
		this.nombreArticulo = nombreArticulo;
		this.dimensionesMaximas = dimensiones;
		this.envasesDisponibles = envasesDisponibles;
	}
	
	public void cantidadRequeridaParaEntregar(int cantidadDelArticulo) {
		this.cantRequeridaDelArticuloParaEntregar = cantidadDelArticulo;
	}
	
	public List<String> getEnvasesDisponibles() {
		return envasesDisponibles;
	}

	public void seleccionarEnvase(int indiceItemSeleccionado) {
		this.envaseSeleccionado = envasesDisponibles.get(indiceItemSeleccionado);
	}
	
	public void seleccionarEnvase(String nombreEnvaseSeleccionado) {
		// Solamente seleccionamos el envase si coincide con algunos de los que indico la empresa
		this.envasesDisponibles.forEach( envase -> {
				if(envase.toLowerCase() == nombreEnvaseSeleccionado.toLowerCase()) {
					this.envaseSeleccionado = envase;
				} else { 
					throw new IllegalArgumentException("Envase Invalido, consulte la lista e ingrese el numero del item o su nombre tal cual lo informa el sistema");
				};	
		});
	}
	
}
