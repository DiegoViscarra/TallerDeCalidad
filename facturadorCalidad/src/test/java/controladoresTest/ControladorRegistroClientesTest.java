package controladoresTest;

import org.testng.annotations.Test;

import casosDeUso.IPersistencia;
import casosDeUso.IPersistenciaBDClientes;
import casosDeUso.IRegistroCDR;
import casosDeUso.IRegistroClientes;
import casosDeUso.IRepositorioCDR;
import casosDeUso.IRepositorioCliente;
import casosDeUso.ITarificacion;
import casosDeUso.Persistencia;
import casosDeUso.RegistroCDR;
import casosDeUso.RegistroClientes;
import casosDeUso.Tarificacion;
import controladores.ControladorCargaCDRs;
import controladores.ControladorRegistroClientes;
import repositorios.PersistenciaArchivos;
import repositorios.PersistenciaBDCDR;
import repositorios.PersistenciaBDClientes;
import repositorios.RepositorioCDR;
import repositorios.RepositorioCliente;

import static spark.Spark.port;
import static spark.Spark.stop;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class ControladorRegistroClientesTest {
	HttpClient client;
	HttpRequest request;
	HttpResponse<?> response;
	IRepositorioCliente repositorioCliente;

	@Test
	public void getCargarClientes() throws Exception, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/cargarClientes")).build();
		response = client.send(request, HttpResponse.BodyHandlers.ofString());
		Assert.assertEquals(response.statusCode(), 200);
	}

	@Test
	public void postApiSubmitCliente() throws Exception, InterruptedException {
		Path data = Paths.get(
				"D:\\II-2020\\GESTION DE CALIDAD DE SISTEMAS (CANEDO ARZE CARLOS OSWALDO)\\Practica 3\\2\\facturadorCalidad\\src\\test\\java\\controladoresTest\\clientes.csv");
		request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/api/submitCliente"))
				.POST(BodyPublishers.ofFile(data)).build();
		request.uri().getRawPath();
		response = client.send(request, BodyHandlers.discarding());
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(3, repositorioCliente.devolverClientes().size());

	}

	@Test
	public void postApiSubmitClienteSinArchivo() throws Exception, InterruptedException {
		request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/api/submitCliente"))
				.POST(BodyPublishers.noBody()).build();
		request.uri().getRawPath();
		response = client.send(request, BodyHandlers.discarding());
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(0, repositorioCliente.devolverClientes().size());
	}

	@BeforeClass
	public void beforeClass() {
		IRepositorioCDR repositorioCDR = new RepositorioCDR();
		IPersistenciaBDClientes persistenciaClientes = new PersistenciaBDClientes();
		IPersistencia persistencia = new Persistencia(new PersistenciaBDCDR(), persistenciaClientes,
				new PersistenciaArchivos(), repositorioCDR);
		repositorioCliente = new RepositorioCliente(persistencia);
		IRegistroClientes registroClientes = new RegistroClientes(repositorioCliente, persistenciaClientes);
		port(8080);
		new ControladorRegistroClientes(registroClientes);
	}

	@BeforeMethod
	public void beforeMethod() {
		client = HttpClient.newBuilder().build();
		response = null;
	}
	
	@AfterClass
	public void afterClass() {
		stop();
	}

}
