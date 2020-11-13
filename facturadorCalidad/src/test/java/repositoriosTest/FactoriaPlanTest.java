package repositoriosTest;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import casosDeUso.IPlan;
import repositorios.FactoriaPlan;

class FactoriaPlanTest {
	FactoriaPlan factoriaPlan;
	IPlan IPlan;
	ArrayList<Integer> numerosAmigos;
	@Test
	void getPlanPrepago() {
		IPlan = factoriaPlan.getPlan("PREPAGO");
		Assert.assertEquals(IPlan.obtenerTipoTarifa(), "PREPAGO");
	}

	@Test
	void getPlanPostpago() {
		IPlan = factoriaPlan.getPlan("POSTPAGO");
		Assert.assertEquals(IPlan.obtenerTipoTarifa(), "POSTPAGO");
	}

	@Test
	void getPlanWOW() {
		
		IPlan = factoriaPlan.getPlan("WOW", numerosAmigos);
		Assert.assertEquals(IPlan.obtenerTipoTarifa(), "WOW");
	}

	@Test
	void getPlanNull() {
		IPlan = factoriaPlan.getPlan("Error");
		Assert.assertEquals(IPlan, null);
	}

	@Test
	void getPlanNull2() {
		IPlan = factoriaPlan.getPlan("Error",numerosAmigos);
		Assert.assertEquals(IPlan, null);
	}
	
	@BeforeMethod
	public void beforeMethod() {
		factoriaPlan = new FactoriaPlan();
		numerosAmigos = null;
	}

}
