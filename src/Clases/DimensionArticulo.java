package Clases;
import java.util.ArrayList;
import java.util.List;

public class DimensionArticulo {
	
	private Double diametro;
	private Double largo;
	private Double ancho;
	
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

}
