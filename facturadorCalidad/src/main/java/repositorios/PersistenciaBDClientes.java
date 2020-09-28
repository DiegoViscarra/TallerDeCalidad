package repositorios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Logger;

import casosDeUso.IPersistenciaBDClientes;
import entidades.Cliente;
import modelos.ClienteModelo;

public class PersistenciaBDClientes implements IPersistenciaBDClientes {
	private static final ConnectionDB connectionDB= new ConnectionDB();
	private static final String ORG_SQLITE_JDBC = "org.sqlite.JDBC";
	private final static Logger LOGGER = Logger.getLogger(PersistenciaBDClientes.class.getName());
	Connection conexionBD = null;
	Statement enunciadoSQL = null;
	
	@Override
	public void crearTabla() {
		try {
			Class.forName(ORG_SQLITE_JDBC);
			conexionBD = DriverManager.getConnection(connectionDB.getConnection());
			
			enunciadoSQL = conexionBD.createStatement();
			String sentenciaSQLClientes = "CREATE TABLE IF NOT EXISTS Clientes " +
					"(nombre CHAR(30)     NOT NULL," +
					" ci    CHAR(30) PRIMARY KEY    NOT NULL, " + 
					" numeroTelefonico        INT    NOT NULL, " + 
					" tipoPlan         CHAR(20)    NOT NULL,"+
					" fechaRegistro          CHAR(20)    NOT NULL)";
			
			String sentenciaSQLAmigos = "CREATE TABLE IF NOT EXISTS NumerosAmigos " +
					"( id INTEGER PRIMARY KEY NOT NULL," + 
					" numeroTelefonico        INT    NOT NULL, " + 
					" fechaRegistro          CHAR(20)    NOT NULL,"+
					" numeroAmigo1        INT    NOT NULL, " + 
					" numeroAmigo2        INT    NOT NULL, " + 
					" numeroAmigo3        INT    NOT NULL, " + 
					" numeroAmigo4        INT    NOT NULL )";
				
			enunciadoSQL.executeUpdate(sentenciaSQLClientes);
			enunciadoSQL.executeUpdate(sentenciaSQLAmigos);
			enunciadoSQL.close();
			conexionBD.close();
		} catch ( Exception e ) {
			LOGGER.severe(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	@Override
	public void poblarTablaClientes(Cliente cliente) {
		try {
			Class.forName(ORG_SQLITE_JDBC);
			conexionBD = DriverManager.getConnection(connectionDB.getConnection());
			conexionBD.setAutoCommit(false);
			LOGGER.info("Opened Clientes successfully");

			String sentenciaSQL = "INSERT INTO CLientes (nombre,ci,numeroTelefonico,tipoPlan,fechaRegistro)" + "values(?,?,?,?,?)";


			try(PreparedStatement enunciadoPreparadoClientes = conexionBD.prepareStatement(sentenciaSQL);){
			enunciadoPreparadoClientes.setString(1,cliente.getNombre());
			enunciadoPreparadoClientes.setString(2,cliente.getCi());
			enunciadoPreparadoClientes.setInt(3,cliente.getNumeroTelefonico());
			enunciadoPreparadoClientes.setString(4,cliente.getTipoPlan());
			SimpleDateFormat formatoFechaCompleta = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
			enunciadoPreparadoClientes.setString(5,formatoFechaCompleta.format(Calendar.getInstance().getTime()).toString());
			enunciadoPreparadoClientes.executeUpdate();}
			conexionBD.commit();
			conexionBD.close();
			LOGGER.info("Clientes closed successfully");
		} catch ( Exception e ) {
			LOGGER.info("entra al errror");
			LOGGER.severe(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		LOGGER.info("Clientes created successfully");
	}
	
	@Override
	public void poblarTablaClientesConNumerosAmigos(ArrayList<Integer> numerosAmigos, int numeroTelefonico) {
		try {
			Class.forName(ORG_SQLITE_JDBC);
			conexionBD = DriverManager.getConnection(connectionDB.getConnection());
			conexionBD.setAutoCommit(false);
			LOGGER.info("Opened numerosAmigos successfully");
			
			String sentenciaSQL = "INSERT INTO NumerosAmigos (numeroTelefonico,fechaRegistro,numeroAmigo1,numeroAmigo2,numeroAmigo3,numeroAmigo4)" + "values(?,?,?,?,?,?)";


			try(PreparedStatement enunciadoPreparadoClientesConAmigos = conexionBD.prepareStatement(sentenciaSQL);){
			enunciadoPreparadoClientesConAmigos.setInt(1,numeroTelefonico);
			SimpleDateFormat formatoFechaCompleta = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
			enunciadoPreparadoClientesConAmigos.setString(2,formatoFechaCompleta.format(Calendar.getInstance().getTime()).toString());
			enunciadoPreparadoClientesConAmigos.setInt(3,numerosAmigos.get(0));
			enunciadoPreparadoClientesConAmigos.setInt(4,numerosAmigos.get(1));
			enunciadoPreparadoClientesConAmigos.setInt(5,numerosAmigos.get(2));
			enunciadoPreparadoClientesConAmigos.setInt(6,numerosAmigos.get(3));
			enunciadoPreparadoClientesConAmigos.executeUpdate();}
			conexionBD.commit();
			conexionBD.close();
			LOGGER.info("NumerosAmigos closed successfully");
		} catch ( Exception e ) {
			LOGGER.info("entra al errror");
			LOGGER.severe( e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		LOGGER.info("NumerosAmigos created successfully");
	}

	@Override
	public ArrayList<ClienteModelo> mostrarTablaClientes() {
		ArrayList<ClienteModelo> clientesRecuperados = new ArrayList<ClienteModelo>();
		try {
			Class.forName(ORG_SQLITE_JDBC);
			conexionBD = DriverManager.getConnection(connectionDB.getConnection());
			conexionBD.setAutoCommit(false);
			LOGGER.info("Opened Clientes successfully");

			enunciadoSQL = conexionBD.createStatement();
			try(ResultSet resultadoConsulta = enunciadoSQL.executeQuery( "SELECT * FROM Clientes;" );){

			while ( resultadoConsulta.next() ) {
				String nombre = resultadoConsulta.getString("nombre");
				String ci = resultadoConsulta.getString("ci");
				int numeroTelefonico = resultadoConsulta.getInt("numeroTelefonico");
				String tipoPlan = resultadoConsulta.getString("tipoPlan");
				String fechaRegistro = resultadoConsulta.getString("fechaRegistro");
				
				ClienteModelo modelo = new ClienteModelo(nombre, ci,numeroTelefonico,tipoPlan,fechaRegistro);
				
				clientesRecuperados.add(modelo);
			}
			resultadoConsulta.close();}
			enunciadoSQL.close();
			conexionBD.close();
			LOGGER.info("selection done successfully");	
			return clientesRecuperados;
		} catch ( Exception e ) {
			LOGGER.severe( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
			return clientesRecuperados;
		}
		
	}

	@Override
	public ArrayList<ClienteModelo> mostrarTablaClientesConNumerosAmigos() {
		ArrayList<ClienteModelo> numerosAmigos = new ArrayList<ClienteModelo>();
		try {
			ArrayList<Integer> numeros = new ArrayList<Integer>();
			Class.forName(ORG_SQLITE_JDBC);
			conexionBD = DriverManager.getConnection(connectionDB.getConnection());
			conexionBD.setAutoCommit(false);
			LOGGER.info("Opened NumerosAmigos successfully");

			enunciadoSQL = conexionBD.createStatement();
			try(ResultSet resultadoConsulta = enunciadoSQL.executeQuery( "SELECT * FROM NumerosAmigos;" );){

			while ( resultadoConsulta.next() ) {

				int numeroTelefonico = resultadoConsulta.getInt("numeroTelefonico");
				String fechaRegistro = resultadoConsulta.getString("fechaRegistro");
				numeros.add(resultadoConsulta.getInt("numeroAmigo1"));
				numeros.add(resultadoConsulta.getInt("numeroAmigo2"));
				numeros.add(resultadoConsulta.getInt("numeroAmigo3"));
				numeros.add(resultadoConsulta.getInt("numeroAmigo4"));
				
				ClienteModelo modelo = new ClienteModelo(numeroTelefonico, fechaRegistro,numeros);
				
				numerosAmigos.add(modelo);
			}
			resultadoConsulta.close();}
			enunciadoSQL.close();
			conexionBD.close();
			LOGGER.info("selection done successfully");	
			return numerosAmigos;
		} catch ( Exception e ) {
			LOGGER.severe(e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
			return numerosAmigos;
		}
		
	}
}
