package casosDeUso;

import java.util.ArrayList;

import org.javatuples.Pair;

import entidades.CDR;
import entidades.Cliente;
import io.vavr.Tuple2;
import modelos.CDRModelo;
import modelos.ClienteModelo;
import modelos.FacturaModelo;
import repositorios.PersistenciaArchivos;
import repositorios.PersistenciaBDCDR;
import repositorios.PersistenciaBDClientes;
import repositorios.RepositorioCDR;

public class Persistencia implements IPersistencia {

	private IPersistenciaBDCDR persistenciaBDCDR;
	private IPersistenciaBDClientes persistenciaBDClientes;
	private IPersistenciaArchivos persistenciaArchivos;
	private IRepositorioCDR repositorioCDR;
	public Persistencia(IPersistenciaBDCDR persistenciaBDCDR,
						IPersistenciaBDClientes persistenciaBDClientes, 
						IPersistenciaArchivos persistenciaArchivos, 
						IRepositorioCDR repositorioCDR){
		this.persistenciaBDCDR = persistenciaBDCDR;
		this.persistenciaBDClientes=persistenciaBDClientes;
		this.persistenciaArchivos = persistenciaArchivos;
		this.repositorioCDR = repositorioCDR;
		persistenciaBDCDR.crearTabla();
		persistenciaBDClientes.crearTabla();
	}
	@Override
	public void persistirEnBDCdr(ArrayList<CDR> registrosTelefonicos) {
		for(CDR registro : registrosTelefonicos)
		{
			if(registro.getCostoDeLlamada() > - 1)
			{
				persistenciaBDCDR.poblarTabla(registro);
			}
		}
		repositorioCDR.vaciarRegistros();
	}

	@Override
	public void persistirEnArchivo(ArrayList<CDR> registrosTelefonicos) {
		persistenciaArchivos.serializar(registrosTelefonicos);
		repositorioCDR.vaciarRegistros();
	}
	@Override
	public ArrayList<CDRModelo> mostrarDeBDCDRs() {
		return persistenciaBDCDR.mostrarTabla("SELECT * FROM CDR;");
	}
	@Override
	public void persistirEnBDClientes(Cliente cliente) {
		persistenciaBDClientes.poblarTablaClientes(cliente);
		
	}
	@Override
	public ArrayList<ClienteModelo> mostrarDeBDClientes() {
		return persistenciaBDClientes.mostrarTablaClientes();
	}
	
	@Override
	public void persistirEnBDNumerosAmigos(ArrayList<Integer> numerosAmigos, Integer numeroCliente) {
		persistenciaBDClientes.poblarTablaClientesConNumerosAmigos(numerosAmigos, numeroCliente);
		
	}
	@Override
	public ArrayList<ClienteModelo> mostrarDeBDClientesAmigos() {
		return persistenciaBDClientes.mostrarTablaClientesConNumerosAmigos();
	}
	@Override
	public ArrayList<Pair<String,ArrayList<CDR>>> deserializarArchivos() {
		return persistenciaArchivos.deserializarTodosLosArchivos();		
	}
	@Override
	public ArrayList<CDRModelo> mostrarDeBDCDRsFiltradosPor(String Fecha) {
		return persistenciaBDCDR.mostrarTabla("SELECT * FROM CDR WHERE fechaTarificacion = " + "'" + Fecha +"'"+";" );
	}
	
	@Override
	public FacturaModelo obtenerFacturaDeMesParaCliente(int numeroTelefonico, String mes) {
		double sumaTotal = 0;
		ArrayList<CDRModelo> costoRegistrosTelefonicos = persistenciaBDCDR.mostrarTabla("SELECT * FROM CDR WHERE numeroTelefonoOrigen = " + numeroTelefonico +";" );
		if(esClienteRegistrado(costoRegistrosTelefonicos))
		{			
			ArrayList<CDRModelo> registrosDelMes = filtrarRegistrosPorMes(mes, costoRegistrosTelefonicos);
			sumaTotal = obtenerSumaTotalDeRegistrosDelMes(registrosDelMes);
		}
		else
			numeroTelefonico = 0;
		mes = obtenerMesLiteral(Integer.parseInt(mes));
		return new FacturaModelo(numeroTelefonico, mes, sumaTotal);
	}
	
	private boolean esClienteRegistrado(ArrayList<CDRModelo> costoRegistrosTelefonicos) {
		return costoRegistrosTelefonicos.size() > 0;
	}
	
	private double obtenerSumaTotalDeRegistrosDelMes(ArrayList<CDRModelo> registrosDelMes) {
		double sumaTotal = 0;
		for(CDRModelo cdr: registrosDelMes) {
			sumaTotal = sumaTotal + cdr.getCostoDeLlamada(); 
		}
		return sumaTotal;
	}
	
	private ArrayList<CDRModelo> filtrarRegistrosPorMes(String mes, ArrayList<CDRModelo> conjuntoCDRs){
		ArrayList<CDRModelo> registrosFiltradosPorMes = new ArrayList<CDRModelo>();
		for(CDRModelo cdr : conjuntoCDRs) {
			String fecha = cdr.getFecha();
			String[] fechaDividida = fecha.split("/");
			if(coincidenEnMes(mes, fechaDividida))
				registrosFiltradosPorMes.add(cdr);
		}
		return registrosFiltradosPorMes;
		
	}
	private boolean coincidenEnMes(String mes, String[] fechaDividida) {
		return Integer.parseInt(mes) == Integer.parseInt(fechaDividida[1]);
	}
	
	private String obtenerMesLiteral(Integer mes) {
		String mesLiteral = "";
		String meses[] = {"No valido","Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
		if(esNumeroDeMesInvalido(mes))
			mesLiteral = "Mes no valido";
		else
			mesLiteral = meses[mes];
		return mesLiteral;
	}
	private boolean esNumeroDeMesInvalido(Integer mes) {
		return mes > 12 || mes < 1;
	}

}
