package controladoresTest;

import org.testng.annotations.Test;
import controladores.ControladorPrincipalTarificador;
import entidades.Cliente;
import entidades.PlanPrepago;
import static spark.Spark.stop;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class ControladorPrincipalTarificadorTest {
	ControladorPrincipalTarificador controladorPrincipalTarificador;
	@BeforeClass
	public void init() throws InterruptedException {
		stop();
		Thread.sleep(2000);
	}

	@Test
	public void revisarDeBDClientesExistentesDespueDeGuardarUno() {
		ControladorPrincipalTarificador.persistenciaClientes.borrarTodosLosDatosDeClientes();
		Cliente cliente = new Cliente("Ana", "2", 456);
		PlanPrepago planPrepago = new PlanPrepago();
		cliente.setPlan(planPrepago);
		cliente.setTipoPlan("PREPAGO");
		ControladorPrincipalTarificador.persistencia.persistirEnBDClientes(cliente);
		ControladorPrincipalTarificador.main(null);
		int result = ControladorPrincipalTarificador.devolverClientesDeBD().size();
		Assert.assertEquals(1, result);
	}
	
	@Test
	public void revisarDeBDClientesExistentes() {
		ControladorPrincipalTarificador.revisarDeBDClientesExistentes();
		// controladorPrincipalTarificador.persistencia.persistirEnBDClientes(cliente);
	}


	@BeforeClass
	public void beforeClass() {
		ControladorPrincipalTarificador.persistenciaClientes.borrarTodosLosDatosDeClientes();
		controladorPrincipalTarificador = new ControladorPrincipalTarificador();
	}

	@BeforeMethod
	public void beforeMethod() {

	}

	@AfterClass
	public void afterClass() {
		ControladorPrincipalTarificador.persistenciaClientes.borrarTodosLosDatosDeClientes();
	}
}
