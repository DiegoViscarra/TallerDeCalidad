package repositoriosTest;

import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import entidades.CDR;
import modelos.CDRModelo;
import repositorios.ConnectionDB;
import repositorios.PersistenciaBDCDR;


class PersistenciaBDCDRTest {
	PersistenciaBDCDR persistenciaBDCDR;
	@Test 
	void crearTabla() throws SQLException {
		persistenciaBDCDR.crearTabla();
		ConnectionDB connectionDB = new ConnectionDB();
		persistenciaBDCDR.conexionBD = DriverManager.getConnection(connectionDB.getConnection("Users.txt"));
		persistenciaBDCDR.conexionBD.createStatement();
		DatabaseMetaData dbm = persistenciaBDCDR.conexionBD.getMetaData();
		ResultSet tables = dbm.getTables(null, null, "CDR", null);
		Assert.assertNotNull(tables.next());
		persistenciaBDCDR.conexionBD.close();
	}

	@Test
	void poblarTabla() {
		CDR registro = new CDR(1,2,"02:45", "sadf", "12:00"); ;
		persistenciaBDCDR.poblarTabla(registro);
		Assert.assertNotNull(persistenciaBDCDR.conexionBD);
	}

	@Test
	void mostrarTabla() throws SQLException {
		ConnectionDB connectionDB = new ConnectionDB();
		persistenciaBDCDR.conexionBD = DriverManager.getConnection(connectionDB.getConnection("Users.txt"));
		persistenciaBDCDR.conexionBD.createStatement();
		ArrayList<CDRModelo> resultado= persistenciaBDCDR.mostrarTabla("SELECT * FROM CDR;");
		Assert.assertNotNull(resultado);
	}
	
	@Test
	void borrarDatos()  {
		persistenciaBDCDR.borrarTodosLosDatosDeCDR();
		Assert.assertNotNull(persistenciaBDCDR.conexionBD);
	}
	@BeforeMethod
	public void beforeMethod() {
		persistenciaBDCDR = new PersistenciaBDCDR();
	}

}
