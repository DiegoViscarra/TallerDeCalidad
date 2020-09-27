package casosDeUso;

import entidades.CDR;

public class CalculadorCostoLlamada {
	public double calcularCosto(String tipoCalculo, CDR registro, IPlan plan) {
		double tarifa = ComposedElements.tarifa.obtenerMontoTarifa(plan.obtenerTipoTarifa(), registro);
		switch(tipoCalculo) {
		case "CALCULOSIMPLE": 
		{
			return tarifa * registro.convertirMinutosADecimal();
		}	
		default:
			return 0;
		}		
	}
}
