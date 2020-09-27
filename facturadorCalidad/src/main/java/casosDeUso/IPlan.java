package casosDeUso;

import entidades.CDR;

public interface IPlan {
	double calcularCostoDeUnaLlamada(CDR registro);
	String obtenerTipoTarifa();
}
