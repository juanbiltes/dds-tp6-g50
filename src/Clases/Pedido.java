package Clases;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Pedido {
	
	private int idPedido = 0;
	private LocalDateTime fecha_primera_entrega;
	private int totalDeEntregasARealizar;
	private String periodicidad;
	private List<Articulo> articulosAPedir = new ArrayList<Articulo>();
	
	public Pedido setFechaDePrimeraEntrega(LocalDateTime fechaIndicadaPorUsuario) {
		this.fecha_primera_entrega = fechaIndicadaPorUsuario;
		return this;
	}
	
	public Pedido setTotalDeEntregasARealizar(int totalIndicadoPorUsuario) {
		this.totalDeEntregasARealizar = totalIndicadoPorUsuario;
		return this;
	}
	
	public Pedido setPeriodicidad(String periodicidad) {
		if(periodicidad == "unico" || periodicidad == "semanal" || periodicidad == "mensual") {
			this.periodicidad = periodicidad;
		} else {
			throw new IllegalArgumentException("Periodicidad Invalida, opciones: unico, semanal o mensual");
		}
		return this;
	}
	
	public Pedido agregarArticulo(Articulo articuloIndicadoPorUsuario) {
		this.articulosAPedir.add(articuloIndicadoPorUsuario);
		return this;
	}
	
	public int getIDPedido() {
		return this.idPedido;
	}
	
	// Setters & getters
	
	public String getPeriodicidad() {
		return this.periodicidad;
	}

	public void setID(int idIndicadoPorElPlanificador) {
		this.idPedido = idIndicadoPorElPlanificador;
	}
	
}
