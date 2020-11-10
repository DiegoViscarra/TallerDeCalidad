package entidadesTest;

import org.testng.annotations.Test;

import entidades.CDR;
import entidades.PlanPrepago;
import junit.framework.Assert;

import org.testng.annotations.BeforeMethod;

public class PlanPrepagoTest {
	PlanPrepago planPrepago;

	@Test
	public void obtenerTipoDeTarifa() {
		String tarifaEsperada = "PREPAGO";
		String tarifaObtenida = planPrepago.obtenerTipoTarifa();
		Assert.assertEquals(tarifaEsperada, tarifaObtenida);
	}

	@Test
	public void calcularCostoDeUnaLlamada() {
		double costoEsperado = 3.9875;
		CDR registro = new CDR(123, 456, "02:45", "03/1/2020", "12:00");
		double costoObtenido = planPrepago.calcularCostoDeUnaLlamada(registro);
		Assert.assertEquals(costoEsperado, costoObtenido);
	}

	@BeforeMethod
	public void beforeMethod() {
		planPrepago = new PlanPrepago();
	}
}
