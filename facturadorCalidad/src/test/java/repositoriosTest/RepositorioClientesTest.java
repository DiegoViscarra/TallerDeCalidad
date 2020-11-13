package repositoriosTest;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import java.util.ArrayList;

import org.testng.annotations.BeforeMethod;

import casosDeUso.IPersistencia;
import casosDeUso.IPersistenciaArchivos;
import casosDeUso.IPersistenciaBDCDR;
import casosDeUso.IPersistenciaBDClientes;
import casosDeUso.IRepositorioCDR;
import casosDeUso.Persistencia;
import entidades.Cliente;
import modelos.ClienteModelo;
import repositorios.PersistenciaArchivos;
import repositorios.PersistenciaBDCDR;
import repositorios.PersistenciaBDClientes;
import repositorios.RepositorioCDR;
import repositorios.RepositorioCliente;

public class RepositorioClientesTest {
	public Cliente clienteNormal, clienteAmigos;
	public ArrayList<Integer> numerosAmigos;
	public RepositorioCliente repositorio = null;
	public IPersistencia persistencia = null;
	public IPersistenciaBDCDR persistenciaBDCDR = null;
	public IPersistenciaBDClientes persistenciaBDClientes = null;
	public IPersistenciaArchivos persistenciaArchivos = null;
	public IRepositorioCDR repositorioCDR = null;
	
	@BeforeClass
	public void initRegistro() {
		clienteNormal = new Cliente("Pedro", "1", 123);
		clienteAmigos = new Cliente("Ana", "2", 456);
		numerosAmigos = new ArrayList<Integer>();
		numerosAmigos.add(234);
		numerosAmigos.add(345);
		numerosAmigos.add(432);
		numerosAmigos.add(321);
		persistenciaBDCDR = new PersistenciaBDCDR();
		persistenciaBDClientes = new PersistenciaBDClientes();
		persistenciaArchivos = new PersistenciaArchivos();
		repositorioCDR = new RepositorioCDR();
		persistencia = new Persistencia(persistenciaBDCDR, persistenciaBDClientes, persistenciaArchivos, repositorioCDR);
		repositorio = new RepositorioCliente(persistencia);

	}
	
	@BeforeMethod
	public void initRegistrarNuevoClientePlanNormal(){
		repositorio.registrarNuevoClientePlanNormal(clienteNormal, "PREPAGO");
		persistenciaBDClientes.borrarTodosLosDatosDeClientes();
		persistenciaBDClientes.borrarTodosLosDatosDeNumerosAmigos();
	}
	
	@Test
	public void registrarNuevoClientePlanNormalTest() {
		Cliente cliente = repositorio.devolverClientes().get(0);
		Assert.assertSame(cliente, clienteNormal);
		
	}
	
	@BeforeMethod
	public void initRegistrarNuevoClientePlanNumerosAmigos() {
		repositorio.registrarNuevoClientePlanNumerosAmigos(clienteAmigos, "WOW", numerosAmigos);
	}
	
	@Test
	public void registrarNuevoClientePlanNumerosAmigos() {
		Cliente cliente = repositorio.devolverClientes().get(1);
		Assert.assertSame(cliente, clienteAmigos);
	}
	
	@Test
	public void buscarClienteTest() {
		Assert.assertSame(repositorio.buscarCliente(123), clienteNormal);
		Assert.assertSame(repositorio.buscarCliente(943), null);
	}
	
	@Test
	public void registrarClienteNormalEnDBTest() {
		String datos = "3;678;Sergio;Postpago;[]";
		repositorio.registrarClientes(datos);
		Assert.assertEquals(repositorio.buscarCliente(678).getNombre(), "Sergio");
		Assert.assertEquals(repositorio.buscarCliente(678).getCi(), "3");
		Assert.assertEquals(repositorio.buscarCliente(678).getNumeroTelefonico(), 678);
		
		ArrayList<ClienteModelo> recuperados = persistenciaBDClientes.mostrarTablaClientes();
		ClienteModelo buscado = null;
		for(ClienteModelo cliente : recuperados) {
			if(cliente.getNumeroTelefonico() == 678) {
				buscado = cliente;
			}
		}
		Assert.assertEquals(buscado.getCi(), "3");
		Assert.assertEquals(buscado.getNombre(), "Sergio");
		
	}
	
	@Test
	public void registrarClienteAmigosEnDBTest() {
		String datos = "4;900;Sara;Wow;[234,345,456,567]";
		ArrayList<Integer> amigos = new ArrayList<Integer>();
		amigos.add(234);
		amigos.add(345);
		amigos.add(456);
		amigos.add(567);
		repositorio.registrarClientes(datos);
		Assert.assertEquals(repositorio.buscarCliente(900).getNombre(), "Sara");
		Assert.assertEquals(repositorio.buscarCliente(900).getCi(), "4");
		Assert.assertEquals(repositorio.buscarCliente(900).getNumeroTelefonico(), 900);
		
		ArrayList<ClienteModelo> recuperados = persistenciaBDClientes.mostrarTablaClientes();
		ClienteModelo buscado = null;
		for(ClienteModelo cliente : recuperados) {
			if(cliente.getNumeroTelefonico() == 900) {
				buscado = cliente;
			}
		}
		Assert.assertEquals(buscado.getCi(), "4");
		Assert.assertEquals(buscado.getNombre(), "Sara");
		
		ArrayList<ClienteModelo> recuperadosAmigos = persistenciaBDClientes.mostrarTablaClientesConNumerosAmigos();
		ClienteModelo buscadoAmigos = null;
		for(ClienteModelo cliente : recuperadosAmigos) {
			if(cliente.getNumeroTelefonico() == 900) {
				buscadoAmigos = cliente;
			}
		}
		Assert.assertEquals(buscadoAmigos.getNumerosAmigos(), amigos);
	}
	
	@AfterClass
	public void borrarRegistrosClientes() {
		persistenciaBDClientes.borrarTodosLosDatosDeClientes();
		persistenciaBDClientes.borrarTodosLosDatosDeNumerosAmigos();
	}
	
	
	

}
