package repositoriosTest;

import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import entidades.CDR;
import entidades.Cliente;
import modelos.CDRModelo;
import modelos.ClienteModelo;
import repositorios.ConnectionDB;
import repositorios.PersistenciaBDClientes;

public class PersistenciaBDClientesTest {
	
	
	@Test 
	void crearTabla() throws SQLException {
		PersistenciaBDClientes persistenciaBDClientes = new PersistenciaBDClientes();
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
	void poblarTabla() {
		PersistenciaBDClientes persistenciaBDClientes = new PersistenciaBDClientes();
		Cliente cliente = new Cliente("Juan", "213", 1);
		cliente.setTipoPlan("PREPAGO");
		persistenciaBDClientes.poblarTablaClientes(cliente);
		Assert.assertNotNull(persistenciaBDClientes.conexionBD);
	}
	
	@Test
	void poblarTablanumAmig() {
		PersistenciaBDClientes persistenciaBDClientes = new PersistenciaBDClientes();
		Cliente cliente = new Cliente("Pedro", "123", 2);
		cliente.setTipoPlan("WOW");
		ArrayList<Integer> numerosAmigos = new ArrayList<>(Arrays.asList(4,5,6,7));
		persistenciaBDClientes.poblarTablaClientesConNumerosAmigos(numerosAmigos, 1);
		Assert.assertNotNull(persistenciaBDClientes.conexionBD);
	}
	
	@Test
	void mostrarTabla() throws SQLException {
		PersistenciaBDClientes persistenciaBDClientes = new PersistenciaBDClientes();
		ConnectionDB connectionDB = new ConnectionDB();
		persistenciaBDClientes.conexionBD = DriverManager.getConnection(connectionDB.getConnection("Users.txt"));
		persistenciaBDClientes.conexionBD.createStatement();
		ArrayList<ClienteModelo> resultado= persistenciaBDClientes.mostrarTablaClientes();
		Assert.assertNotNull(resultado);
	}
	
	@Test
	void mostrarTablaAmig() throws SQLException {
		PersistenciaBDClientes persistenciaBDClientes = new PersistenciaBDClientes();
		ConnectionDB connectionDB = new ConnectionDB();
		persistenciaBDClientes.conexionBD = DriverManager.getConnection(connectionDB.getConnection("Users.txt"));
		persistenciaBDClientes.conexionBD.createStatement();
		ArrayList<ClienteModelo> resultado= persistenciaBDClientes.mostrarTablaClientesConNumerosAmigos();
		Assert.assertNotNull(resultado);
	}
	
	@Test
	void borrarDatos()  {
		PersistenciaBDClientes persistenciaBDClientes = new PersistenciaBDClientes();
		persistenciaBDClientes.borrarTodosLosDatosDeClientes();
		persistenciaBDClientes.borrarTodosLosDatosDeNumerosAmigos();
		Assert.assertNotNull(persistenciaBDClientes.conexionBD);
	}
	/*
	@Test(
		    expectedExceptions = Exception.class
		)
	void crearTablaError() throws SQLException {
		PersistenciaBDClientes persistenciaBDClientes = new PersistenciaBDClientes();
		persistenciaBDClientes.crearTabla();
		ConnectionDB connectionDB = new ConnectionDB();
		persistenciaBDClientes.conexionBD = DriverManager.getConnection(connectionDB.getConnection("Users.txt"));
		persistenciaBDClientes.conexionBD.createStatement();
		DatabaseMetaData dbm = persistenciaBDClientes.conexionBD.getMetaData();
		ResultSet tables = dbm.getTables(null, null, null, null);
		Assert.assertNotNull(tables.next());
		persistenciaBDClientes.conexionBD.close();
	}*/
}
