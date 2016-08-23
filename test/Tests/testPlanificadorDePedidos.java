package Tests;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Clases.Articulo;
import Clases.Cliente;
import Clases.DimensionArticulo;
import Clases.Pedido;
import Clases.Planificador;


public class testPlanificadorDePedidos {
	
	Cliente pepe = new Cliente();
	Cliente pepita = new Cliente();
	
	Planificador planificador = Planificador.getInstance();
	
	DimensionArticulo dimensionJabon = new DimensionArticulo(10.2, 11.5, 123.0);
	DimensionArticulo dimensionMetalAnguloso = new DimensionArticulo(100.0, 111.5, 123.0);
	
	List<String> envasesJabon = new ArrayList<String>();
	List<String> envasesMetalAnguloso = new ArrayList<String>();
	
	Articulo jabonDeAcero;
	Articulo metalAnguloso;
	
	Pedido pedidoDePepe = new Pedido();
	Pedido pedidoDePepita = new Pedido();


	@Before
	public void setUp() throws Exception {
		
		envasesJabon.add("Caja carton de 6");
		envasesJabon.add("Caja metal de 20");
		
		envasesMetalAnguloso.add("Envase en Scania");
		envasesMetalAnguloso.add("Envase en flete");
		
		jabonDeAcero = new Articulo(321456, "Jabon de Acero", dimensionJabon, envasesJabon);
		metalAnguloso = new Articulo(321456, "Metal Anguloso", dimensionJabon, envasesJabon);
		
		planificador.agregarArticuloAStock(jabonDeAcero);
		planificador.agregarArticuloAStock(metalAnguloso);
		
		planificador.agregarCliente(pepe);
		planificador.agregarCliente(pepita);		

	}	
	
	@Test(expected=IllegalArgumentException.class)
	public void testSeAgregaNuevamenteUnUsuarioAlPlanificador() throws Exception {
		planificador.agregarCliente(pepe);
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
	
	@Test
	public void testElSistemaAsignaElIDAlPedido() throws Exception {
		pedidoDePepe.agregarArticulo(jabonDeAcero)
					.agregarArticulo(metalAnguloso)
					.setPeriodicidad("mensual")
					.setFechaDePrimeraEntrega(LocalDateTime.now().plusDays(2))
					.setTotalDeEntregasARealizar(4);
		
		assertEquals(0,pedidoDePepe.getIDPedido());
		
		int idPedido = planificador.hacerPedido(pedidoDePepe);
		
		assertTrue(idPedido == pedidoDePepe.getIDPedido());
	}
	
}

