package controladoresTest;

import java.io.IOException; 
import java.util.ArrayList; 

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
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
import entidades.CDR;
import entidades.Cliente;
import entidades.PlanPostpago;
import entidades.PlanPrepago;
import repositorios.PersistenciaArchivos;
import repositorios.PersistenciaBDCDR;
import repositorios.PersistenciaBDClientes;
import repositorios.RepositorioCDR;
import repositorios.RepositorioCliente;

public class ControladorCDRTest {
	public ITarificacion tarificacion = null;
	public IPersistencia persistencia = null;
	public IRegistroCDR registroCDR = null;
	public IRepositorioCliente repositorio = null;
	public ControladorCDRs controlador = null;
	public IPersistenciaBDCDR persistenciaBDCDR;
	public IPersistenciaBDClientes persistenciaBDClientes;
	public IPersistenciaArchivos persistenciaArchivos;
	public IRepositorioCDR repositorioCDR;
	
	@BeforeClass
	public void initControlador() {
		persistenciaBDCDR = new PersistenciaBDCDR();
		persistenciaBDClientes = new PersistenciaBDClientes();
		persistenciaArchivos = new PersistenciaArchivos();
		repositorioCDR = new RepositorioCDR();
		
		persistencia = new Persistencia(persistenciaBDCDR, persistenciaBDClientes, persistenciaArchivos, repositorioCDR);
		repositorio = new  RepositorioCliente(persistencia);
		tarificacion = new Tarificacion(repositorio);
		registroCDR = new RegistroCDR(repositorioCDR);
		
		controlador = new ControladorCDRs(tarificacion, persistencia, registroCDR);
		
		//CrearClientes
		Cliente cliente1 = new Cliente("Sergio", "5", 123);
		IPlan plan1 = new PlanPrepago();
		cliente1.setPlan(plan1);
		cliente1.setTipoPlan("PREPAGO");
		
		Cliente cliente2 = new Cliente("Ana", "9", 789);
		IPlan plan2 = new PlanPostpago();
		cliente2.setPlan(plan2); 
		cliente2.setTipoPlan("POSTPAGO");
		persistenciaBDClientes.poblarTablaClientes(cliente1);
		persistenciaBDClientes.poblarTablaClientes(cliente2);
		repositorio.registrarNuevoClientePlanNormal(cliente1, "PREPAGO");
		repositorio.registrarNuevoClientePlanNormal(cliente2, "POSTPAGO");
	}
	
	@BeforeMethod
	public void initRegistrosNoTarificados() {

		repositorioCDR.registrarCDRs("123;456;2:45;3/1/2020;11:00\\r\\n\"");
		repositorioCDR.registrarCDRs("789;456;1:45;3/1/2020;11:00\\r\\n\"");
	}
	
	@Test
	public void devolverRegistrosNoTarificadosTest() {
		ArrayList<CDR> registros = ControladorCDRs.devolverRegistrosNoTarificados(registroCDR);
		for(CDR cdr: registros) {
			Assert.assertEquals(-1.0, cdr.getCostoDeLlamada());
		}
	}
	
	
	@Test
	public void devolverRegistrosTarificadosTest() {
		ArrayList<CDR> registros = ControladorCDRs.devolverRegistrosTarificados(tarificacion, registroCDR);
		for(CDR cdr: registros) {
			Assert.assertNotEquals(-1.0, cdr.getCostoDeLlamada());
		}
	}
	
	@Test
	public void getCDR() throws IOException, InterruptedException {
		HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://localhost:8080/registrosTarificados");
        HttpResponse response = client.execute(request);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	}
	
	@Test 
	public void getCDRRecuperados() throws IOException, InterruptedException {
		HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://localhost:8080/registrosRecuperados");
        HttpResponse response = client.execute(request);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	}
	
	@Test
	public void cambiarModoDeConfiguracionDB() throws IOException, InterruptedException {
		HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://localhost:8080/configuracion/baseDeDatos");
        HttpResponse response = client.execute(request);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	}
	
	@Test
	public void cambiarModoDeConfiguracionArchivo() throws IOException, InterruptedException {
		HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://localhost:8080/configuracion/archivo");
        HttpResponse response = client.execute(request);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	}
	
	@Test
	public void guardar() throws IOException, InterruptedException {
		HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://localhost:8080/guardar");
        HttpResponse response = client.execute(request);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	}
	
	@Test
	public void configuracion() throws IOException, InterruptedException {
		HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://localhost:8080/configuracion");
        HttpResponse response = client.execute(request);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	}
	
	@Test
	public void postFiltrar() throws IOException, InterruptedException {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost("http://localhost:8080/filtrar?fecha=10102020");
		HttpResponse response = httpclient.execute(httpPost);
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	}
	
	@AfterMethod
	public void emptyRepositorio() {
		repositorioCDR.vaciarRegistros();
	}
	
	
	@AfterClass
	public void endTest() {
		persistenciaBDClientes.borrarTodosLosDatosDeClientes();
		persistenciaBDCDR.borrarTodosLosDatosDeCDR();
		persistenciaBDClientes.borrarTodosLosDatosDeNumerosAmigos();
	}
}


