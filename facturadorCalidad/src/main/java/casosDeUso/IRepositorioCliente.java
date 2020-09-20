package casosDeUso;

import java.util.ArrayList;

import entidades.Cliente;

public interface IRepositorioCliente {
	void registrarNuevoClientePlanNormal(Cliente cliente, String planCliente);
	void registrarNuevoClientePlanNumerosAmigos(Cliente cliente, String planCliente, ArrayList<Integer> numerosAmigos);
	Cliente buscarCliente(int numeroTelefonico);
	ArrayList<Cliente> devolverClientes();
	void registrarClientes(String registroClientes);
}
