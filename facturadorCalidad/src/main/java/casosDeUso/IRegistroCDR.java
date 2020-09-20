package casosDeUso;

import java.util.ArrayList;

import entidades.CDR;

public interface IRegistroCDR {
	void guardarTemporalmenteCDRs(String registros);
	ArrayList<CDR> obtenerRegistrosNoTarificados();
}
