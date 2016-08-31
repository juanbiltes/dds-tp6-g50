package Clases;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table( name = "clientes")
public class Cliente {
	
	@Id @GeneratedValue
	private int idCliente;
	
	private String nombreCliente;
	
	@OneToOne
	private Pedido pedidoRealizado;
	
	@SuppressWarnings("unused")
	private Cliente() {};
	
	public Cliente(int id) {
		// Para tests sin db
		this.idCliente = id;
	}
	
	public Cliente(String nombre) {
		this.nombreCliente = nombre;
	}
	
	public void hacerPedido(Pedido pedido) {
		this.pedidoRealizado = pedido;
	}
	
	public void setID(int idAsignadoPorElSistema) {
		this.idCliente = idAsignadoPorElSistema;
	}
	
	
	public int getUserID() {
		return this.idCliente;
	}

	public String getName() {
		return this.nombreCliente;
	}

}
