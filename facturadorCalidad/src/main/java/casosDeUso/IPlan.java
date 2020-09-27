package casosDeUso;

import entidades.CDR;

public interface IPlan {
	//public static ObtenerMontoTarifa tarifa = new ObtenerMontoTarifa();
	//public static CalculadorCostoLlamada costoLlamada = new CalculadorCostoLlamada();
	double calcularCostoDeUnaLlamada(CDR registro);
	String obtenerTipoTarifa();
}
