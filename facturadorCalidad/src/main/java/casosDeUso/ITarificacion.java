package casosDeUso;

import java.util.ArrayList;

import entidades.CDR;
import entidades.Cliente;

public interface ITarificacion {
	double tarificar(CDR registro, Cliente cliente);
	ArrayList<CDR> tarificarRegistros(ArrayList<CDR> registrosTelefonicos);
}
