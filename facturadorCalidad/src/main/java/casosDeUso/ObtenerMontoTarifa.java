package casosDeUso;

import entidades.CDR;

public class ObtenerMontoTarifa {

	private static final double _TARIFANORMALWOW = 0.99;
	private static final int _TARIFAFIJAPOSTPAGO = 1;
	private static final double _TARIFAHORARIOSUPERREDUCIDO = 0.70;
	private static final double _TARIFAHORARIOREDUCIDO = 0.95;
	private static final double _TARIFAHORARIONORMAL = 1.45;

	
	public double obtenerMontoTarifa(String tipoPlan, CDR registro) {
		switch(tipoPlan) {
		case "POSTPAGO":
		{
			return obtenerMontoTarifaPostpago();
		}
		case "PREPAGO":
		{
			return obtenerMontoTarifaDiferenciadaPorHora(registro);
		}
		case "WOW":
		{
			return obtenerMontoTarifaWow();
		}
		default:
			return 0;
		}
	}

	private double obtenerMontoTarifaDiferenciadaPorHora(CDR registro) {
		double tarifa = 0;
		int hora = Integer.parseInt(registro.getHora().split(":")[0]);
		if(estaEnHorarioNormal(hora))
			tarifa = _TARIFAHORARIONORMAL;
		if(estaEnHorarioReducido(hora))
			tarifa = _TARIFAHORARIOREDUCIDO;
		if(estaEnHorarioSuperReducido(hora))
			tarifa = _TARIFAHORARIOSUPERREDUCIDO;
		return tarifa;
	} 

	private double obtenerMontoTarifaWow() {
		return _TARIFANORMALWOW;
	}

	private double obtenerMontoTarifaPostpago() {
		return _TARIFAFIJAPOSTPAGO;
	}
	
	private boolean estaEnHorarioSuperReducido(int hora) {
		return hora >=1 && hora < 7;
	}

	private boolean estaEnHorarioReducido(int hora) {
		return hora >= 21 && hora < 24 || hora >= 0 && hora < 1;
	}

	private boolean estaEnHorarioNormal(int hora) {
		return hora >= 7 && hora < 21;
	}
}

