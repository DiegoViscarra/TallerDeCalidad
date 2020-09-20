import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import entidades.CDR;
import entidades.PlanWow;


class PlanWowTest {
	
	@Test
	void calcularCostoDeUnaLlamadaTestPertenecienteASusNumerosAmigos() {
		ArrayList<Integer> numerosAuxiliares = new ArrayList<Integer>();
		numerosAuxiliares.add(2);
		numerosAuxiliares.add(3);
		numerosAuxiliares.add(4);
		numerosAuxiliares.add(5);
		PlanWow tester = new PlanWow(numerosAuxiliares);
		CDR registro = new CDR(1,2,"02:45", "sadf", "12:00");
		assertEquals(0,tester.calcularCostoDeUnaLlamada(registro));
		CDR registro2 = new CDR(1,7,"02:45", "sadf", "12:00");
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
		CDR registro = new CDR(1,7,"02:45", "sadf", "12:00");
		assertEquals(0.99*2.75,tester.calcularCostoDeUnaLlamada(registro));
	}

}
