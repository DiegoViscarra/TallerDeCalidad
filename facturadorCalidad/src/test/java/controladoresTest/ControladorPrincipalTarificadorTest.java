package controladoresTest;

import org.testng.annotations.Test;
import controladores.ControladorPrincipalTarificador;
import entidades.Cliente;
import entidades.PlanPrepago;

import static spark.Spark.port;
import static spark.Spark.stop;
import org.testng.annotations.BeforeSuite;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;

import org.testng.annotations.BeforeClass;

public class ControladorPrincipalTarificadorTest {
	ControladorPrincipalTarificador controladorPrincipalTarificador;
	
	@AfterSuite
	public void afterSuite() {
		stop();
	}
	
	@BeforeSuite
	public void beforeSuite() throws InterruptedException {
		port(8080);
	}
	

	@Test
	public void revisarDeBDClientesExistentesDespueDeGuardarUno() {
		ControladorPrincipalTarificador.persistenciaClientes.borrarTodosLosDatosDeClientes();
		Cliente cliente = new Cliente("Ana", "2", 456);
		PlanPrepago planPrepago = new PlanPrepago();
		cliente.setPlan(planPrepago);
		cliente.setTipoPlan("PREPAGO");
		ControladorPrincipalTarificador.persistencia.persistirEnBDClientes(cliente);
		ControladorPrincipalTarificador.inicializar();
		int result = ControladorPrincipalTarificador.devolverClientesDeBD().size();
		ControladorPrincipalTarificador.persistenciaClientes.borrarTodosLosDatosDeClientes();
		Assert.assertEquals(1, result);
	}

	@Test
	public void revisarDeBDClientesExistentes() {
		ControladorPrincipalTarificador.revisarDeBDClientesExistentes();
}

	@BeforeClass
	public void beforeClass() {
		controladorPrincipalTarificador = new ControladorPrincipalTarificador();
	}
	
}
