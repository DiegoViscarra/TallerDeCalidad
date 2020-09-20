package casosDeUso;

import java.util.ArrayList;

import entidades.Cliente;

public interface IRegistroClientes {
	Cliente buscarCliente(int numeroTelefonico);
	ArrayList<Cliente> devolverClientes();
	void guardarClientesCargados(String RegistroClientes);
	void llenarListaClientes();
}
