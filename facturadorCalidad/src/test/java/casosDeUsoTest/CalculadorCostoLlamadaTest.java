package casosDeUsoTest;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import casosDeUso.CalculadorCostoLlamada;
import entidades.CDR;
import entidades.Cliente;
import entidades.PlanPrepago;

public class CalculadorCostoLlamadaTest {
	CDR registro;
	PlanPrepago plan;
	@BeforeMethod
	public void beforeMethod() {
		registro = new CDR(1,2,"02:45", "3/1/2020", "12:00");
		plan = new PlanPrepago();
	}
	/*Prueba método calcularCosto, caso por default*/
	  @Test
	  void asignarCostoYConseguirCostoDeLlamadaCasoDefaultTest() {
		    CalculadorCostoLlamada calcular = new CalculadorCostoLlamada();
			Assert.assertEquals(0,calcular.calcularCosto("Ninguno",registro,plan));
		}
	
	  /*Prueba método calcularCosto, caso por CALCULOSIMPLE*/
	  @Test
	  void asignarCostoYConseguirCostoDeLlamadaCasoCalculosimpleTest() {
		    CalculadorCostoLlamada calcular = new CalculadorCostoLlamada();
			Assert.assertEquals(3.9875,calcular.calcularCosto("CALCULOSIMPLE",registro,plan));
		}
	  
}
