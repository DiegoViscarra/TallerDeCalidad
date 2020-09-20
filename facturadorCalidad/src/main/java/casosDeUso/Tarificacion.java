package casosDeUso;

import java.util.ArrayList;

import entidades.CDR;
import entidades.Cliente;

public class Tarificacion implements ITarificacion {

	private IRepositorioCliente repositorioClientes;
	public Tarificacion(IRepositorioCliente repositorioClientes) {
		this.repositorioClientes = repositorioClientes;		
	}
	@Override
	public double tarificar(CDR registro, Cliente cliente) {
		return registro.calcularCostoDeLlamada(cliente.getPlan());
	}

	@Override
	public ArrayList<CDR> tarificarRegistros(ArrayList<CDR> registrosTelefonicosNoTarificados) {
		ArrayList<CDR> registrosTelefonicos = registrosTelefonicosNoTarificados;
		for(CDR registro : registrosTelefonicos)
		{
			if(noEstaTarificado(registro))
			{
				Cliente cliente = repositorioClientes.buscarCliente(registro.getNumeroTelefonoOrigen());
				registro.setCosto(tarificar(registro, cliente));
			}
		}
		return registrosTelefonicos;
	}
	
	private boolean noEstaTarificado(CDR registro) {
		return registro.getCostoDeLlamada() < 0;
	}

}
