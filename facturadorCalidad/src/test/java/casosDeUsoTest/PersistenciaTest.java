package casosDeUsoTest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import casosDeUso.IPersistenciaArchivos;
import casosDeUso.IPersistenciaBDCDR;
import casosDeUso.IPersistenciaBDClientes;
import casosDeUso.IPlan;
import casosDeUso.IRepositorioCDR;
import casosDeUso.Persistencia;
import entidades.CDR;
import entidades.Cliente;
import entidades.PlanPostpago;
import entidades.PlanWow;
import modelos.CDRModelo;
import modelos.ClienteModelo;
import modelos.FacturaModelo;
import repositorios.PersistenciaArchivos;
import repositorios.PersistenciaBDCDR;
import repositorios.PersistenciaBDClientes;
import repositorios.RepositorioCDR;

public class PersistenciaTest {
	
	public Persistencia persistencia;
	public IPersistenciaBDCDR persistenciaBDCDR;
	public IPersistenciaBDClientes persistenciaBDClientes;
	public IPersistenciaArchivos persistenciaArchivos;
	public IRepositorioCDR repositorioCDR;
	public ArrayList<CDRModelo> registros, filtrados;
	@BeforeClass
	public void initPersistencia() {
		persistenciaBDCDR = new PersistenciaBDCDR();
		persistenciaBDClientes = new PersistenciaBDClientes();
		persistenciaArchivos = new PersistenciaArchivos();
		repositorioCDR = new RepositorioCDR();
		
		persistencia = new Persistencia(persistenciaBDCDR, persistenciaBDClientes, persistenciaArchivos, repositorioCDR);
	}

	@BeforeMethod
	public void initBD() {
		persistenciaBDClientes.borrarTodosLosDatosDeClientes();
		persistenciaBDClientes.borrarTodosLosDatosDeNumerosAmigos();
		persistenciaBDCDR.borrarTodosLosDatosDeCDR();
		Cliente cliente = new Cliente("Sergio", "1", 234);
		IPlan plan = new PlanPostpago();
		cliente.setPlan(plan);
		cliente.setTipoPlan("POSTPAGO");
		persistenciaBDClientes.poblarTablaClientes(cliente);
		CDR uno = new CDR(234, 345, "02:45", "11/03/2020", "23:00");
		uno.setCosto(2.75);
		CDR dos = new CDR(234, 345, "06:45", "11/03/2020", "12:00");
		dos.setCosto(6.75);
		CDR tres = new CDR(234, 345, "01:45", "11/10/2020", "15:00");
		tres.setCosto(1.75);
		persistenciaBDCDR.poblarTabla(uno);
		persistenciaBDCDR.poblarTabla(dos);
		persistenciaBDCDR.poblarTabla(tres);
	}
	
	@Test
	public void obtenerFacturaDelMesParaClienteTest() {
		System.out.print(persistenciaBDCDR.mostrarTabla("SELECT * FROM CDR ;"));
		FacturaModelo factura = persistencia.obtenerFacturaDeMesParaCliente(234, "03");
		System.out.print(factura.montoMes());
		Assert.assertEquals(factura.getNumeroTelefonico(),(Integer)234);
		Assert.assertEquals((Double)factura.montoMes(),(Double)9.5);
		
	}  
	
	@Test
	public void obtenerFacturaConClienteNoRegistrado() {
		FacturaModelo factura = persistencia.obtenerFacturaDeMesParaCliente(567, "3");
		Assert.assertEquals((Integer)0, factura.getNumeroTelefonico());
		Assert.assertEquals((Double)factura.montoMes(),(Double)0.0);
		
	}
	
	@AfterMethod
	public void tearBD() {
		persistenciaBDClientes.borrarTodosLosDatosDeClientes();
		persistenciaBDClientes.borrarTodosLosDatosDeNumerosAmigos();
		persistenciaBDCDR.borrarTodosLosDatosDeCDR();
	}
	
}
