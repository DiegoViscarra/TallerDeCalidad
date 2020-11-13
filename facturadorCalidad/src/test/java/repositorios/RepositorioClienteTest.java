package repositorios;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import casosDeUso.Persistencia;
import entidades.Cliente;

class RepositorioClienteTest {
	private final RepositorioCliente repositorio = new RepositorioCliente(new Persistencia(new PersistenciaBDCDR(), new PersistenciaBDClientes(), new PersistenciaArchivos(), new RepositorioCDR()));
	@Test
	void testRegistrarClientePlanNormal() {
		repositorio.registrarClientes("788;456;Sergio Ramos;Postpago;[]\r\n");
		ArrayList<Cliente> clientes = repositorio.devolverClientes();
		assertEquals(clientes.size(),1);
		assertEquals(clientes.get(0).getCi(), "788");
		assertEquals(clientes.get(0).getNumeroTelefonico(), 456);
		assertEquals(clientes.get(0).getTipoPlan(), "POSTPAGO");
	}
	@Test
	void testRegistrarClientePlanNumerosAmigos() {
		repositorio.registrarClientes("787;789;Ana Rojas;Wow;[234,345,456,567]\r\n");
		ArrayList<Cliente> clientes = repositorio.devolverClientes();
		assertEquals(clientes.size(),1);
		assertEquals(clientes.get(0).getCi(), "787");
		assertEquals(clientes.get(0).getNumeroTelefonico(), 789);
		assertEquals(clientes.get(0).getTipoPlan(), "WOW");
	}
}
