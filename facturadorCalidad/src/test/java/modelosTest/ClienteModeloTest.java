package modelosTest;

import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import entidades.CDR;
import modelos.ClienteModelo;

public class ClienteModeloTest {
	ArrayList<Integer> numerosAmigos;
	ClienteModelo registroAmigos;
	ClienteModelo registro;
	@BeforeMethod
	public void beforeMethod() {
		registroAmigos = new ClienteModelo(1, "3/1/2020", numerosAmigos);
		registro = new ClienteModelo("Juan", "213", 1, "PlanWow", "3/1/2020");
	}
	@BeforeClass
	public void beforeClass() {
		numerosAmigos = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
	}

	@Test
	  void conseguirNumeroTelefonicoTest() {
			Assert.assertEquals(1,registro.getNumeroTelefonico());
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
	  void conseguirTipoDePlanTest() {
			Assert.assertEquals("PlanWow",registro.getTipoPlan());
		}
	
	@Test
	  void conseguirFechaDeRegistroTest() {
			Assert.assertEquals("3/1/2020",registro.getFechaRegistro());
		}
	
	@Test
	  void conseguirNumerosDeAmigosTest() {
			Assert.assertEquals(numerosAmigos,registroAmigos.getNumerosAmigos());
		}
}
