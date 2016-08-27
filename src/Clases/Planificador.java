package Clases;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Planificador {
	
	private List<Cliente> repositorioDeClientes = new ArrayList<Cliente>();
	private List<Articulo> articulosDisponibles = new ArrayList<Articulo>();
	private List<Pedido> pedidosRecibidos = new ArrayList<Pedido>();
	private static Planificador instance;
	
	protected Planificador(){};
	
	public static Planificador getInstance() {
		if(instance == null) instance = new Planificador();
		return instance;
	}
	
	public int agregarCliente(Cliente cliente) {
		repositorioDeClientes.add(cliente);
		return cliente.getUserID();
	}
	
	public int agregarArticuloAStock(Articulo articulo) {
		// Para que la empresa agregue los articulos de los que dispone
		this.articulosDisponibles.add(articulo);
		return articulosDisponibles.indexOf(articulo);
	}
	
	public int hacerPedido(Pedido pedido) {
		this.pedidosRecibidos.add(pedido);
		return pedido.getIDPedido();
	}
	
	public List<Envase> consultarEnvasesDisponibles(Articulo articuloAConsultar) {
		return articuloAConsultar.getEnvasesDisponibles();
	}
	
	// Setters & Getters
	
	public List<Pedido> getPedidosRecibidos() {
		return this.pedidosRecibidos;
	}
	

}
