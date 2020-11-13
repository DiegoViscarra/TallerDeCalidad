package controladoresTest;

import org.testng.annotations.Test;

import casosDeUso.IRegistroCDR;
import casosDeUso.IRepositorioCDR;
import casosDeUso.RegistroCDR;
import controladores.ControladorCargaCDRs;
import repositorios.RepositorioCDR;

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

public class ControladorCargaCDRsTest {
	HttpClient client;
	HttpRequest request;
	HttpResponse<?> response;
	IRegistroCDR registroCDR;

	@Test
	public void getRegistrosCargados() throws Exception, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/registrosCargados")).build();
		response = client.send(request, HttpResponse.BodyHandlers.ofString());
		Assert.assertEquals(response.statusCode(), 200);
	}

	@Test
	public void postApiSubmit() throws Exception, InterruptedException {
		Path data = Paths.get(
				"D:\\II-2020\\GESTION DE CALIDAD DE SISTEMAS (CANEDO ARZE CARLOS OSWALDO)\\Practica 3\\2\\facturadorCalidad\\src\\test\\java\\controladoresTest\\DatosParaCargar.csv");
		request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/api/submit"))
				.POST(BodyPublishers.ofFile(data)).build();
		request.uri().getRawPath();
		response = client.send(request, BodyHandlers.discarding());
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(3, registroCDR.obtenerRegistrosNoTarificados().size());
	}

	@Test
	public void postApiSubmitSinArchivo() throws Exception, InterruptedException {
		request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/api/submit"))
				.POST(BodyPublishers.noBody()).build();
		request.uri().getRawPath();
		response = client.send(request, BodyHandlers.discarding());
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(0, registroCDR.obtenerRegistrosNoTarificados().size());
	}

	@BeforeClass
	public void beforeClass() {
		IRepositorioCDR repositorioCDR = new RepositorioCDR();
		registroCDR = new RegistroCDR(repositorioCDR);
		port(8080);
		new ControladorCargaCDRs(registroCDR);
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
