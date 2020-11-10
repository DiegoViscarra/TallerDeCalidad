package modelosTest;
import modelos.FacturaModelo;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.testng.Assert;

public class FacturaModeloTest {
	public FacturaModelo facturaTester;
	
	@BeforeMethod
	public void init() {
		facturaTester = new FacturaModelo(1234567, "Septiembre", 1200);
	}
	
	@Test
	public void numeroTelefonicoTest() {
		Assert.assertEquals(Integer.valueOf(1234567), facturaTester.getNumeroTelefonico());
	}
	
	@Test
	public void mesTest() {
		Assert.assertEquals("Septiembre", facturaTester.mes());
	}
	
	@Test
	public void montoMesTest() {
		Assert.assertEquals(1200, facturaTester.montoMes());
	}

}
