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
		setRandomClientID(cliente);
		repositorioDeClientes.add(cliente);
		return cliente.getUserID();
	}
	
	private Cliente setRandomClientID(Cliente cliente) {
		cliente.setID(new Random().nextInt(999999) + 1);
		repositorioDeClientes.forEach( clienteDelRepo -> {
			if(cliente == clienteDelRepo)  {
				throw new IllegalArgumentException("Ya se encuentra este usuario en el planificador");
			} else if(clienteDelRepo.getUserID() == cliente.getUserID()) {
				// Si de casualidad nos dio un ID que ya fue asignado, asignamos otro
				setRandomClientID(cliente);
			}
		});
		return cliente;
	}
	
	public int agregarArticuloAStock(Articulo articulo) {
		// Para que la empresa agregue los articulos de los que dispone
		this.articulosDisponibles.add(articulo);
		return articulosDisponibles.indexOf(articulo);
	}
	
	public int hacerPedido(Pedido pedido) {
		this.pedidosRecibidos.add(pedido);
		// Sumamos 1 para asegurar nunca el id sea 0
		pedido.setID(new Random().nextInt(999999) + 1);
		return pedido.getIDPedido();
	}
	
	public List<String> consultarEnvasesDisponibles(Articulo articuloAConsultar) {
		return articuloAConsultar.getEnvasesDisponibles();
	}
	
	// Setters & Getters
	
	public List<Pedido> getPedidosRecibidos() {
		return this.pedidosRecibidos;
	}
	

}
