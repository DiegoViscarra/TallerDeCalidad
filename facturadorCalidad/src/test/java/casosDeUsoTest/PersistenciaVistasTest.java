package casosDeUsoTest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import casosDeUso.IPersistenciaArchivos;
import casosDeUso.IPersistenciaBDCDR;
import casosDeUso.IPersistenciaBDClientes;
import casosDeUso.IPlan;
import casosDeUso.IRepositorioCDR;
import casosDeUso.Persistencia;
import entidades.CDR;
import entidades.Cliente;
import entidades.PlanWow;
import modelos.CDRModelo;
import modelos.ClienteModelo;
import repositorios.PersistenciaArchivos;
import repositorios.PersistenciaBDCDR;
import repositorios.PersistenciaBDClientes;
import repositorios.RepositorioCDR;

public class PersistenciaVistasTest {
	public Persistencia persistencia;
	public IPersistenciaBDCDR persistenciaBDCDR;
	public IPersistenciaBDClientes persistenciaBDClientes;
	public IPersistenciaArchivos persistenciaArchivos;
	public IRepositorioCDR repositorioCDR;
	public ArrayList<CDRModelo> registros, filtrados;
	@BeforeClass
	public void initPersistencia() {
		persistenciaBDCDR = new PersistenciaBDCDR();
		persistenciaBDClientes = new PersistenciaBDClientes();
		persistenciaArchivos = new PersistenciaArchivos();
		repositorioCDR = new RepositorioCDR();
		
		persistencia = new Persistencia(persistenciaBDCDR, persistenciaBDClientes, persistenciaArchivos, repositorioCDR);
	}
	
	@BeforeMethod
	public void initPersistirEnBD() {
		persistenciaBDClientes.borrarTodosLosDatosDeClientes();
		persistenciaBDClientes.borrarTodosLosDatosDeNumerosAmigos();
		persistenciaBDCDR.borrarTodosLosDatosDeCDR();
		Cliente cliente = new Cliente("Sergio", "1", 323);
		ArrayList<Integer> amigos = new ArrayList<Integer>();
		amigos.add(234);
		amigos.add(345);
		amigos.add(456);
		amigos.add(567);
		IPlan plan = new PlanWow(amigos);
		cliente.setPlan(plan);
		cliente.setTipoPlan("WOW");
		persistencia.persistirEnBDClientes(cliente);
		persistencia.persistirEnBDNumerosAmigos(amigos, 323);
		
		ArrayList<CDR> registrosTelefonicos = new ArrayList<CDR>();
		CDR registro = new CDR(323, 345, "02:45", "11/11/2020", "23:00");
		registro.setCosto(2.75);
		CDR registro1 = new CDR(323, 325, "02:45", "11/10/2020", "23:00");
		registro1.setCosto(2.75);
		registrosTelefonicos.add(registro);
		registrosTelefonicos.add(registro1);
		persistencia.persistirEnBDCdr(registrosTelefonicos);
	}
	
	@Test
	public void mostrarPersistenciaClienteEnBD() {
		ArrayList<ClienteModelo> clientes = persistencia.mostrarDeBDClientes();
		Assert.assertEquals(clientes.get(0).getNumeroTelefonico(), 323);
		Assert.assertEquals(clientes.size(), 1);
		Assert.assertEquals(clientes.get(0).getNombre(), "Sergio");
	}
	
	
	@Test
	public void mostrarPersistenciaAmigosEnBD() {
		ArrayList<Integer> amigos = new ArrayList<Integer>();
		amigos.add(234);
		amigos.add(345);
		amigos.add(456);
		amigos.add(567);
		ArrayList<ClienteModelo> clientesAmigos = persistencia.mostrarDeBDClientesAmigos();
		Assert.assertEquals(clientesAmigos.size(), 1);
		Assert.assertEquals(clientesAmigos.get(0).getNumerosAmigos(), amigos);
		Assert.assertEquals(clientesAmigos.get(0).getNumeroTelefonico(), 323);
;	}
	
	@Test
	public void mostrarPersistenciaCDREnBD() {
		ArrayList<CDRModelo> registros = persistencia.mostrarDeBDCDRs();
		Assert.assertEquals(registros.size(), 2);
		Assert.assertEquals(registros.get(0).getFecha(), "11/11/2020");
		Assert.assertEquals(registros.get(1).getFecha(), "11/10/2020");
	}
	
	@Test
	public void mostrarPersistenciaCDRFiltroMes() {
		Date myDate = new Date();
		String fecha= new SimpleDateFormat("yyyy-MM-dd").format(myDate);
		ArrayList<CDRModelo> registros = persistencia.mostrarDeBDCDRsFiltradosPor("2020-03-11");
		Assert.assertEquals(registros.size(), 0);
		System.out.print(fecha);
		ArrayList<CDRModelo> registros1 = persistencia.mostrarDeBDCDRsFiltradosPor(fecha);
		Assert.assertEquals(registros1.size(), 2);
	}
	@AfterMethod
	public void tearBDs() {
		persistenciaBDClientes.borrarTodosLosDatosDeClientes();
		persistenciaBDClientes.borrarTodosLosDatosDeNumerosAmigos();
		persistenciaBDCDR.borrarTodosLosDatosDeCDR();
	}
	

}
