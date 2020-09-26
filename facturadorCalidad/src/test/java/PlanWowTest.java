import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import entidades.CDR;
import entidades.PlanWow;


class PlanWowTest {
	
	private static final String TESTINGDURATION = "12:00";
	private static final String TESTINGHOUR = "02:45";

	@Test
	void calcularCostoDeUnaLlamadaTestPertenecienteASusNumerosAmigos() {
		ArrayList<Integer> numerosAuxiliares = new ArrayList<Integer>();
		numerosAuxiliares.add(2);
		numerosAuxiliares.add(3);
		numerosAuxiliares.add(4);
		numerosAuxiliares.add(5);
		PlanWow tester = new PlanWow(numerosAuxiliares);
		CDR registro = new CDR(1,2,TESTINGHOUR, "sadf", TESTINGDURATION);
		assertEquals(0,tester.calcularCostoDeUnaLlamada(registro));
		CDR registro2 = new CDR(1,7,TESTINGHOUR, "sadf", TESTINGDURATION);
		assertEquals(0.99*2.75,tester.calcularCostoDeUnaLlamada(registro2));
	}
	
	@Test
	void calcularCostoDeUnaLlamadaTestNoPertenecienteASusNumerosAmigos() {
		ArrayList<Integer> numerosAuxiliares = new ArrayList<Integer>();
		numerosAuxiliares.add(2);
		numerosAuxiliares.add(3);
		numerosAuxiliares.add(4);
		numerosAuxiliares.add(5);
		PlanWow tester = new PlanWow(numerosAuxiliares);
		CDR registro = new CDR(1,7,TESTINGHOUR, "sadf", TESTINGDURATION);
		assertEquals(0.99*2.75,tester.calcularCostoDeUnaLlamada(registro));
	}

}
