/*import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import casosDeUso.*;
import entidades.CDR;
import entidades.Cliente;
import entidades.PlanPostpago;
import entidades.PlanPrepago;
import entidades.PlanWow;
import repositorios.PersistenciaArchivos;
import repositorios.PersistenciaBD;
import repositorios.RepositorioCDR;
import repositorios.RepositorioCliente;
*/
class CentralTest {

	//private final CentralTelefonica tester = new CentralTelefonica(new RepositorioCliente(), new RepositorioCDR(), new PersistenciaArchivos(), new PersistenciaBD());
	//@Test
	/*void buscarClienteWowTest() {
		Cliente c = new Cliente("a", "123", 1);
		Cliente c1 = c;
		ArrayList<Integer> numerosAuxiliares = new ArrayList<Integer>();
		numerosAuxiliares.add(2);
		numerosAuxiliares.add(3);
		numerosAuxiliares.add(4);
		numerosAuxiliares.add(5); 
		c1.setPlan(new PlanWow(numerosAuxiliares));
		tester.registrarNuevoClientePlanNumerosAmigos(c, "WOW", numerosAuxiliares);
		assertEquals(c1,tester.buscarCliente(1));
	}*/
	
	/*@Test
	void buscarClientePostpagoTest() {
		Cliente c = new Cliente("b", "456", 2);
		Cliente c1 = c;
		c1.setPlan(new PlanPostpago());
		tester.registrarNuevoClientePlanNormal(c, "POSTPAGO");
		assertEquals(c1, tester.buscarCliente(2));
	}*/
	
	/*@Test
	void buscarClientePrepagoTest() {
		Cliente c = new Cliente("c", "789", 3);
		Cliente c1 = c;
		c1.setPlan(new PlanPostpago());
		tester.registrarNuevoClientePlanNormal(c, "PREPAGO");
		assertEquals(c1, tester.buscarCliente(3));
	}*/
	
	
	/*@Test
	void obtenerRegistroTarifadoTest() {
		Cliente c = new Cliente("a", "123", 1);
		c.setPlan(new PlanPrepago());
		
		CDR r = new CDR(1,2,"02:45", "sadf", "12:00");
		assertEquals(2.75*1.45, tester.tarifar(r,c));
	}*/
	
	/*@Test*/
	//void obtenerRegistrosTarifadosTest() {
		//Cliente cliente1 = new Cliente("juanito","789",123);
		//Cliente cliente2 = new Cliente("benito","788",456);
		//Cliente cliente3 = new Cliente("anita","787",789);
		
		//tester.registrarNuevoClientePlanNormal(cliente1,"PREPAGO");
		//tester.registrarNuevoClientePlanNormal(cliente2,"POSTPAGO");
		
		//ArrayList<Integer> numerosAuxiliares = new ArrayList<Integer>();
		/*numerosAuxiliares.add(234);
		numerosAuxiliares.add(345);
		numerosAuxiliares.add(456);
		numerosAuxiliares.add(567);
		
		tester.registrarNuevoClientePlanNumerosAmigos(cliente3,"WOW", numerosAuxiliares);*/
		
		/*CDR registro1 = new CDR(123,456,"02:45", "sadf", "12:00");
		CDR registro2 = new CDR(789,123,"05:25", "sadf", "19:00");
		CDR registro3 = new CDR(456,789,"04:32", "sadf", "21:00");
		CDR registro4 = new CDR(456,123,"09:42", "sadf", "23:00");
		CDR registro5 = new CDR(789,456,"02:11", "sadf", "07:00");*/
		
		/*ArrayList<CDR> registrosTarifados = new ArrayList<CDR>();
		CDR r1,r2,r3,r4,r5;*/
		/*r1 = registro1;
		r1.setCosto(2.75*1.45);
		r2 = registro2;
		r2.setCosto(65/12*0.99);
		r3 = registro3;
		r3.setCosto(68/15*1);
		r4 = registro4;
		r4.setCosto(9.7*1);
		r5 = registro5;
		r5.setCosto(0);
		
		registrosTarifados.add(r1);
		registrosTarifados.add(r2);
		registrosTarifados.add(r3);
		registrosTarifados.add(r4);
		registrosTarifados.add(r5);
		
		
		tester.incluirNuevoRegistro(registro1);
		tester.incluirNuevoRegistro(registro2);
		tester.incluirNuevoRegistro(registro3);
		tester.incluirNuevoRegistro(registro4);
		tester.incluirNuevoRegistro(registro5);
		
		tester.tarificarRegistros();*/
		
			
}


