package repositoriosTest;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import entidades.CDR;
import entidades.PlanPrepago;
import repositorios.RepositorioCDR;

public class RepositorioCDRTest {
	RepositorioCDR repositorio;
	ArrayList<CDR> registros;
	@BeforeMethod
	public void beforeMethod() {
		repositorio = new RepositorioCDR();
		repositorio.registrarCDRs("1;2;02:45;3/1/2020;12:00\r\n");
		registros = new ArrayList<CDR>();
	}
	/*Prueba métodos registrarCDRs y obtenerRegistrosNoTarificados*/
	@Test
	  void registrarCDRyObtnerLosRegistrosNoTarificadosTest() {
			CDR registro = new CDR(1,2,"02:45", "3/1/2020", "12:00");
			registros.add(registro);
			Assert.assertEquals(registros.size(),repositorio.obtenerRegistrosNoTarificados().size());
			Assert.assertEquals(registros.get(0).getFecha(),repositorio.obtenerRegistrosNoTarificados().get(0).getFecha());
			Assert.assertEquals(registros.get(0).getNumeroTelefonoDestino(),repositorio.obtenerRegistrosNoTarificados().get(0).getNumeroTelefonoDestino());
		}
	
	/*Prueba métodos registrarCDRs y vaciarRegistros*/
	@Test
	  void registrarCDRyVaciarLosRegistrosTest() {
			repositorio.vaciarRegistros();
			Assert.assertEquals(registros.size(),repositorio.obtenerRegistrosNoTarificados().size());
		}
	
}
