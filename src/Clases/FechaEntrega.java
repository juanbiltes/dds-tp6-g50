package Clases;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "fechas_de_entrega")
public class FechaEntrega {

	@Id
	private LocalDateTime fecha;
	
	private Envase envaseAEntregarEnFecha;
	
	public FechaEntrega() {}

	public FechaEntrega(Envase envaseAEntregarEnFecha) {
		super();
		this.envaseAEntregarEnFecha = envaseAEntregarEnFecha;
	}
	
	private FechaEntrega(LocalDateTime fecha, Envase envaseAEntregarEnFecha) {
		this.fecha = fecha;
		this.envaseAEntregarEnFecha = envaseAEntregarEnFecha;
	}
	
	public FechaEntrega(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public FechaEntrega plusMonths(long cant) {
		return new FechaEntrega(this.fecha.plusMonths(cant), this.envaseAEntregarEnFecha);
	}
	
	public FechaEntrega plusDays(long cant) {
		return new FechaEntrega(this.fecha.plusDays(cant), this.envaseAEntregarEnFecha);
	}
	
	public void cambiarEnvase(Envase nuevoEnvase) {
		this.envaseAEntregarEnFecha = nuevoEnvase;
	}
	

}
