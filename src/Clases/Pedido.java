package Clases;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido {
	
	@Id @GeneratedValue
	private int idPedido = 0;
	
	private FechasDeEntrega fechasEntregas = new FechasDeEntrega();

	private int totalDeEntregasARealizar;
	
	private String periodicidad;
	
	private List<Articulo> articulosSolicitados = new ArrayList<Articulo>();
	
	public Pedido setFechaDePrimeraEntrega(LocalDateTime fechaIndicadaPorUsuario) {
		this.fechasEntregas.setFechaInicial(fechaIndicadaPorUsuario);
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
			fechasEntregas.unicaEntrega();
			break;
		case "semanal":
			this.periodicidad = periodicidad;
			fechasEntregas.entregasSemanales(totalEntregasARealizar);
			break;
		case "mensual":
			this.periodicidad = periodicidad;
			fechasEntregas.entregasMensuales(totalEntregasARealizar);
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
	
}
