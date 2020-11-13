package casosDeUso;
import java.util.ArrayList;

import org.javatuples.Pair;

import entidades.CDR;

public interface IPersistenciaArchivos {
	String serializar(ArrayList<CDR> registrosTelefonicos);
	ArrayList<CDR> deserializar(String nombreArchivo);
	ArrayList<Pair<String, ArrayList<CDR>>> deserializarTodosLosArchivos();
}
