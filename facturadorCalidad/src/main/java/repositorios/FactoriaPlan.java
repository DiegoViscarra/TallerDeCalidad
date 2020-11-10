package repositorios;

import java.util.ArrayList;

import casosDeUso.IPlan;
import entidades.PlanPostpago;
import entidades.PlanPrepago;
import entidades.PlanWow;

public class FactoriaPlan {
	public IPlan getPlan(String tipoPlan) {
		if(esPrepago(tipoPlan)) 
			return new PlanPrepago();
		if(esPostpago(tipoPlan))
			return new PlanPostpago();
		return null;
	}
	
	public IPlan getPlan(String tipoPlan,ArrayList<Integer> numerosAmigos) {
		if(esWow(tipoPlan))
			return new PlanWow(numerosAmigos); 
		return null;
	}
	

	private boolean esWow(String tipoPlan) {
		return tipoPlan.equalsIgnoreCase("WOW");
	}

	private boolean esPostpago(String tipoPlan) {
		return tipoPlan.equalsIgnoreCase("POSTPAGO");
	}

	private boolean esPrepago(String tipoPlan) {
		return tipoPlan.equalsIgnoreCase("PREPAGO");
	}
	
	
	
}
