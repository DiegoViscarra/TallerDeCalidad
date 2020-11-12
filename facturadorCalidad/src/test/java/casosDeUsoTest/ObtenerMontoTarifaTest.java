package casosDeUsoTest;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import casosDeUso.ObtenerMontoTarifa;
import entidades.CDR;
import junit.framework.Assert;

public class ObtenerMontoTarifaTest {
	public ObtenerMontoTarifa tester;
	public CDR registroTest, registroTestPrepagoNormal, registroTestPrepagoReducido, registroTestPrepagoSuperReducido;
	@BeforeMethod
	public void init() {
		registroTest = new CDR(123, 345, "02:45", "01/01/2000", "12:00");
		tester = new ObtenerMontoTarifa();
	}
	
	@Test 
	public void obtenerMontoTarifaWow() {
		Assert.assertEquals(0.99, tester.obtenerMontoTarifa("WOW", registroTest));
	}
	
	@Test
	public void obtenerMontoTarifaPostpago() {
		Assert.assertEquals(1.0, tester.obtenerMontoTarifa("POSTPAGO", registroTest));
	}
	
	@BeforeMethod
	public void initTarifaPrepagoNormal() {
		registroTestPrepagoNormal = new CDR(123, 345, "02:45", "01/01/2000", "7:00");
	}
	
	@Test
	public void obtenerMontoTarifaPrepagoNormal() {
		Assert.assertEquals(1.45, tester.obtenerMontoTarifa("PREPAGO", registroTestPrepagoNormal));
	}
	
	@BeforeMethod
	public void initTarifaPrepagoReducida() {
		registroTestPrepagoReducido = new CDR(123, 345, "02:45", "01/01/2000", "21:00");
	}
	
	@Test
	public void obtenerMontoTarifaPrepagoReducida() {
		Assert.assertEquals(0.95, tester.obtenerMontoTarifa("PREPAGO", registroTestPrepagoReducido));
	}
	
	@BeforeMethod
	public void initTarifaPrepagoSuperReducida() {
		registroTestPrepagoSuperReducido = new CDR(123, 345, "02:45", "01/01/2000", "2:00");
	}
	
	@Test
	public void obtenerMontoTarifaPrepagoSuperReducida() {
		Assert.assertEquals(0.70, tester.obtenerMontoTarifa("PREPAGO", registroTestPrepagoSuperReducido));
	}
	
	@Test
	public void obtenerMontoTarifaPlanNoRegistrado() {
		Assert.assertEquals(0.0, tester.obtenerMontoTarifa("EMERGENCIA", registroTestPrepagoSuperReducido));
	}
	
	
	

}
