

import org.testng.Assert;
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
	
  /*Prueba métodos setCosto y getCostoDeLlamada*/
  @Test
  void asignarCostoYConseguirCostoDeLlamadaTest() {
		CDR registro = new CDR(1,2,"02:45", "3/1/2020", "12:00");
		registro.setCosto(1.25);
		Assert.assertEquals(1.25,registro.getCostoDeLlamada());
	}
  
  /*Prueba método getNumeroTelefonoOrigen*/
  @Test
  void conseguirNumeroDeTelefonoDeOrigenTest() {
		CDR registro = new CDR(1,2,"02:45", "3/1/2020", "12:00");
		registro.setCosto(1.25);
		Assert.assertEquals(1,registro.getNumeroTelefonoOrigen());
	}
  
  /*Prueba método getNumeroTelefonoDestino*/
  @Test
  void conseguirNumeroDeTelefonoDeDestinoTest() {
		CDR registro = new CDR(1,2,"02:45", "3/1/2020", "12:00");
		registro.setCosto(1.25);
		Assert.assertEquals(2,registro.getNumeroTelefonoDestino());
	}
  
  /*Prueba método convertirMinutosADecimal*/
  @Test
  void conversiónDeTiempoDeLlamadaADecimalTest() {
		CDR registro = new CDR(1,2,"02:45", "3/1/2020", "12:00");
		Assert.assertEquals(2.75,registro.convertirMinutosADecimal());
	}
  
  /*Prueba método getHora*/
  @Test
  void conseguirHoraDeLlamadaTest() {
		CDR registro = new CDR(1,2,"02:45", "3/1/2020", "12:00");
		Assert.assertEquals("12:00",registro.getHora());
	}
  
  /*Prueba método getFecha*/
  @Test
  void conseguirFechaDeLlamadaTest() {
		CDR registro = new CDR(1,2,"02:45", "3/1/2020", "12:00");
		registro.setCosto(1.25);
		Assert.assertEquals("3/1/2020",registro.getFecha());
	}
  
  /*Prueba método getDuracionLLamada*/
  @Test
  void conseguirDuracionDeLlamadaTest() {
		CDR registro = new CDR(1,2,"02:45", "3/1/2020", "12:00");
		registro.setCosto(1.25);
		Assert.assertEquals("02:45",registro.getDuracionLLamada());
	}
  
  /*Prueba método calcularCostoDeLlamada*/
  @Test
  void calcularElCostoDeLlamadaTest() {
		FactoriaPlan factoria = new FactoriaPlan();
		IPlan plan = factoria.getPlan("PREPAGO");
		CDR registro = new CDR(1,2,"02:45", "3/1/2020", "12:00");
		Assert.assertEquals(3.9875, registro.calcularCostoDeLlamada(plan));
	}
  
}
