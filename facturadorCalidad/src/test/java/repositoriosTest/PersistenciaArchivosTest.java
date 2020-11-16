package repositoriosTest;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.javatuples.Pair;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import entidades.CDR;

import repositorios.PersistenciaArchivos;

public class PersistenciaArchivosTest {
	public PersistenciaArchivos persistencia = null;
	public ArrayList<CDR> listaRegistros, listaAuxiliar1, listaAuxiliar2 = null;
	
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
	
	@BeforeMethod
	public void initDeserializarTodosLosArchivos() {
		listaAuxiliar1 = new ArrayList<CDR>();
		CDR aux = new CDR(345, 345, "05:15", "11/10/2020", "23:00");
		aux.setCosto(5.25);
		listaAuxiliar1.add(aux);
		listaAuxiliar2 = new ArrayList<CDR>();
		CDR aux2 = new CDR(687, 345, "03:21", "11/10/2020", "23:00");
		aux.setCosto(3.35);
		listaAuxiliar2.add(aux2);	
	}
	
	
	@Test
	public void deserializarTodosLosArchivosTest() {
		String archivo1 = persistencia.serializar(listaAuxiliar1);
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String archivo2 = persistencia.serializar(listaAuxiliar2);
		archivo1 = archivo1 + ".txt";
		archivo2 = archivo2 + ".txt";
		ArrayList<Pair<String,ArrayList<CDR>>> deserializable = persistencia.deserializarTodosLosArchivos();
		ArrayList<CDR> rest1 = new ArrayList<CDR>();
		ArrayList<CDR> rest2 = new ArrayList<CDR>();
		for(Pair<String,ArrayList<CDR>> par : deserializable) {
			System.out.print(par);
			if(par.getValue0().contentEquals(archivo1)) {
				rest1 = par.getValue1();
			}
		}
		
		for(Pair<String,ArrayList<CDR>> par : deserializable) {
			System.out.print(par);
			if(par.getValue0().contentEquals(archivo2)) {
				rest2 = par.getValue1();
			}
		}
		
		Assert.assertEquals(rest1.get(0).getNumeroTelefonoOrigen(), listaAuxiliar1.get(0).getNumeroTelefonoOrigen());
		Assert.assertEquals(rest1.get(0).getNumeroTelefonoDestino(), listaAuxiliar1.get(0).getNumeroTelefonoDestino());
		Assert.assertEquals(rest1.get(0).getDuracionLLamada(), listaAuxiliar1.get(0).getDuracionLLamada());
		
		Assert.assertEquals(rest2.get(0).getNumeroTelefonoOrigen(), listaAuxiliar2.get(0).getNumeroTelefonoOrigen());
		Assert.assertEquals(rest2.get(0).getNumeroTelefonoDestino(), listaAuxiliar2.get(0).getNumeroTelefonoDestino());
		Assert.assertEquals(rest2.get(0).getDuracionLLamada(), listaAuxiliar2.get(0).getDuracionLLamada());
		
		
	}
}
