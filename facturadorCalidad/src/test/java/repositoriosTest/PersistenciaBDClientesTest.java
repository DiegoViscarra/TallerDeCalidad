package repositoriosTest;

import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import entidades.CDR;
import entidades.Cliente;
import modelos.CDRModelo;
import modelos.ClienteModelo;
import repositorios.ConnectionDB;
import repositorios.PersistenciaBDClientes;
import repositorios.RepositorioCDR;

public class PersistenciaBDClientesTest {
	PersistenciaBDClientes persistenciaBDClientes;
	@BeforeMethod
	public void beforeMethod() {
		persistenciaBDClientes = new PersistenciaBDClientes();
	}

	@Test 
	void crearTablaClientesYNumerosAmigos() throws SQLException {
		persistenciaBDClientes.crearTabla();
		ConnectionDB connectionDB = new ConnectionDB();
		persistenciaBDClientes.conexionBD = DriverManager.getConnection(connectionDB.getConnection("Users.txt"));
		persistenciaBDClientes.conexionBD.createStatement();
		DatabaseMetaData dbm = persistenciaBDClientes.conexionBD.getMetaData();
		ResultSet tables = dbm.getTables(null, null, "Clientes", null);
		Assert.assertNotNull(tables.next());
		ResultSet tablesAmigos = dbm.getTables(null, null, "NumerosAmigos", null);
		Assert.assertNotNull(tablesAmigos.next());
		persistenciaBDClientes.conexionBD.close();
	}
	
	@Test
	void poblarTablaDeClientes() {
		persistenciaBDClientes.crearTabla();
		Cliente cliente = new Cliente("Juan", "213", 1);
		cliente.setTipoPlan("PREPAGO");
		persistenciaBDClientes.poblarTablaClientes(cliente);
		Assert.assertNotNull(persistenciaBDClientes.conexionBD);
	}
	
	@Test
	void poblarTablaDeNumerosDeAmigos() {
		persistenciaBDClientes.crearTabla();
		Cliente cliente = new Cliente("Pedro", "123", 2);
		cliente.setTipoPlan("WOW");
		ArrayList<Integer> numerosAmigos = new ArrayList<>(Arrays.asList(4,5,6,7));
		persistenciaBDClientes.poblarTablaClientesConNumerosAmigos(numerosAmigos, 1);
		Assert.assertNotNull(persistenciaBDClientes.conexionBD);
	}
	
	@Test
	void mostrarTablaDeClientes() throws SQLException {
		persistenciaBDClientes.crearTabla();
		Cliente cliente = new Cliente("Juan", "213", 1);
		cliente.setTipoPlan("PREPAGO");
		persistenciaBDClientes.poblarTablaClientes(cliente);
		ConnectionDB connectionDB = new ConnectionDB();
		persistenciaBDClientes.conexionBD = DriverManager.getConnection(connectionDB.getConnection("Users.txt"));
		persistenciaBDClientes.conexionBD.createStatement();
		ArrayList<ClienteModelo> resultado= persistenciaBDClientes.mostrarTablaClientes();
		Assert.assertNotNull(resultado);
	}
	
	@Test
	void mostrarTablaDeNumerosAmigos() throws SQLException {
		persistenciaBDClientes.crearTabla();
		Cliente cliente = new Cliente("Pedro", "123", 2);
		cliente.setTipoPlan("WOW");
		ArrayList<Integer> numerosAmigos = new ArrayList<>(Arrays.asList(4,5,6,7));
		persistenciaBDClientes.poblarTablaClientesConNumerosAmigos(numerosAmigos, 1);
		ConnectionDB connectionDB = new ConnectionDB();
		persistenciaBDClientes.conexionBD = DriverManager.getConnection(connectionDB.getConnection("Users.txt"));
		persistenciaBDClientes.conexionBD.createStatement();
		ArrayList<ClienteModelo> resultado= persistenciaBDClientes.mostrarTablaClientesConNumerosAmigos();
		Assert.assertNotNull(resultado);
	}
	
	@AfterMethod
	public void afterMethod() {
		persistenciaBDClientes.borrarTodosLosDatosDeClientes();
		persistenciaBDClientes.borrarTodosLosDatosDeNumerosAmigos();
	}
	
}
