package casosDeUsoTest;

import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import casosDeUso.IPersistenciaArchivos;
import casosDeUso.IPersistenciaBDCDR;
import casosDeUso.IPersistenciaBDClientes;
import casosDeUso.IRepositorioCDR;
import casosDeUso.Persistencia;
import entidades.CDR;
import modelos.CDRModelo;
import repositorios.PersistenciaArchivos;
import repositorios.PersistenciaBDCDR;
import repositorios.PersistenciaBDClientes;
import repositorios.RepositorioCDR;

public class PersistenciaTest {
	
	/*Prueba método calcularCosto, caso por default
	  @Test
	  void asignarCostoYConseguirCostoDeLlamadaCasoDefaultTest() {
		    Persistencia persistencia = new Persistencia(null, null, null, null);
		    CDR registro = new CDR(1,2,"02:45", "3/1/2020", "12:00");
		    ArrayList<CDR> registrosTelefonicos = new ArrayList<CDR>();
		    registrosTelefonicos.add(registro);
			//Assert.assertEquals("Ninguno",persistencia.persistirEnBDCdr(registrosTelefonicos));
		}
	*/
	public Persistencia persistencia;
	public IPersistenciaBDCDR persistenciaBDCDR;
	public IPersistenciaBDClientes persistenciaBDClientes;
	public IPersistenciaArchivos persistenciaArchivos;
	public IRepositorioCDR repositorioCDR;
	public ArrayList<CDRModelo> registros, filtrados;
	@BeforeClass
	public void initPersistencia() {
		persistenciaBDCDR = new PersistenciaBDCDR();
		persistenciaBDClientes = new PersistenciaBDClientes();
		persistenciaArchivos = new PersistenciaArchivos();
		repositorioCDR = new RepositorioCDR();
		
		persistencia = new Persistencia(persistenciaBDCDR, persistenciaBDClientes, persistenciaArchivos, repositorioCDR);
	}
	
	@Test
	public void esNumeroInvalidoDeMesTest() {
		Assert.assertTrue(persistencia.esNumeroDeMesInvalido(13));
		Assert.assertTrue(persistencia.esNumeroDeMesInvalido(0));
		Assert.assertFalse(persistencia.esNumeroDeMesInvalido(5));
	}
	
	@Test
	public void obtenerMesLiteralTest() {
		Assert.assertEquals("Enero", persistencia.obtenerMesLiteral(1));
		Assert.assertEquals("Mes no valido", persistencia.obtenerMesLiteral(90));
	}
	
	@Test
	public void coincidenEnMesTest() {
		String[] fecha = {"11", "1","2020"};
		Assert.assertTrue(persistencia.coincidenEnMes("1", fecha));
		Assert.assertFalse(persistencia.coincidenEnMes("2", fecha));
	}
	
	@BeforeMethod
	public void initFiltroRegistros() {
		registros = new ArrayList<CDRModelo>();
		CDRModelo uno = new CDRModelo(1);
				uno.setDatosBasicosCDR(123, 345);
				uno.setDatosAvanzadosCDR( "02:45", "11/10/2020", "23:00", "11/11/2011", 2.75, "2:00");
		CDRModelo dos = new CDRModelo(1);
				dos.setDatosBasicosCDR(123, 345);
				dos.setDatosAvanzadosCDR( "01:45", "11/11/2020", "23:00", "11/11/2011", 1.75, "2:00");
		CDRModelo tres = new CDRModelo(1);
				tres.setDatosBasicosCDR(123, 345);
				tres.setDatosAvanzadosCDR( "03:45", "11/11/2020", "23:00", "11/11/2011", 3.75, "2:00");

		registros.add(uno);
		registros.add(dos);
		registros.add(tres);
		
		filtrados = new ArrayList<CDRModelo>();
		filtrados.add(dos);
		filtrados.add(tres);
	}
	
	@Test
	public void filtrarRegistrosPorMesTest() {
		Assert.assertEquals(filtrados, persistencia.filtrarRegistrosPorMes("11", registros));
	}
	
	@Test
	public void sumarTotalDelMesTest() {
		Assert.assertEquals(5.5, persistencia.obtenerSumaTotalDeRegistrosDelMes(filtrados));
	}
	
	
}
