import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import entidades.CDR;
import entidades.PlanPostpago;

class PlanPostpagoTest {

	private final PlanPostpago tester = new PlanPostpago();
	private final CDR registro = new CDR(1,2,"02:45", "sadf", "12:00");
	@Test
	void calcularCostoDeUnaLlamadaTest() {
		assertEquals(2.75,tester.calcularCostoDeUnaLlamada(registro));
	}

}
