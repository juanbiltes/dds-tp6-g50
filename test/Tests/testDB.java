package Tests;

import static org.junit.Assert.*;

import java.awt.IllegalComponentStateException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transaction;

import Clases.Articulo;
import Clases.Cliente;
import Clases.DimensionArticulo;
import Clases.Envase;
import Clases.FechaEntrega;
import Clases.Pedido;
import Clases.Planificador;


public class testDB  extends AbstractPersistenceTest implements WithGlobalEntityManager {
	
	@Test
	public void contextUp() {
		assertNotNull(entityManager());
	}
	
	@Test
	public void contextUpWithTransaction() throws Exception {
		withTransaction(() -> {});
	}
	

	
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
		
		jabonDeAcero = new Articulo("Jabon de Acero", dimensionJabon, envasesJabon);
		metalAnguloso = new Articulo("Metal Anguloso", dimensionJabon, envasesMetalAnguloso);
		
		planificador.agregarArticuloAStock(jabonDeAcero);
		planificador.agregarArticuloAStock(metalAnguloso);
		
		planificador.agregarCliente(pepe);
		planificador.agregarCliente(pepita);		

	}	
	
	@Test
	public void persistsDimensionArticulo() {
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		DimensionArticulo dimensionJabon = new DimensionArticulo(10.2, 11.5, 123.0);
		
		EntityTransaction tx = entityManager.getTransaction();
		
		tx.rollback();
		
		tx.begin();
		
		entityManager.persist(dimensionJabon);
		
		tx.commit();
		
		DimensionArticulo encontrado = entityManager.find(DimensionArticulo.class, dimensionJabon.getId());
		
		assertEquals(dimensionJabon, encontrado);
		
		entityManager.clear();
		PerThreadEntityManagers.closeEntityManager();
	}
	
	@Test
	public void persistsClienteQueTodaviaNoHizoPedido() {
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		Cliente clienteX = new Cliente("Pepin");
		
		EntityTransaction tx = entityManager.getTransaction();
		
		tx.rollback();
		
		tx.begin();
		
		entityManager.persist(clienteX);
		
		tx.commit();
		
		Cliente encontrado = entityManager.find(Cliente.class, clienteX.getUserID());
		
		assertEquals(clienteX, encontrado);
		assertEquals(encontrado.getName(), "Pepin");
		
		entityManager.clear();
		PerThreadEntityManagers.closeEntityManager();
	}
	
	@Test
	public void persistsEnvases() {
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		
		EntityTransaction tx = entityManager.getTransaction();
		
		tx.rollback();
		
		tx.begin();
		
		envasesJabon.forEach(envase -> entityManager.persist(envase));
		
		tx.commit();
		
		envasesJabon.forEach(envaseJabon -> {
			assertEquals(envaseJabon, entityManager.find(Envase.class, envaseJabon.getId()));
		});
		
		entityManager.clear();
		PerThreadEntityManagers.closeEntityManager();
	}
	
	@Test
	public void persistsFechaEntrega() {
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		Envase envaseRequeridoParaLaFechaX = new Envase(30);
		FechaEntrega fechaX = new FechaEntrega(envaseRequeridoParaLaFechaX, LocalDateTime.now());
		
		
		EntityTransaction tx = entityManager.getTransaction();
		
		tx.rollback();
		
		tx.begin();
		
		entityManager.persist(envaseRequeridoParaLaFechaX);
		entityManager.persist(fechaX);
		
		tx.commit();
		
		assertEquals(fechaX, entityManager.find(FechaEntrega.class, fechaX.getId()));
		
		entityManager.clear();
		PerThreadEntityManagers.closeEntityManager();
	}
	
	//@Test
	public void persistArticulo() {
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		Articulo jabonDeAcero = new Articulo("Jabon de Acero", dimensionJabon, envasesJabon);
		
		EntityTransaction tx = entityManager.getTransaction();
		
		tx.rollback();
		
		tx.begin();
		
		entityManager.persist(jabonDeAcero);
		
		tx.commit();
		
		Articulo articuloObtenido = entityManager.find(Articulo.class, jabonDeAcero.getId());
		assertEquals(articuloObtenido.getId(), jabonDeAcero.getId());

		entityManager.clear();
		PerThreadEntityManagers.closeEntityManager();
		
	}

}