package Clases;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class DimensionArticulo {
	
	@Id @GeneratedValue
	private int id;
	
	private Double diametro;
	private Double largo;
	private Double ancho;
	
	public DimensionArticulo() {};
	
	public DimensionArticulo(Double diametro, Double largo, Double ancho) {
		this.diametro = diametro;
		this.largo = largo;
		this.ancho = ancho;
	}
	
	public List<Double> toArray() {
		List<Double> lista = new ArrayList<Double>();
		lista.add(diametro);
		lista.add(largo);
		lista.add(ancho);
		return lista;
		
	}

	public int getId() {
		return this.id;
	}

}
