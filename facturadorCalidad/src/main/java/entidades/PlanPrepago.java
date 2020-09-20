package entidades;

import casosDeUso.IPlan;

public class PlanPrepago implements IPlan{
	
	@Override
	public double calcularCostoDeUnaLlamada(CDR registro) {
		return costoLlamada.calcularCosto("CALCULOSIMPLE", registro, this);
	}

	@Override
	public String obtenerTipoTarifa() {
		return "PREPAGO";
	}
}
