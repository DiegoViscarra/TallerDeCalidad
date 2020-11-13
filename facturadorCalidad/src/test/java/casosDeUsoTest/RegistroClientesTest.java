package casosDeUsoTest;

import java.util.ArrayList;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import casosDeUso.IPersistencia;
import casosDeUso.IPersistenciaBDClientes;
import casosDeUso.IRegistroClientes;
import casosDeUso.IRepositorioCDR;
import casosDeUso.IRepositorioCliente;
import casosDeUso.ITarificacion;
import casosDeUso.Persistencia;
import casosDeUso.RegistroClientes;
import casosDeUso.Tarificacion;
import entidades.Cliente;
import repositorios.PersistenciaArchivos;
import repositorios.PersistenciaBDCDR;
import repositorios.PersistenciaBDClientes;
import repositorios.RepositorioCDR;
import repositorios.RepositorioCliente;

public class RegistroClientesTest {
	RegistroClientes registroClientes;
	PersistenciaBDClientes persistenciaClientes;
	PersistenciaBDCDR persistenciaBDCDR ;

	@Test
	public void guardarTemporalmenteCDRs() {
		registroClientes.guardarClientesCargados("788;456;Sergio Ramos;Postpago;[]\r\n");
		Assert.assertEquals(registroClientes.buscarCliente(456).getNumeroTelefonico(), 456);
	}

	@Test
	public void guardarClientesCargados() {
		registroClientes.guardarClientesCargados("788;456;Sergio Ramos;Postpago;[]\r\n");
		ArrayList<Cliente> resultado = registroClientes.devolverClientes();

		Assert.assertEquals(resultado.get(0).getCi(), "788");
	}

	@Test
	public void llenarListaClientes() {
		registroClientes.guardarClientesCargados("788;456;Sergio Ramos;Postpago;[]\r\n");
		registroClientes.llenarListaClientes();
		ArrayList<Cliente> resultado = registroClientes.devolverClientes();
		Assert.assertEquals(resultado.get(0).getCi(), "788");
	}

	@Test
	public void llenarListaClientesPlanWow() {
		registroClientes.guardarClientesCargados("788;456;Sergio Ramos;Wow;[234,234,456,567]\r\n");
		registroClientes.llenarListaClientes();
		ArrayList<Cliente> resultado = registroClientes.devolverClientes();
		Assert.assertEquals(resultado.get(0).getCi(), "788");
	}

	@Test
	public void noCoincidenEnNumeroTelefonico() {
		registroClientes.guardarClientesCargados("788;321;Sergio Ramos;Wow;[234,423,456,567]\r\n");
		registroClientes.llenarListaClientes();
		ArrayList<Cliente> resultado = registroClientes.devolverClientes();
		Assert.assertEquals(resultado.get(0).getCi(), "788");
	}

	@BeforeMethod
	public void beforeMethod() {
		IRepositorioCDR repositorioCDR = new RepositorioCDR();
		persistenciaClientes = new PersistenciaBDClientes();
		persistenciaBDCDR = new PersistenciaBDCDR();
		persistenciaBDCDR.borrarTodosLosDatosDeUsuario();
		IPersistencia persistencia = new Persistencia(persistenciaBDCDR, persistenciaClientes,
				new PersistenciaArchivos(), repositorioCDR);
		IRepositorioCliente repositorioCliente = new RepositorioCliente(persistencia);
		registroClientes = new RegistroClientes(repositorioCliente, persistenciaClientes);
	}

	@AfterClass
	public void borrarRegistrosClientes() {
		persistenciaClientes.borrarTodosLosDatosDeClientes();
		persistenciaClientes.borrarTodosLosDatosDeNumerosAmigos();
		persistenciaBDCDR.borrarTodosLosDatosDeCDR();
		persistenciaBDCDR.borrarTodosLosDatosDeUsuario();
	}

}
