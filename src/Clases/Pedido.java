package Clases;
import java.awt.IllegalComponentStateException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "pedido")
public class Pedido {
	
	@Id @GeneratedValue
	private int idPedido;
	
	@Transient
	private FechaEntrega fechaInicial = null;
	
	@OneToOne
	List<FechaEntrega> fechasDeEntrega = new ArrayList<>();

	private int totalDeEntregasARealizar = 0;
	
	private String periodicidad;
	
	@OneToMany
	private List<Articulo> articulosSolicitados = new ArrayList<Articulo>();
	
	public Pedido setFechaDePrimeraEntrega(LocalDateTime fechaIndicadaPorUsuario) {
		this.fechaInicial = new FechaEntrega(fechaIndicadaPorUsuario);
		return this;
	}
	
	public Pedido setTotalDeEntregasARealizar(int totalIndicadoPorUsuario) {
		this.totalDeEntregasARealizar = totalIndicadoPorUsuario;
		return this;
	}
	
	public Pedido setPeriodicidad(String periodicidad) {
		int totalEntregasARealizar = totalDeEntregasARealizar;
		
		switch (periodicidad) {
		case "unico":
			this.periodicidad = periodicidad;
			this.unicaEntrega();
			break;
		case "semanal":
			this.periodicidad = periodicidad;
			this.entregasSemanales(totalEntregasARealizar);
			break;
		case "mensual":
			this.periodicidad = periodicidad;
			this.entregasMensuales(totalEntregasARealizar);
			break;
		default:
			throw new IllegalArgumentException("Periodicidad Invalida, opciones: unico, semanal o mensual");
		}
		return this;
	}
	
	public Pedido agregarArticulo(Articulo articuloIndicadoPorUsuario) {
		this.articulosSolicitados.add(articuloIndicadoPorUsuario);
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
	
	


	
	
	
	public void setFechaInicial(LocalDateTime fecha) {
		this.fechaInicial = new FechaEntrega(fecha);
	}
	
	private void checkFechaInicial() {
		if (this.fechaInicial == null) {
			throw new IllegalComponentStateException("Por favor, indique primero una fecha inicial");
		}
	}

	public void unicaEntrega() {
		this.checkFechaInicial();
		this.fechasDeEntrega.add(fechaInicial);
	}

	public void entregasSemanales(int cantEntregas) {
		this.checkFechaInicial();
		this.fechasDeEntrega.add(fechaInicial);
		for (int i = 0; i < cantEntregas; i++) {
			fechasDeEntrega.add(fechaInicial.plusDays(7));
		}
	}

	public void entregasMensuales(int cantEntregas) {
		this.checkFechaInicial();
		this.fechasDeEntrega.add(fechaInicial);
		for (int i = 0; i < cantEntregas; i++) {
			fechasDeEntrega.add(fechaInicial.plusMonths(1));
		}
	}
	
}
