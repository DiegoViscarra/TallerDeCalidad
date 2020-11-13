package casosDeUsoTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import casosDeUso.CalculadorCostoLlamada;
import entidades.CDR;
import entidades.PlanPrepago;

public class CalculadorCostoLlamadaTest {
	
	/*Prueba método calcularCosto, caso por default*/
	  @Test
	  void asignarCostoYConseguirCostoDeLlamadaCasoDefaultTest() {
		  	CDR registro = new CDR(1,2,"02:45", "3/1/2020", "12:00");
		  	PlanPrepago plan = new PlanPrepago();
		    CalculadorCostoLlamada calcular = new CalculadorCostoLlamada();
			Assert.assertEquals(0,calcular.calcularCosto("Ninguno",registro,plan));
		}
	
	  /*Prueba método calcularCosto, caso por CALCULOSIMPLE*/
	  @Test
	  void asignarCostoYConseguirCostoDeLlamadaCasoCalculosimpleTest() {
		  	CDR registro = new CDR(1,2,"02:45", "3/1/2020", "12:00");
		  	PlanPrepago plan = new PlanPrepago();
		    CalculadorCostoLlamada calcular = new CalculadorCostoLlamada();
			Assert.assertEquals(3.9875,calcular.calcularCosto("CALCULOSIMPLE",registro,plan));
		}
	  
}
