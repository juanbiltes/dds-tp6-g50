package Clases;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Cliente {
	
	private int idCliente = 0;
	private Pedido pedidoRealizado;
	
	
	
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
