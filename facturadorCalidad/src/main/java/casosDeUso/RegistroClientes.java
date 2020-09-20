package casosDeUso;

import java.util.ArrayList;

import entidades.Cliente;
import modelos.ClienteModelo;

public class RegistroClientes implements IRegistroClientes {

	private IRepositorioCliente repositorioCliente;
	private IPersistenciaBDClientes persistenciaBDClientes;
	public RegistroClientes(IRepositorioCliente repositorioCliente, IPersistenciaBDClientes persistenciaBDClientes) {
		this.repositorioCliente = repositorioCliente;
		this.persistenciaBDClientes = persistenciaBDClientes;
	}
	

	@Override
	public Cliente buscarCliente(int numeroTelefonico) {
		return repositorioCliente.buscarCliente(numeroTelefonico);
	}

	@Override
	public ArrayList<Cliente> devolverClientes() {
		return repositorioCliente.devolverClientes();
	}

	@Override
	public void guardarClientesCargados(String RegistroClientes) {
		repositorioCliente.registrarClientes(RegistroClientes);
	}
	
	@Override
	public void llenarListaClientes() {
		ArrayList<ClienteModelo> clientes = persistenciaBDClientes.mostrarTablaClientes();
		ArrayList<ClienteModelo> clientesAmigos = persistenciaBDClientes.mostrarTablaClientesConNumerosAmigos();
		for(ClienteModelo cliente : clientes) {
			Cliente clienteEntidad = convertirACliente(cliente);
			recuperarClienteEnSistema(clientesAmigos, cliente, clienteEntidad);
		}
		
	}

	private void recuperarClienteEnSistema(ArrayList<ClienteModelo> clientesAmigos, ClienteModelo cliente,
			Cliente clienteEntidad) {
		if(tienePlanWow(cliente)) {
			ArrayList<Integer> numerosAmigosDelCliente = buscarRegistroAmigos(cliente.getNumeroTelefonico(), clientesAmigos);
			repositorioCliente.registrarNuevoClientePlanNumerosAmigos(clienteEntidad, cliente.getTipoPlan(), numerosAmigosDelCliente);
		}
		else {
			repositorioCliente.registrarNuevoClientePlanNormal(clienteEntidad, cliente.getTipoPlan());
		}
	}

	private boolean tienePlanWow(ClienteModelo cliente) {
		return cliente.getTipoPlan().equalsIgnoreCase("WOW");
	}
	
	ArrayList<Integer> buscarRegistroAmigos(Integer numeroCliente, ArrayList<ClienteModelo> clientesAmigos){
		for(ClienteModelo cliente:clientesAmigos) {
			if(coincidenEnNumeroTelefonico(numeroCliente, cliente))
				return cliente.getNumerosAmigos();
		}
		return null;
	}

	private boolean coincidenEnNumeroTelefonico(Integer numeroCliente, ClienteModelo cliente) {
		return cliente.getNumeroTelefonico() == numeroCliente;
	}
	
	Cliente convertirACliente(ClienteModelo cliente) {
		Cliente clienteEntidad = new Cliente(cliente.getNombre(), cliente.getCi(), cliente.getNumeroTelefonico());
		return clienteEntidad;
	}

}
