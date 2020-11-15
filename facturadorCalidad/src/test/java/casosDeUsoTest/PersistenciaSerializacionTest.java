package casosDeUsoTest;

import java.util.ArrayList;

import org.javatuples.Pair;
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

public class PersistenciaSerializacionTest {
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
	
	@BeforeMethod
	public void initRegistros() {
		ArrayList<CDR> registros = new ArrayList<CDR>();
		CDR registro = new CDR(323, 345, "02:45", "11/11/2020", "23:00");
		registro.setCosto(2.75);
		registros.add(registro);
		persistencia.persistirEnArchivo(registros);
	} 
	
	@Test
	public void deserializar() {
		ArrayList<Pair<String,ArrayList<CDR>>> deserializado = persistencia.deserializarArchivos();
		Assert.assertNotNull(deserializado);
	}

}
