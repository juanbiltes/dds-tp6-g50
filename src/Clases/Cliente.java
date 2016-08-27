package Clases;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "clientes")
public class Cliente {
	
	@Id @GeneratedValue
	private int idCliente;
	
	private Pedido pedidoRealizado;
	
	@SuppressWarnings("unused")
	private Cliente() {};
	
	public Cliente(int id) {
		this.idCliente = id;
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

}
