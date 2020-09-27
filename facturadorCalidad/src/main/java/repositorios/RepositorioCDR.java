package repositorios;

import java.util.ArrayList;

import casosDeUso.IRepositorioCDR;
import entidades.CDR;

public class RepositorioCDR implements IRepositorioCDR {
	
	private ArrayList<CDR> registrosCargados = new ArrayList<CDR>();
	
	
	@Override
	public void registrarCDRs(String registros) {
		
		int numeroTelefonicoOrigen = 0, numeroTelefonicoDestino = 0;
		String duracionDeLaLlamada = null, fecha = null, hora = null;
		String[] registrosDivididos = registros.split("\\r?\\n|;");
		int i =0;
		while(i<registrosDivididos.length) {
			numeroTelefonicoOrigen=Integer.parseInt(registrosDivididos[i]);i++;
			numeroTelefonicoDestino=Integer.parseInt(registrosDivididos[i]);i++;
			duracionDeLaLlamada=registrosDivididos[i];i++;
			fecha=registrosDivididos[i];i++;
			hora=registrosDivididos[i];	
			CDR nuevoCDR = new CDR(numeroTelefonicoOrigen, numeroTelefonicoDestino, duracionDeLaLlamada, fecha, hora);
			registrosCargados.add(nuevoCDR);
			i++;
		}	
	}
	@Override
	public ArrayList<CDR> obtenerRegistrosNoTarificados() {
		return registrosCargados;
	}

	@Override
	public void vaciarRegistros() {
		registrosCargados.clear();
	}

}
