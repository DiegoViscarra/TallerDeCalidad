package casosDeUso;

import java.util.ArrayList;

import entidades.CDR;
import modelos.CDRModelo;

public interface IPersistenciaBDCDR {
	public void crearTabla();
	public void poblarTabla(CDR registro);
	public ArrayList<CDRModelo> mostrarTabla(String sentenciaSQL);
	public void borrarTodosLosDatosDeCDR();

	
}
