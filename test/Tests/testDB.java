package Tests;

import static org.junit.Assert.*;

import java.awt.IllegalComponentStateException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transaction;

import Clases.Articulo;
import Clases.ArticuloStock;
import Clases.Cliente;
import Clases.DimensionArticulo;
import Clases.Envase;
import Clases.Pedido;
import Clases.Planificador;


public class testDB  extends AbstractPersistenceTest implements WithGlobalEntityManager {
	

	
	Cliente pepe = new Cliente(0);
	Cliente pepita = new Cliente(1);
	
	Planificador planificador = Planificador.getInstance();
	
	DimensionArticulo dimensionJabon = new DimensionArticulo(10.2, 11.5, 123.0);
	DimensionArticulo dimensionMetalAnguloso = new DimensionArticulo(100.0, 111.5, 123.0);
	
	List<Envase> envasesJabon = new ArrayList<Envase>();
	List<Envase> envasesMetalAnguloso = new ArrayList<Envase>();
	
	Articulo jabonDeAcero;
	Articulo metalAnguloso;
	
	Pedido pedidoDePepe = new Pedido();
	Pedido pedidoDePepita = new Pedido();


	@Before
	public void setUp() throws Exception {
		
		envasesJabon.add(new Envase(20));
		envasesJabon.add(new Envase(40));
		envasesJabon.add(new Envase(60));
		
		envasesMetalAnguloso.add(new Envase(300));
		envasesMetalAnguloso.add(new Envase(400));
		
		jabonDeAcero = new ArticuloStock("Jabon de Acero", dimensionJabon, envasesJabon);
		metalAnguloso = new ArticuloStock("Metal Anguloso", dimensionJabon, envasesJabon);
		
		planificador.agregarArticuloAStock(jabonDeAcero);
		planificador.agregarArticuloAStock(metalAnguloso);
		
		planificador.agregarCliente(pepe);
		planificador.agregarCliente(pepita);		

	}	
	
	
	@Test
	public void testSeObtienenLosEnvases() throws Exception {
		assertTrue(planificador.consultarEnvasesDisponibles(jabonDeAcero) == envasesJabon);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSeIngresaMalLaPeriodicidad() throws Exception {
		pedidoDePepe.setPeriodicidad("todas las semanas");
	}
	
	@Test
	public void testElSistemaRetieneLosPedidos() throws Exception {
		planificador.hacerPedido(pedidoDePepe);
		planificador.hacerPedido(pedidoDePepita);
		
		assertEquals(2, planificador.getPedidosRecibidos().size());
		assertEquals(pedidoDePepe, planificador.getPedidosRecibidos().get(0));
		assertEquals(pedidoDePepita, planificador.getPedidosRecibidos().get(1));
	}
	
	@Test(expected=IllegalComponentStateException.class)
	public void testExcepcionPorNoDefinirFechaInicial() throws Exception {
		pedidoDePepe.agregarArticulo(jabonDeAcero)
					.agregarArticulo(metalAnguloso)
					.setPeriodicidad("mensual");
	}
	
	
	@Test
	public void testElSistemaAsignaElIDAlPedido() throws Exception {
		pedidoDePepe.agregarArticulo(jabonDeAcero)
					.agregarArticulo(metalAnguloso)
					.setFechaDePrimeraEntrega(LocalDateTime.now().plusDays(2))
					.setPeriodicidad("mensual")
					.setTotalDeEntregasARealizar(4);
		
		assertEquals(0,pedidoDePepe.getIDPedido());
		
		int idPedido = planificador.hacerPedido(pedidoDePepe);
		
		assertTrue(idPedido == pedidoDePepe.getIDPedido());
	}
}