package entidadesTest;



import org.testng.Assert;
import org.testng.annotations.Test;

import entidades.Cliente;
import entidades.PlanPrepago;

public class ClienteTest {
	
	/*Prueba métodos setNumeroTelefonico y getNumeroTelefonico*/
	@Test
	  void asignarNumeroTelefonicoYConseguirNumeroTelefonicoTest() {
			Cliente registro = new Cliente("Juan", "213", 1);
			registro.setNumeroTelefonico(2);
			Assert.assertEquals(2,registro.getNumeroTelefonico());
		}
	
	/*Prueba método getCi*/
	@Test
	  void conseguirCiTest() {
			Cliente registro = new Cliente("Juan", "213", 1);
			Assert.assertEquals("213",registro.getCi());
		}
	
	/*Prueba método getNombre*/
	@Test
	  void conseguirNombreTest() {
			Cliente registro = new Cliente("Juan", "213", 1);
			Assert.assertEquals("Juan",registro.getNombre());
		}
	
	/*Prueba métodos setPlan y getPlan*/
	@Test
	  void asignarPlanYConseguirPlanTest() {
		    PlanPrepago plan = new PlanPrepago();
			Cliente registro = new Cliente("Juan", "213", 1);
			registro.setPlan(plan);
			Assert.assertEquals(plan,registro.getPlan());
		}
	
	/*Prueba métodos setTipoPlan y getTipoPlan*/
	@Test
	  void asignarTipoDePlanYConseguirTipoDePlanTest() {
			Cliente registro = new Cliente("Juan", "213", 1);
			registro.setTipoPlan("PlanPostpago");;
			Assert.assertEquals("PlanPostpago",registro.getTipoPlan());
		}
}
