package repositoriosTest;

import java.util.ArrayList;

import org.javatuples.Pair;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import entidades.CDR;
import junit.framework.Assert;
import repositorios.PersistenciaArchivos;

public class PersistenciaArchivosTest {
	public PersistenciaArchivos persistencia = null;
	public ArrayList<CDR> listaRegistros = null;
	
	@BeforeMethod
	public void initSerializarArchivos() {
		persistencia = new PersistenciaArchivos();
		listaRegistros = new ArrayList<CDR>();
		CDR uno = new CDR(123, 345, "02:45", "11/10/2020", "23:00");
		uno.setCosto(2.75);
		listaRegistros.add(uno);
	}
	
	@Test
	public void serializarTest() {
		String nombreArchivo = persistencia.serializar(listaRegistros);
		nombreArchivo = nombreArchivo + ".txt";
		ArrayList<CDR> deserializado = persistencia.deserializar(nombreArchivo);
		
		Assert.assertEquals(listaRegistros.get(0).getNumeroTelefonoOrigen(), deserializado.get(0).getNumeroTelefonoOrigen());
		Assert.assertEquals(listaRegistros.get(0).getNumeroTelefonoDestino(), deserializado.get(0).getNumeroTelefonoDestino());
		Assert.assertEquals(listaRegistros.get(0).getFecha(), deserializado.get(0).getFecha());
		Assert.assertEquals(listaRegistros.get(0).getHora(), deserializado.get(0).getHora());
		Assert.assertEquals(listaRegistros.get(0).getDuracionLLamada(), deserializado.get(0).getDuracionLLamada());
		Assert.assertEquals(listaRegistros.get(0).getCostoDeLlamada(), deserializado.get(0).getCostoDeLlamada());
	}
}
