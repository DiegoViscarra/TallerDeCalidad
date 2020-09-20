package casosDeUso;

import java.util.ArrayList;

import entidades.CDR;
import entidades.Cliente;

public class RegistroCDR implements IRegistroCDR{

	private IRepositorioCDR repositorioCDR;
	
	public RegistroCDR(IRepositorioCDR repositorioCDR){
		this.repositorioCDR =repositorioCDR;
	}

	@Override
	public void guardarTemporalmenteCDRs(String registros) {
		repositorioCDR.registrarCDRs(registros);
		
	}

	@Override
	public ArrayList<CDR> obtenerRegistrosNoTarificados() {
		return repositorioCDR.obtenerRegistrosNoTarificados();
	}

}
