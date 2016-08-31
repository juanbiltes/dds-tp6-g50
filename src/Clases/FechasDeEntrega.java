package Clases;

import java.awt.IllegalComponentStateException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class FechasDeEntrega {
	
	@Id @GeneratedValue
	private int id;
	
	@Transient
	private FechaEntrega fechaInicial = null;
	
	@OneToMany
	private List<FechaEntrega> fechasDeEntrega = new ArrayList<>();
	
	
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
