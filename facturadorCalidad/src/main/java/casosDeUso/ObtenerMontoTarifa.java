package casosDeUso;

import entidades.CDR;

public class ObtenerMontoTarifa {

	private static final double TARIFANORMALWOW = 0.99;
	private static final int TARIFAFIJAPOSTPAGO = 1;
	private static final double TARIFAHORARIOSUPERREDUCIDO = 0.70;
	private static final double TARIFAHORARIOREDUCIDO = 0.95;
	private static final double TARIFAHORARIONORMAL = 1.45;

	
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
			tarifa = TARIFAHORARIONORMAL;
		if(estaEnHorarioReducido(hora))
			tarifa = TARIFAHORARIOREDUCIDO;
		if(estaEnHorarioSuperReducido(hora))
			tarifa = TARIFAHORARIOSUPERREDUCIDO;
		return tarifa;
	} 

	private double obtenerMontoTarifaWow() {
		return TARIFANORMALWOW;
	}

	private double obtenerMontoTarifaPostpago() {
		return TARIFAFIJAPOSTPAGO;
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

