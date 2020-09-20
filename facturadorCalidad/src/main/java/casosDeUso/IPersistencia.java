package casosDeUso;

import java.util.ArrayList;

import org.javatuples.Pair;

import entidades.CDR;
import entidades.Cliente;
import io.vavr.Tuple2;
import modelos.CDRModelo;
import modelos.ClienteModelo;
import modelos.FacturaModelo;

public interface IPersistencia {
	void persistirEnBDCdr(ArrayList<CDR> registrosTelefonicos);
	void persistirEnArchivo(ArrayList<CDR> registrosTelefonicos);
	ArrayList<CDRModelo> mostrarDeBDCDRs();
	ArrayList<CDRModelo> mostrarDeBDCDRsFiltradosPor(String Fecha);
	void persistirEnBDClientes(Cliente cliente);
	void persistirEnBDNumerosAmigos(ArrayList<Integer> numerosAmigos, Integer numeroCliente);
	ArrayList<ClienteModelo> mostrarDeBDClientes();
	ArrayList<ClienteModelo> mostrarDeBDClientesAmigos();
	ArrayList<Pair<String,ArrayList<CDR>>> deserializarArchivos();
	FacturaModelo obtenerFacturaDeMesParaCliente(int numeroTelefonico, String mes);
}
