package entidadesTest;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import casosDeUso.IPlan;
import casosDeUso.Tarificacion;
import entidades.CDR;
import entidades.Cliente;
import entidades.PlanPostpago;
import repositorios.FactoriaPlan;
import repositorios.PersistenciaArchivos;
import repositorios.PersistenciaBDCDR;
import repositorios.PersistenciaBDClientes;
import repositorios.RepositorioCDR;
import repositorios.RepositorioCliente;

public class CDRTest {
	CDR registro;
	@BeforeMethod
	public void beforeMethod() {
		registro = new CDR(1,2,"02:45", "3/1/2020", "12:00");
	}

  @Test
  void asignarCostoYConseguirCostoDeLlamadaTest() {
		registro.setCosto(1.25);
		Assert.assertEquals(1.25,registro.getCostoDeLlamada());
	}
  
  @Test
  void conseguirNumeroDeTelefonoDeOrigenTest() {
		Assert.assertEquals(1,registro.getNumeroTelefonoOrigen());
	}
  
  @Test
  void conseguirNumeroDeTelefonoDeDestinoTest() {
		Assert.assertEquals(2,registro.getNumeroTelefonoDestino());
	}
  
  @Test
  void conversiónDeTiempoDeLlamadaADecimalTest() {
		Assert.assertEquals(2.75,registro.convertirMinutosADecimal());
	}
  
  @Test
  void conseguirHoraDeLlamadaTest() {
		Assert.assertEquals("12:00",registro.getHora());
	}
  
  @Test
  void conseguirFechaDeLlamadaTest() {
		Assert.assertEquals("3/1/2020",registro.getFecha());
	}
  
  @Test
  void conseguirDuracionDeLlamadaTest() {
		Assert.assertEquals("02:45",registro.getDuracionLLamada());
	}
  
  @Test
  void calcularElCostoDeLlamadaTest() {
		FactoriaPlan factoria = new FactoriaPlan();
		IPlan plan = factoria.getPlan("PREPAGO");
		Assert.assertEquals(3.9875, registro.calcularCostoDeLlamada(plan));
	}
}
