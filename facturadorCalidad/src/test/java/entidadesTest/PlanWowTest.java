package entidadesTest;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import entidades.CDR;
import entidades.PlanWow;


public class PlanWowTest {
	public String duracionPrueba, horaPrueba;
	public ArrayList<Integer> numerosAmigos = new ArrayList<Integer>();
	public PlanWow planTester;
	public CDR registroLlamadaAmigo, registroLlamadaExterna;
	
	@BeforeMethod
	public void init() {
		horaPrueba = "02:45";
		duracionPrueba = "12:00";
		numerosAmigos.add(1234567);
		numerosAmigos.add(2345678);
		numerosAmigos.add(3456789);
		numerosAmigos.add(4567890);
		planTester = new PlanWow(numerosAmigos);
		registroLlamadaAmigo= new CDR(7777777, 1234567, horaPrueba, "Test", duracionPrueba);
		registroLlamadaExterna = new CDR(7777777, 9876543, horaPrueba, "Test", duracionPrueba);
	}
	
	@Test
	public void calculatePriceCallBelongsToFriends() {
		Assert.assertEquals(0.0, planTester.calcularCostoDeUnaLlamada(registroLlamadaAmigo));
	}
	
	@Test
	public void calculatePriceExternalNumber() {
		Assert.assertEquals(2.7225, planTester.calcularCostoDeUnaLlamada(registroLlamadaExterna));
	}
	
	
	
	
	

}
