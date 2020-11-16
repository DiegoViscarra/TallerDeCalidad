package controladoresTest;

import org.testng.annotations.Test;

import casosDeUso.IRegistroCDR;
import casosDeUso.IRepositorioCDR;
import casosDeUso.RegistroCDR;
import controladores.ControladorCargaCDRs;
import repositorios.RepositorioCDR;

import java.io.File;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;

import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class ControladorCargaCDRsTest {
	IRegistroCDR registroCDR;
	IRepositorioCDR repositorioCDR;

	@Test
	public void getRegistrosCargados() throws Exception, InterruptedException {
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet("http://localhost:8080/registrosCargados");
		HttpResponse response = client.execute(request);
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	}

	@Test
	public void postApiSubmit() throws Exception, InterruptedException {
		repositorioCDR.vaciarRegistros();
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost("http://localhost:8080/api/submit");
		File uploadFile = new File("FilesTest/DatosParaCargar.csv");
		FileBody uploadFilePart = new FileBody(uploadFile);
		MultipartEntity reqEntity = new MultipartEntity();
		reqEntity.addPart("myFile", uploadFilePart);
		httpPost.setEntity(reqEntity);

		HttpResponse response = httpclient.execute(httpPost);
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
		Assert.assertEquals(7, registroCDR.obtenerRegistrosNoTarificados().size());
	}

	@Test
	public void postApiSubmitSinArchivo() throws Exception, InterruptedException {
		repositorioCDR.vaciarRegistros();
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost("http://localhost:8080/api/submit");
		HttpResponse response = httpclient.execute(httpPost);
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
		Assert.assertEquals(0, registroCDR.obtenerRegistrosNoTarificados().size());
	}

	@BeforeClass
	public void beforeClass() {
		repositorioCDR = new RepositorioCDR();
		registroCDR = new RegistroCDR(repositorioCDR);
				new ControladorCargaCDRs(registroCDR);
	}

	@BeforeMethod
	public void beforeMethod() {

	}

	@AfterClass
	public void afterClass() {

	}
}
