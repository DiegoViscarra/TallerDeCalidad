package testconNg;

import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import modelos.ClienteModelo;

public class ClienteModeloTest {
	
	/*Prueba método getNumeroTelefonico*/
	@Test
	  void conseguirNumeroTelefonicoTest() {
			ArrayList<Integer> numerosAmigos = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
			ClienteModelo registro = new ClienteModelo(1, "3/1/2020", numerosAmigos);
			Assert.assertEquals(1,registro.getNumeroTelefonico());
		}

	/*Prueba método getCi*/
	@Test
	  void conseguirCiTest() {
			ClienteModelo registro = new ClienteModelo("Juan", "213", 1, "PlanWow", "3/1/2020");
			Assert.assertEquals("213",registro.getCi());
		}
	
	/*Prueba método getNombre*/
	@Test
	  void conseguirNombreTest() {
			ClienteModelo registro = new ClienteModelo("Juan", "213", 1, "PlanWow", "3/1/2020");
			Assert.assertEquals("Juan",registro.getNombre());
		}
	
	/*Prueba método getTipoPlan*/
	@Test
	  void conseguirTipoDePlanTest() {
		ClienteModelo registro = new ClienteModelo("Juan", "213", 1, "PlanWow", "3/1/2020");
		Assert.assertEquals("PlanWow",registro.getTipoPlan());
		}
	
	/*Prueba método getFechaRegistro*/
	@Test
	  void conseguirFechaDeRegistroTest() {
		ClienteModelo registro = new ClienteModelo("Juan", "213", 1, "PlanWow", "3/1/2020");
		Assert.assertEquals("3/1/2020",registro.getFechaRegistro());
		}
	
	/*Prueba método getNumerosAmigos*/
	@Test
	  void conseguirNumerosDeAmigosTest() {
			ArrayList<Integer> numerosAmigos = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
			ClienteModelo registro = new ClienteModelo(1, "3/1/2020", numerosAmigos);
			Assert.assertEquals(numerosAmigos,registro.getNumerosAmigos());
		}
}
