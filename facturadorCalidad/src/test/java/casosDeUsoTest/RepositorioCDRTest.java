package casosDeUsoTest;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import entidades.CDR;
import repositorios.RepositorioCDR;

public class RepositorioCDRTest {

	/*Prueba métodos registrarCDRs y obtenerRegistrosNoTarificados*/
	@Test
	  void registrarCDRyObtnerLosRegistrosNoTarificadosTest() {
			RepositorioCDR repositorio = new RepositorioCDR();
			repositorio.registrarCDRs("1;2;02:45;3/1/2020;12:00\r\n");
			CDR registro = new CDR(1,2,"02:45", "3/1/2020", "12:00");
			ArrayList<CDR> registros = new ArrayList<CDR>();
			registros.add(registro);
			Assert.assertEquals(registros.size(),repositorio.obtenerRegistrosNoTarificados().size());
			Assert.assertEquals(registros.get(0).getFecha(),repositorio.obtenerRegistrosNoTarificados().get(0).getFecha());
			Assert.assertEquals(registros.get(0).getNumeroTelefonoDestino(),repositorio.obtenerRegistrosNoTarificados().get(0).getNumeroTelefonoDestino());
		}
	
	/*Prueba métodos registrarCDRs y vaciarRegistros*/
	@Test
	  void registrarCDRyVaciarLosRegistrosTest() {
			RepositorioCDR repositorio = new RepositorioCDR();
			repositorio.registrarCDRs("1;2;02:45;3/1/2020;12:00\r\n");
			ArrayList<CDR> registros = new ArrayList<CDR>();
			repositorio.vaciarRegistros();
			Assert.assertEquals(registros.size(),repositorio.obtenerRegistrosNoTarificados().size());
		}
	
}
