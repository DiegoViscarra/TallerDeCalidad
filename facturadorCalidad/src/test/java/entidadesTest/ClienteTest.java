package entidadesTest;



import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import entidades.CDR;
import entidades.Cliente;
import entidades.PlanPrepago;

public class ClienteTest { 
	Cliente registro;
	@BeforeMethod
	public void beforeMethod() {
		registro = new Cliente("Juan", "213", 1);
	}
	/*Prueba métodos setNumeroTelefonico y getNumeroTelefonico*/
	@Test
	  void asignarNumeroTelefonicoYConseguirNumeroTelefonicoTest() {
			registro.setNumeroTelefonico(2);
			Assert.assertEquals(2,registro.getNumeroTelefonico());
		}
	
	/*Prueba método getCi*/
	@Test
	  void conseguirCiTest() {
			Assert.assertEquals("213",registro.getCi());
		}
	
	/*Prueba método getNombre*/
	@Test
	  void conseguirNombreTest() {
			Assert.assertEquals("Juan",registro.getNombre());
		}
	
	/*Prueba métodos setPlan y getPlan*/
	@Test
	  void asignarPlanYConseguirPlanTest() {
		    PlanPrepago plan = new PlanPrepago();
			registro.setPlan(plan);
			Assert.assertEquals(plan,registro.getPlan());
		}
	
	/*Prueba métodos setTipoPlan y getTipoPlan*/
	@Test
	  void asignarTipoDePlanYConseguirTipoDePlanTest() {
			registro.setTipoPlan("PlanPostpago");;
			Assert.assertEquals("PlanPostpago",registro.getTipoPlan());
		}
}
