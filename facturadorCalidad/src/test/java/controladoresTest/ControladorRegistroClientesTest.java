package controladoresTest;

import org.testng.annotations.Test;

import casosDeUso.IPersistencia;
import casosDeUso.IPersistenciaBDClientes;
import casosDeUso.IRegistroClientes;
import casosDeUso.IRepositorioCDR;
import casosDeUso.IRepositorioCliente;
import casosDeUso.Persistencia;
import casosDeUso.RegistroClientes;
import controladores.ControladorRegistroClientes;
import repositorios.PersistenciaArchivos;
import repositorios.PersistenciaBDCDR;
import repositorios.PersistenciaBDClientes;
import repositorios.RepositorioCDR;
import repositorios.RepositorioCliente;
import java.io.File;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

public class ControladorRegistroClientesTest {
	IPersistenciaBDClientes persistenciaClientes;
	IRegistroClientes registroClientes;
	IRepositorioCliente repositorioCliente;

	@Test
	public void getRegistrosCargados() throws Exception, InterruptedException {
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet("http://localhost:8080/cargarClientes");
		HttpResponse response = client.execute(request);
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	}
	
	@Test
	public void postApiSubmitSinArchivo() throws Exception, InterruptedException {
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost("http://localhost:8080/api/submitCliente");
		HttpResponse response = httpclient.execute(httpPost);
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
		}

	@Test
	public void postApiSubmit() throws Exception, InterruptedException {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost("http://localhost:8080/api/submitCliente");
		File uploadFile = new File("FilesTest/Clientes.csv");
		FileBody uploadFilePart = new FileBody(uploadFile);
		MultipartEntity reqEntity = new MultipartEntity();
		reqEntity.addPart("myFileClient", uploadFilePart);
		httpPost.setEntity(reqEntity);
		HttpResponse response = httpclient.execute(httpPost);
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
		Assert.assertEquals(3, persistenciaClientes.mostrarTablaClientes().size());
	}

	@BeforeClass
	public void beforeClass() {
		IRepositorioCDR repositorioCDR = new RepositorioCDR();
		persistenciaClientes = new PersistenciaBDClientes();
		IPersistencia persistencia = new Persistencia(new PersistenciaBDCDR(), persistenciaClientes, new PersistenciaArchivos(), repositorioCDR);
		repositorioCliente = new RepositorioCliente(persistencia);
		registroClientes = new RegistroClientes(repositorioCliente,persistenciaClientes);
		
		new ControladorRegistroClientes(registroClientes);
	}

}
