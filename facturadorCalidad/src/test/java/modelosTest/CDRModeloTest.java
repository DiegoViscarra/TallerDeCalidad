package modelosTest;

import org.testng.annotations.Test;

import junit.framework.Assert;
import modelos.CDRModelo;

import org.testng.annotations.BeforeMethod;

public class CDRModeloTest {
	CDRModelo cdrModelo;

	@Test
	public void obtenerNumeroTelefonoOrigen() {
		Assert.assertEquals(123, cdrModelo.getNumeroTelefonoOrigen());
	}

	@Test
	public void obtenerNumeroTelefonoDestino() {
		Assert.assertEquals(456, cdrModelo.getNumeroTelefonoDestino());
	}

	@Test
	public void obtenerId() {
		Assert.assertEquals(1, cdrModelo.getId());
	}

	@Test
	public void obtenerCostoDeLlamada() {
		Assert.assertEquals(3.9, cdrModelo.getCostoDeLlamada());
	}

	@Test
	public void obtenerDuracionLlamada() {
		Assert.assertEquals("02:15", cdrModelo.getDuracionLLamada());
	}

	@Test
	public void obtenerFecha() {
		Assert.assertEquals("09/10/2020", cdrModelo.getFecha());
	}

	@Test
	public void obtenerHora() {
		Assert.assertEquals("11:00", cdrModelo.getHora());
	}

	@Test
	public void obtenerFechaTarificacion() {
		Assert.assertEquals("09/11/2020", cdrModelo.getFechaTarificacion());
	}

	@Test
	public void obtenerHoraTarificacion() {
		Assert.assertEquals("12:00", cdrModelo.getHoraTarificacion());
	}

	@BeforeMethod
	public void beforeMethod() {
		cdrModelo = new CDRModelo(1);
		cdrModelo.setDatosBasicosCDR(123, 456);
		cdrModelo.setDatosAvanzadosCDR("02:15", "09/10/2020", "11:00", "09/11/2020", 3.9, "12:00");
	}
}
