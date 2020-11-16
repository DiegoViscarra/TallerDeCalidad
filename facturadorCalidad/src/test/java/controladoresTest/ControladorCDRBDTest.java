package controladoresTest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
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
import casosDeUso.RegistroCDR;
import casosDeUso.Tarificacion;
import controladores.ControladorCDRs;
import entidades.CDR;
import entidades.Cliente;
import entidades.PlanPostpago;
import entidades.PlanPrepago;
import modelos.CDRModelo;
import repositorios.PersistenciaArchivos;
import repositorios.PersistenciaBDCDR;
import repositorios.PersistenciaBDClientes;
import repositorios.RepositorioCDR;
import repositorios.RepositorioCliente;

public class ControladorCDRBDTest {
	public ITarificacion tarificacion = null;
	public IPersistencia persistencia = null;
	public IRegistroCDR registroCDR = null;
	public IRepositorioCliente repositorio = null;
	public ControladorCDRs controlador = null;
	public IPersistenciaBDCDR persistenciaBDCDR;
	public IPersistenciaBDClientes persistenciaBDClientes;
	public IPersistenciaArchivos persistenciaArchivos;
	public IRepositorioCDR repositorioCDR;
	
	@BeforeClass
	public void initControlador() {
		persistenciaBDCDR = new PersistenciaBDCDR();
		persistenciaBDClientes = new PersistenciaBDClientes();
		persistenciaArchivos = new PersistenciaArchivos();
		repositorioCDR = new RepositorioCDR();
		
		persistencia = new Persistencia(persistenciaBDCDR, persistenciaBDClientes, persistenciaArchivos, repositorioCDR);
		repositorio = new  RepositorioCliente(persistencia);
		tarificacion = new Tarificacion(repositorio);
		registroCDR = new RegistroCDR(repositorioCDR);
		
		controlador = new ControladorCDRs(tarificacion, persistencia, registroCDR);
		
		//CrearClientes
		Cliente cliente1 = new Cliente("Sergio", "5", 123);
		IPlan plan1 = new PlanPrepago();
		cliente1.setPlan(plan1);
		cliente1.setTipoPlan("PREPAGO");
		
		Cliente cliente2 = new Cliente("Ana", "9", 789);
		IPlan plan2 = new PlanPostpago();
		cliente2.setPlan(plan2);
		cliente2.setTipoPlan("POSTPAGO");
		persistenciaBDClientes.poblarTablaClientes(cliente1);
		persistenciaBDClientes.poblarTablaClientes(cliente2);
		repositorio.registrarNuevoClientePlanNormal(cliente1, "PREPAGO");
		repositorio.registrarNuevoClientePlanNormal(cliente2, "POSTPAGO");
	}
	
	@BeforeMethod
	public void initGuardarRegistrosEnBD() {
		persistenciaBDCDR.borrarTodosLosDatosDeCDR();
		repositorioCDR.registrarCDRs("123;456;2:45;3/1/2020;11:00\\r\\n\"");
		repositorioCDR.registrarCDRs("789;456;1:45;3/1/2020;11:00\\r\\n\"");
		ControladorCDRs.guardarEnBD(persistencia, tarificacion, registroCDR);
	}
	
	@Test
	public void cdrGuardadoBDTest() {
		ArrayList<CDRModelo> registros = ControladorCDRs.devolverDeBDCDRModelo(persistencia);
		Assert.assertEquals(registros.size(), 2);
		for(CDRModelo cdr : registros) {
			Assert.assertNotEquals(cdr.getCostoDeLlamada(), -1.0);
		}
	}
	
	@Test
	public void filtroPorFechaTest() {
		Date myDate = new Date();
		String fecha= new SimpleDateFormat("yyyy-MM-dd").format(myDate);
		ArrayList<CDRModelo> registros = ControladorCDRs.devolverCDRModeloFiltrado(fecha, persistencia);
		Assert.assertEquals(registros.size(), 2);
	}
	
	@AfterMethod
	public void emptyRepositorio() {
		repositorioCDR.vaciarRegistros();
	}
	
	
	@AfterClass
	public void endTest() {
		persistenciaBDClientes.borrarTodosLosDatosDeClientes();
		persistenciaBDCDR.borrarTodosLosDatosDeCDR();
		persistenciaBDClientes.borrarTodosLosDatosDeNumerosAmigos();
	}
}
