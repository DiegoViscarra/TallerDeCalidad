package entidades;

import java.util.ArrayList;

import casosDeUso.ComposedElements;
import casosDeUso.IPlan;

public class PlanWow implements IPlan{
	private ArrayList<Integer> numerosAmigos = new ArrayList<Integer>();
	public PlanWow (ArrayList<Integer> numerosAmigos){
		this.numerosAmigos = numerosAmigos;
	}
	@Override
	public double calcularCostoDeUnaLlamada(CDR registro) {
		if(estaEntreNumerosAmigos(registro))
			return 0;
		else
			return ComposedElements.costoLlamada.calcularCosto("CALCULOSIMPLE", registro, this);
	}

	private boolean estaEntreNumerosAmigos(CDR registro) {
		return numerosAmigos.contains(registro.getNumeroTelefonoDestino());
	}

	@Override
	public String obtenerTipoTarifa() {
		return "WOW";
	}
	
}
