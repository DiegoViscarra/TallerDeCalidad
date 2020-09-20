import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import entidades.CDR;
import entidades.PlanPrepago;

class PlanPrepagoTest {

	private final PlanPrepago tester = new PlanPrepago();
	private final CDR registroHorarioNormal = new CDR(1,2,"02:45", "sadf", "12:00");
	private final CDR registroHorarioReducido = new CDR(1,2,"02:45", "sadf", "00:01");
	private final CDR registroHorarioSuperReducido = new CDR(1,2,"02:45", "sadf", "04:00");
	@Test
	void calcularCostoLlamadaHorarioNormalTest() {
		assertEquals(2.75*1.45,tester.calcularCostoDeUnaLlamada(registroHorarioNormal));
	}
	
	@Test
	void calcularCostoDeLlamadaHorarioReducidoTest() {
		assertEquals(2.75*0.95,tester.calcularCostoDeUnaLlamada(registroHorarioReducido));
	}
	
	@Test
	void calcularCostoDeLlamadaHorarioSuperReducidoTest() {
		assertEquals(2.75*0.70,tester.calcularCostoDeUnaLlamada(registroHorarioSuperReducido));
	}

}
