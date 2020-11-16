package controladoresTest;

import java.io.IOException;
import java.util.ArrayList;

import static spark.Spark.port;
import static spark.Spark.stop;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.javatuples.Pair;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import casosDeUso.IPersistencia;
import casosDeUso.IPersistenciaArchivos;
import casosDeUso.IPersistenciaBDCDR;
import casosDeUso.IPersistenciaBDClientes;
import casosDeUso.IPlan;
import casosDeUso.IRegistroCDR;
import casosDeUso.IRepositorioCDR;
import casosDeUso.IRepositorioCliente;
import casosDeUso.ITarificacion;
import casosDeUso.Persistencia;
import casosDeUso.RegistroCDR;
import casosDeUso.Tarificacion;
import controladores.ControladorCDRs;
import controladores.ControladorClientes;
import entidades.CDR;
import entidades.Cliente;
import entidades.PlanPostpago;
import entidades.PlanPrepago;
import entidades.PlanWow;
import modelos.ClienteModelo;
import repositorios.PersistenciaArchivos;
import repositorios.PersistenciaBDCDR;
import repositorios.PersistenciaBDClientes;
import repositorios.RepositorioCDR;
import repositorios.RepositorioCliente;

public class ControladorClientesTest {
	public ITarificacion tarificacion = null;
	public IPersistencia persistencia = null;
	public IRegistroCDR registroCDR = null;
	public IRepositorioCliente repositorio = null;
	public ControladorClientes controlador = null;
	public IPersistenciaBDCDR persistenciaBDCDR;
	public IPersistenciaBDClientes persistenciaBDClientes;
	public IPersistenciaArchivos persistenciaArchivos;
	public IRepositorioCDR repositorioCDR;
	
	
	@BeforeClass
	public void initControlador() {
		
<<<<<<< HEAD
=======
		port(8080);
>>>>>>> c0e1545286515c829d1a31fdc97693d1e9abe5fd
		persistenciaBDCDR = new PersistenciaBDCDR();
		persistenciaBDClientes = new PersistenciaBDClientes();
		persistenciaArchivos = new PersistenciaArchivos();
		repositorioCDR = new RepositorioCDR();
		
		persistencia = new Persistencia(persistenciaBDCDR, persistenciaBDClientes, persistenciaArchivos, repositorioCDR);
		repositorio = new  RepositorioCliente(persistencia);
		controlador = new ControladorClientes(persistencia);
		
		persistenciaBDClientes.borrarTodosLosDatosDeClientes();
		persistenciaBDClientes.borrarTodosLosDatosDeNumerosAmigos();

		
		//CrearClientes
		Cliente cliente1 = new Cliente("Sergio", "5", 123);
		IPlan plan1 = new PlanPrepago();
		cliente1.setPlan(plan1);
		cliente1.setTipoPlan("PREPAGO");
		
		Cliente cliente2 = new Cliente("Ana", "9", 789);
		IPlan plan2 = new PlanPostpago();
		cliente2.setPlan(plan2);
		cliente2.setTipoPlan("POSTPAGO");
		
		Cliente cliente3 = new Cliente("Pedro", "3", 567);
		ArrayList<Integer> amigos = new ArrayList<Integer>();
		amigos.add(123); amigos.add(234); amigos.add(345); amigos.add(456);
		IPlan plan3 = new PlanWow(amigos);
		cliente3.setPlan(plan3);
		cliente3.setTipoPlan("WOW");
		
		
		persistenciaBDClientes.poblarTablaClientes(cliente1);
		persistenciaBDClientes.poblarTablaClientes(cliente2);
		persistenciaBDClientes.poblarTablaClientes(cliente3);
		persistenciaBDClientes.poblarTablaClientesConNumerosAmigos(amigos, 567);
		repositorio.registrarNuevoClientePlanNormal(cliente1, "PREPAGO");
		repositorio.registrarNuevoClientePlanNormal(cliente2, "POSTPAGO");
		repositorio.registrarNuevoClientePlanNumerosAmigos(cliente3, "WOW", amigos);
		
	}
	
	@Test
	public void recuperarClientesTest() {
		ArrayList<ClienteModelo> clientes = ControladorClientes.devolverClientesDeBD(persistencia);
		Assert.assertNotNull(clientes);
		Assert.assertEquals(clientes.size(), 3);
	}
	
	@Test
	public void recuperarClientesAmigosTest() {
		ArrayList<ClienteModelo> clientes = ControladorClientes.devolverClientesConNumerosAmigosDeBD(persistencia);
		Assert.assertNotNull(clientes);
		Assert.assertEquals(clientes.size(), 1);
	}
	
	
	@Test
	public void getClientes() throws IOException, InterruptedException {
		HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://localhost:8080/clientes");
        HttpResponse response = client.execute(request);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	}
	
	
		
	@AfterClass
	public void endTest() {
<<<<<<< HEAD
=======
		stop();
>>>>>>> c0e1545286515c829d1a31fdc97693d1e9abe5fd
		persistenciaBDClientes.borrarTodosLosDatosDeClientes();
		persistenciaBDClientes.borrarTodosLosDatosDeNumerosAmigos();
		persistenciaBDCDR.borrarTodosLosDatosDeCDR();
	}
	

}
