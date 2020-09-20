package repositorios;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import entidades.CDR;
import repositorios.RepositorioCDR;

class RepositorioCDRTest {
	private final RepositorioCDR repositorio = new RepositorioCDR();
	@Test
	void testRecuperacionRegistrosCargados() {
		repositorio.registrarCDRs("123;456;15:26;3/1/2020;11:00\r\n");
		CDR registro = new CDR(123,456,"15:26","3/1/2020","11:00");
		ArrayList<CDR> registros = repositorio.obtenerRegistrosNoTarificados();
		assertEquals(registros.size(), 1);
		assertEquals(registros.get(0).getNumeroTelefonoOrigen(), registro.getNumeroTelefonoOrigen());
		assertEquals(registros.get(0).getNumeroTelefonoDestino(), registro.getNumeroTelefonoDestino());
	}
	
	@Test
	void testVaciarRegistrosCargados() {
		repositorio.registrarCDRs("123;456;15:26;3/1/2020;11:00\r\n");
		ArrayList<CDR> registros = repositorio.obtenerRegistrosNoTarificados();
		assertEquals(registros.size(), 1);
		repositorio.vaciarRegistros();
		registros = repositorio.obtenerRegistrosNoTarificados();
		assertEquals(registros.size(), 0);
		
	}
}
