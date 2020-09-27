package entidades;

import casosDeUso.ComposedElements;
import casosDeUso.IPlan;

public class PlanPostpago implements IPlan{

	public double calcularCostoDeUnaLlamada(CDR registro) {
		return ComposedElements.costoLlamada.calcularCosto("CALCULOSIMPLE", registro, this);
	}

	@Override
	public String obtenerTipoTarifa() {
		return "POSTPAGO";
	}

}
