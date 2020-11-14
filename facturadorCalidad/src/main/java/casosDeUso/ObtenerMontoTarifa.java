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

	public double obtenerMontoTarifaDiferenciadaPorHora(CDR registro) {
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

	public double obtenerMontoTarifaWow() {
		return TARIFANORMALWOW;
	}

	public double obtenerMontoTarifaPostpago() {
		return TARIFAFIJAPOSTPAGO;
	}
	
	public boolean estaEnHorarioSuperReducido(int hora) {
		return hora >=1 && hora < 7;
	}

	public boolean estaEnHorarioReducido(int hora) {
		return hora >= 21 && hora < 24 || hora >= 0 && hora < 1;
	}

	public boolean estaEnHorarioNormal(int hora) {
		return hora >= 7 && hora < 21;
	}
}

