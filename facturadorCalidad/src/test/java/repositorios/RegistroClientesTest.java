package repositorios;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import casosDeUso.Persistencia;
import casosDeUso.RegistroClientes;
import entidades.Cliente;

class RegistroClientesTest {
	private Persistencia persistencia = new Persistencia(new PersistenciaBDCDR(), new PersistenciaBDClientes(), new PersistenciaArchivos(), new RepositorioCDR());
	private final RegistroClientes registroClientes = new RegistroClientes(new RepositorioCliente(persistencia), new PersistenciaBDClientes());

	@Test
	void testRegistrarClientePlanNormal() {
		registroClientes.guardarClientesCargados("233;567;Sergio Roca;Postpago;[]\r\n");
		ArrayList<Cliente> clientes = registroClientes.devolverClientes();
		assertEquals(clientes.size(),1);
		assertEquals(clientes.get(0).getCi(), "233");
		assertEquals(clientes.get(0).getNumeroTelefonico(), 567);
		assertEquals(clientes.get(0).getTipoPlan(), "POSTPAGO");
		Cliente cliente = registroClientes.buscarCliente(567);
		assertEquals(cliente.getCi(), "233");
	}
	
	@Test
	void testRegistrarClientePlanNumerosAmigos() {
		registroClientes.guardarClientesCargados("455;234;Pedro Rojas;Wow;[234,345,456,567]\r\n");
		ArrayList<Cliente> clientes = registroClientes.devolverClientes();
		assertEquals(clientes.size(),1);
		assertEquals(clientes.get(0).getCi(), "455");
		assertEquals(clientes.get(0).getNumeroTelefonico(), 234);
		assertEquals(clientes.get(0).getTipoPlan(), "WOW");
	}


}
