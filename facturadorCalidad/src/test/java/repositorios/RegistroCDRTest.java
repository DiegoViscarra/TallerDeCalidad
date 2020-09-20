package repositorios;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import casosDeUso.RegistroCDR;
import entidades.CDR;

class RegistroCDRTest {
	private final RegistroCDR registroCDR = new RegistroCDR(new RepositorioCDR());
	@Test
	void testRecuperarRegistroGuardado() {
		registroCDR.guardarTemporalmenteCDRs("123;456;15:26;3/1/2020;11:00\r\n");
		ArrayList<CDR> registros = registroCDR.obtenerRegistrosNoTarificados();
		assertEquals(registros.size(),1);
		assertEquals(registros.get(0).getNumeroTelefonoOrigen(), 123);
		assertEquals(registros.get(0).getNumeroTelefonoDestino(), 456);
	}

}
