package Clases;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "envase")
public class Envase {
	
	@Id @GeneratedValue
	private int idEnvase;
	
	private int capacidad;

	public Envase() {};
	
	public Envase(int capacidadEnvase) {
		this.capacidad = capacidadEnvase;
	}
	
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	
	public int getCapacidad() {
		return this.capacidad;
	}
	
}
