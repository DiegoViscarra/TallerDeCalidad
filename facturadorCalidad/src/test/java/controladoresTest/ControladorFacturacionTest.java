package controladoresTest;

import static spark.Spark.port;

import java.io.IOException;
import java.net.URI;
<<<<<<< HEAD
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
=======
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

>>>>>>> c0e1545286515c829d1a31fdc97693d1e9abe5fd
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
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
import controladores.ControladorClientes;
import controladores.ControladorFacturacion;
import entidades.CDR;
import entidades.Cliente;
import entidades.PlanPostpago;
<<<<<<< HEAD
=======
import entidades.PlanPrepago;
import entidades.PlanWow;
>>>>>>> c0e1545286515c829d1a31fdc97693d1e9abe5fd
import modelos.FacturaModelo;
import repositorios.PersistenciaArchivos;
import repositorios.PersistenciaBDCDR;
import repositorios.PersistenciaBDClientes;
import repositorios.RepositorioCDR;
import repositorios.RepositorioCliente;

public class ControladorFacturacionTest {
<<<<<<< HEAD
=======
	
	HttpClient client;
	HttpRequest request;
	HttpResponse<?> response;
	
>>>>>>> c0e1545286515c829d1a31fdc97693d1e9abe5fd
	public ITarificacion tarificacion = null;
	public IPersistencia persistencia = null;
	public IRegistroCDR registroCDR = null;
	public IRepositorioCliente repositorio = null;
<<<<<<< HEAD
	public ControladorFacturacion controlador = null;
=======
	public ControladorClientes controlador = null;
>>>>>>> c0e1545286515c829d1a31fdc97693d1e9abe5fd
	public IPersistenciaBDCDR persistenciaBDCDR;
	public IPersistenciaBDClientes persistenciaBDClientes;
	public IPersistenciaArchivos persistenciaArchivos;
	public IRepositorioCDR repositorioCDR;
	
	@BeforeClass
	public void initControlador() {
<<<<<<< HEAD
=======
		//port(8080);
		//client = HttpClient.newBuilder().build();
		//response = null;
		
>>>>>>> c0e1545286515c829d1a31fdc97693d1e9abe5fd
		persistenciaBDCDR = new PersistenciaBDCDR();
		persistenciaBDClientes = new PersistenciaBDClientes();
		persistenciaArchivos = new PersistenciaArchivos();
		repositorioCDR = new RepositorioCDR();
		
		persistencia = new Persistencia(persistenciaBDCDR, persistenciaBDClientes, persistenciaArchivos, repositorioCDR);
		repositorio = new  RepositorioCliente(persistencia);
<<<<<<< HEAD
		controlador = new ControladorFacturacion(persistencia);
=======
		controlador = new ControladorClientes(persistencia);
>>>>>>> c0e1545286515c829d1a31fdc97693d1e9abe5fd
		
		persistenciaBDClientes.borrarTodosLosDatosDeClientes();
		persistenciaBDClientes.borrarTodosLosDatosDeNumerosAmigos();

		
		//CrearClientes
		Cliente cliente1 = new Cliente("Sergio", "5", 123);
		IPlan plan1 = new PlanPostpago();
		cliente1.setPlan(plan1);
		cliente1.setTipoPlan("POSTPAGO");	
		
		persistenciaBDClientes.poblarTablaClientes(cliente1);
		repositorio.registrarNuevoClientePlanNormal(cliente1, "POSTPAGO");
		CDR registro = new CDR(123, 456, "2:45","11/1/2020" , "12:00");
		registro.setCosto(2.75);
		persistenciaBDCDR.poblarTabla(registro);
	}
	
	@Test
<<<<<<< HEAD
	public void getFactura() throws IOException, InterruptedException {
		HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://localhost:8080/costoLlamadaCliente/123/mes/1");
        HttpResponse response = client.execute(request);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	}
	
	@Test
	public void devolverFacturaDeUnMes() {
		FacturaModelo factura = ControladorFacturacion.devolverFacturaDeUnMesDeUnCliente(123, "1", persistencia);
		Assert.assertEquals(factura.getNumeroTelefonico(), (Integer)123);
		Assert.assertEquals(factura.montoMes(), 2.75); 
	}
	
	
=======
	public void devolverFacturaDeUnMes() {
		FacturaModelo factura = ControladorFacturacion.devolverFacturaDeUnMesDeUnCliente(123, "1", persistencia);
		Assert.assertEquals(factura.montoMes(), 2.75);
		Assert.assertEquals(factura.getNumeroTelefonico(), (Integer)123);
	}
	
	/*
	@Test
	public void getClientes() throws IOException, InterruptedException {
		client = HttpClient.newHttpClient();
		request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/costoLlamadaCliente/123/mes/1")).build();
		response = client.send(request, HttpResponse.BodyHandlers.ofString());
		Assert.assertEquals(response.statusCode(), 200);
	}
	*/
>>>>>>> c0e1545286515c829d1a31fdc97693d1e9abe5fd

	@AfterClass
	public void endTest() {
		persistenciaBDClientes.borrarTodosLosDatosDeClientes();
		persistenciaBDCDR.borrarTodosLosDatosDeCDR();
		persistenciaBDClientes.borrarTodosLosDatosDeNumerosAmigos();
	}
}
