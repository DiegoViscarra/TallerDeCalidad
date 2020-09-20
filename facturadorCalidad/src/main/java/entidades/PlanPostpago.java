package entidades;

import casosDeUso.IPlan;

public class PlanPostpago implements IPlan{

	public double calcularCostoDeUnaLlamada(CDR registro) {
		return costoLlamada.calcularCosto("CALCULOSIMPLE", registro, this);
	}

	@Override
	public String obtenerTipoTarifa() {
		return "POSTPAGO";
	}

}
