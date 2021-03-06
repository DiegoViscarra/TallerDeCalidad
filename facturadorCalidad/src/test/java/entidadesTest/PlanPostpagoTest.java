package entidadesTest;

import org.testng.annotations.Test;

import entidades.CDR;
import entidades.PlanPostpago;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

public class PlanPostpagoTest {
	PlanPostpago planPostPago;

	@Test
	public void obtenerTipoDeTarifa() {
		String tarifaEsperada = "POSTPAGO";
		String tarifaObtenida = planPostPago.obtenerTipoTarifa();
		Assert.assertEquals(tarifaEsperada, tarifaObtenida);
	}

	@Test
	public void calcularCostoDeUnaLlamada() {
		double costoEsperado = 9.7;
		CDR registro = new CDR(456, 123, "09:42", "14/2/2020", "23:00");
		double costoObtenido = planPostPago.calcularCostoDeUnaLlamada(registro);
		Assert.assertEquals(costoEsperado, costoObtenido);
	}

	@BeforeMethod
	public void beforeMethod() {
		planPostPago = new PlanPostpago();
	}
}
