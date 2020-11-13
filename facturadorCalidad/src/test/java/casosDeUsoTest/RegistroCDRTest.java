package casosDeUsoTest;

import java.util.ArrayList;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import casosDeUso.RegistroCDR;
import entidades.CDR;
import repositorios.RepositorioCDR;

public class RegistroCDRTest {
	RegistroCDR registroCDR;
	@Test
	public void guardarTemporalmenteCDRs() {
		registroCDR.guardarTemporalmenteCDRs("123;456;15:26;3/1/2020;11:00\r\n");
		ArrayList<CDR> registros = registroCDR.obtenerRegistrosNoTarificados();
		Assert.assertEquals(registros.size(),1);
		Assert.assertEquals(registros.get(0).getNumeroTelefonoOrigen(), 123);
		Assert.assertEquals(registros.get(0).getNumeroTelefonoDestino(), 456);
	}

	@BeforeMethod
	public void beforeMethod() {
		registroCDR = new RegistroCDR(new RepositorioCDR());
	}
}
