package casosDeUso;

import java.util.ArrayList;

import entidades.Cliente;
import modelos.ClienteModelo;

public interface IPersistenciaBDClientes {
	public void crearTabla();
	public void poblarTablaClientes(Cliente cliente);
	public void borrarTodosLosDatosDeClientes();
	public ArrayList<ClienteModelo> mostrarTablaClientes();
	
	public void poblarTablaClientesConNumerosAmigos(ArrayList<Integer> numerosAmigos, int numeroTelefonico);
	public ArrayList<ClienteModelo> mostrarTablaClientesConNumerosAmigos();
	public void borrarTodosLosDatosDeNumerosAmigos();
}
