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

	@Test
	  void asignarNumeroTelefonicoYConseguirNumeroTelefonicoTest() {
			registro.setNumeroTelefonico(2);
			Assert.assertEquals(2,registro.getNumeroTelefonico());
		}
	
	@Test
	  void conseguirCiTest() {
			Assert.assertEquals("213",registro.getCi());
		}
	
	@Test
	  void conseguirNombreTest() {
			Assert.assertEquals("Juan",registro.getNombre());
		}
	
	@Test
	  void asignarPlanYConseguirPlanTest() {
		    PlanPrepago plan = new PlanPrepago();
			registro.setPlan(plan);
			Assert.assertEquals(plan,registro.getPlan());
		}
	
	@Test
	  void asignarTipoDePlanYConseguirTipoDePlanTest() {
			registro.setTipoPlan("PlanPostpago");;
			Assert.assertEquals("PlanPostpago",registro.getTipoPlan());
		}
}
