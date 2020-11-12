package casosDeUsoTest;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import casosDeUso.IRepositorioCliente;
import casosDeUso.Tarificacion;
import entidades.CDR;
import entidades.Cliente;
import repositorios.RepositorioCliente;

public class TarificacionTest {
	public IRepositorioCliente repositorio = null;
	public ArrayList<CDR> listaRegistros, listaComparacion;
	public Tarificacion tarificador = null;
	public CDR registro1, registro2;
	public Cliente cliente;
	
	@BeforeMethod
	public void init() {
		repositorio = new RepositorioCliente(null);
		tarificador = new Tarificacion(repositorio);
		
		cliente = new Cliente("Pedro", "1", 123);
		repositorio.registrarNuevoClientePlanNormal(cliente, "POSTPAGO");
		
		registro1 = new CDR(123, 345, "02:45", "11/10/2020", "23:00");
	}
	
	@Test
	public void CDRNoTarificadoTest() {
		Assert.assertEquals(true, tarificador.noEstaTarificado(registro1));
	}
	
	@Test
	public void tarificarRegistroTest() {
		Assert.assertEquals(Double.valueOf(2.75), (Double)tarificador.tarificar(registro1, cliente));
	}
	
	@BeforeMethod
	public void initTarificacionRegistrosTest() {
		listaRegistros = new ArrayList<CDR>();
		CDR uno = new CDR(123, 345, "02:45", "11/10/2020", "23:00");
		CDR dos = new CDR(456, 345, "06:15", "11/10/2020", "12:00");
		CDR tres = new CDR(789, 345, "01:30", "11/10/2020", "15:00");
		CDR cuatro = new CDR(123, 345, "01:30", "11/10/2020", "15:00");
		cuatro.setCosto(0);
		
		listaRegistros.add(uno);
		listaRegistros.add(dos);
		listaRegistros.add(tres);
		listaRegistros.add(cuatro);
		
		Cliente c1 = new Cliente("Ana", "2", 456);
		Cliente c2 = new Cliente("Pablo", "3", 789);
		repositorio.registrarNuevoClientePlanNormal(c1, "PREPAGO");
		repositorio.registrarNuevoClientePlanNormal(c2, "POSTPAGO");
		
		listaComparacion = new ArrayList<CDR>();
		CDR unoT = new CDR(123, 345, "02:45", "11/10/2020", "23:00");
		CDR dosT = new CDR(456, 345, "06:15", "11/10/2020", "12:00");
		CDR tresT = new CDR(789, 345, "01:30", "11/10/2020", "15:00");
		unoT.setCosto(2.75);
		dosT.setCosto(9.0625);
		tresT.setCosto(1.5);
		listaComparacion.add(unoT);
		listaComparacion.add(dosT);
		listaComparacion.add(tresT);
		listaComparacion.add(cuatro);
	}
	
	@Test
	public void tarificarRegistrosTest() {
		ArrayList<CDR> registrosTarificados = tarificador.tarificarRegistros(listaRegistros);
	
		Assert.assertEquals(listaComparacion.size(), registrosTarificados.size());
		int index = 0;
		for(CDR cdr : registrosTarificados) {
			Assert.assertEquals((Double)cdr.getCostoDeLlamada(), (Double)listaComparacion.get(index).getCostoDeLlamada());
			index = index + 1;
		}
		
	}
	
	@BeforeMethod
	public void initNoEstaTarificadoPathTest() {
		registro2 = new CDR(123, 345, "02:45", "11/10/2020", "23:00");
		registro2.setCosto(2.75);
	}
	
	@Test
	public void CDRTarificadoTest() {
		Assert.assertEquals(false, tarificador.noEstaTarificado(registro2));
	}

}
