package casosDeUso;

import java.util.ArrayList;

import entidades.CDR;

public interface IRepositorioCDR {
	void registrarCDRs(String registros);
	ArrayList<CDR> obtenerRegistrosNoTarificados();
	void vaciarRegistros();
}
