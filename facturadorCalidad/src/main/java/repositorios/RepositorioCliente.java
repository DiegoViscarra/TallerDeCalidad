package repositorios;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;

import casosDeUso.IPersistencia;
import casosDeUso.IPlan;
import casosDeUso.IRepositorioCliente;
import entidades.Cliente;

public class RepositorioCliente implements IRepositorioCliente{
	private final static Logger LOGGER = Logger.getLogger(RepositorioCliente.class.getName());
	private ArrayList<Cliente> clientesRegistrados = new ArrayList<Cliente>();
	private FactoriaPlan factoria = new FactoriaPlan();
	IPersistencia persistencia;
	public RepositorioCliente(IPersistencia persistencia) {
		this.persistencia = persistencia;
	}
	@Override
	public void registrarNuevoClientePlanNormal(Cliente cliente, String planCliente) {
		IPlan nuevoPlan;
		nuevoPlan = factoria.getPlan(planCliente);
		cliente.setPlan(nuevoPlan);
		cliente.setTipoPlan(planCliente);
		clientesRegistrados.add(cliente);
		
	}

	@Override
	public void registrarNuevoClientePlanNumerosAmigos(Cliente cliente, String planCliente,
			ArrayList<Integer> numerosAmigos) {
		IPlan nuevoPlan;
		nuevoPlan = factoria.getPlan(planCliente, numerosAmigos);
		cliente.setPlan(nuevoPlan);
		cliente.setTipoPlan(planCliente);
		clientesRegistrados.add(cliente);
	}

	@Override
	public Cliente buscarCliente(int numeroTelefonico) {
		for(Cliente cliente : clientesRegistrados)
		{
			if(cliente.getNumeroTelefonico() == numeroTelefonico)
				return cliente;
		}
		return null;
	}

	@Override
	public ArrayList<Cliente> devolverClientes() {
		return clientesRegistrados;
	}
	@Override
	public void registrarClientes(String registroClientes) {
		String ci = null, nombre = null, plan= null;
		int numeroTelefonico = 0;
	    ArrayList<Integer>numerosAmigos = new ArrayList<Integer>();
		String[] clientesDivididos = registroClientes.split("\\r?\\n|;");
		int i =0;
		while(i<clientesDivididos.length) {
			ci = clientesDivididos[i]; i++;
			numeroTelefonico = Integer.parseInt(clientesDivididos[i]);i++;
			String message=String.valueOf(numeroTelefonico);
			LOGGER.fine(message);
			nombre = clientesDivididos[i];i++;
			plan = clientesDivididos[i].toUpperCase(); i++;
			plan = plan.trim();
			Cliente cliente = registrarClienteEnBD(ci, nombre, plan, numeroTelefonico);
			registrarClienteEnRepositorio(plan, numeroTelefonico, numerosAmigos, clientesDivididos, i, cliente);
			i++;
		}
	}
	private void registrarClienteEnRepositorio(String plan, int numeroTelefonico, ArrayList<Integer> numerosAmigos,
			String[] clientesDivididos, int posicion, Cliente cliente) {
		if(plan.equalsIgnoreCase("WOW")) {
			registrarClientePlanAmigos(plan, numeroTelefonico, numerosAmigos, clientesDivididos, posicion, cliente);
		}
		else {
			registrarNuevoClientePlanNormal(cliente,plan);
		}
	}
	
	private Cliente registrarClienteEnBD(String ci, String nombre, String plan, int numeroTelefonico) {
		Cliente cliente = new Cliente(nombre,ci,numeroTelefonico);
		cliente.setTipoPlan(plan);
		persistencia.persistirEnBDClientes(cliente);
		return cliente;
	}
	
	private void registrarClientePlanAmigos(String plan, int numeroTelefonico, ArrayList<Integer> numerosAmigos,
			String[] clientesDivididos, int posicion, Cliente cliente) {
		int numTelefonicoAmigo;
		String numAmigos = clientesDivididos[posicion].replace('[',' ');
		numAmigos = numAmigos.replace(']',' ');
		numAmigos = StringUtils.remove(numAmigos, " ");				
		String[] amigos = numAmigos.split(",");
		for(int j=0; j<amigos.length; j++) {
			numTelefonicoAmigo=Integer.parseInt(amigos[j]);
			numerosAmigos.add(numTelefonicoAmigo);
		}
		registrarNuevoClientePlanNumerosAmigos(cliente,plan, numerosAmigos);
		persistencia.persistirEnBDNumerosAmigos(numerosAmigos, numeroTelefonico);
	}

}
