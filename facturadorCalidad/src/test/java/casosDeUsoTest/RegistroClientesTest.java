package casosDeUsoTest;

import java.util.ArrayList;
import org.testng.Assert;
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
import repositorios.PersistenciaArchivos;
import repositorios.PersistenciaBDCDR;
import repositorios.PersistenciaBDClientes;
import repositorios.RepositorioCDR;
import repositorios.RepositorioCliente;

public class RegistroClientesTest {
	
	IRegistroClientes registroClientes;
	
	public void guardarTemporalmenteCDRs() {
		
		Assert.assertEquals(registroClientes.buscarCliente(123).getNumeroTelefonico(), 123);
	}

	@BeforeMethod
	public void beforeMethod() {
		IRepositorioCDR repositorioCDR = new RepositorioCDR();
		IPersistenciaBDClientes persistenciaClientes = new PersistenciaBDClientes();
		IPersistencia persistencia = new Persistencia(new PersistenciaBDCDR(), persistenciaClientes, new PersistenciaArchivos(), repositorioCDR);
		IRepositorioCliente repositorioCliente = new RepositorioCliente(persistencia);
		repositorioCliente.registrarClientes("233;567;Sergio Roca;Postpago;[]\r\n");
		registroClientes = new RegistroClientes(repositorioCliente,persistenciaClientes);
	}
}
