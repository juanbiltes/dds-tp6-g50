package Clases;

import java.awt.IllegalComponentStateException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class FechasDeEntrega {
	
	
	private FechaEntrega fechaInicial = null;
	private List<FechaEntrega> fechasDeEntrega = new ArrayList<FechaEntrega>();
	
	
	public FechasDeEntrega() {};
	
	public FechasDeEntrega(FechaEntrega fechaInicial) {
		super();
		this.fechaInicial = fechaInicial;
	}
	
	public void setFechaInicial(LocalDateTime fecha) {
		this.fechaInicial = new FechaEntrega(fecha);
	}
	
	private void checkFechaInicial() {
		if (this.fechaInicial == null) {
			throw new IllegalComponentStateException("Por favor, indique primero una fecha inicial");
		}
	}

	public FechasDeEntrega unicaEntrega() {
		this.checkFechaInicial();
		this.fechasDeEntrega.add(fechaInicial);
		return this;
	}

	public FechasDeEntrega entregasSemanales(int cantEntregas) {
		this.checkFechaInicial();
		this.fechasDeEntrega.add(fechaInicial);
		for (int i = 0; i < cantEntregas; i++) {
			fechasDeEntrega.add(fechaInicial.plusDays(7));
		}
		return this;
	}

	public FechasDeEntrega entregasMensuales(int cantEntregas) {
		this.checkFechaInicial();
		this.fechasDeEntrega.add(fechaInicial);
		for (int i = 0; i < cantEntregas; i++) {
			fechasDeEntrega.add(fechaInicial.plusMonths(1));
		}
		return this;
	}

}
