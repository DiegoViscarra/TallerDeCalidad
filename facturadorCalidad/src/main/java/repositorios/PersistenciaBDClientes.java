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
	private static final String JDBC_SQLITE_DB_SQL_DB = "jdbc:sqlite:dbSQL.db;user=user&password=password";
	private static final String ORG_SQLITE_JDBC = "org.sqlite.JDBC";
	private final static Logger LOGGER = Logger.getLogger(PersistenciaBDClientes.class.getName());
	Connection conexionBD = null;
	Statement enunciadoSQL = null;
	
	@Override
	public void crearTabla() {
		try {
			Class.forName(ORG_SQLITE_JDBC);
			conexionBD = DriverManager.getConnection(JDBC_SQLITE_DB_SQL_DB);
			
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
			conexionBD = DriverManager.getConnection(JDBC_SQLITE_DB_SQL_DB);
			conexionBD.setAutoCommit(false);
			LOGGER.info("Opened Clientes successfully");

			String sentenciaSQL = "INSERT INTO CLientes (nombre,ci,numeroTelefonico,tipoPlan,fechaRegistro)" + "values(?,?,?,?,?)";


			try(PreparedStatement enunciadoPreparado = conexionBD.prepareStatement(sentenciaSQL);){
			
			enunciadoPreparado.setString(1,cliente.getNombre());
			enunciadoPreparado.setString(2,cliente.getCi());
			enunciadoPreparado.setInt(3,cliente.getNumeroTelefonico());
			enunciadoPreparado.setString(4,cliente.getTipoPlan());
			SimpleDateFormat formatoFechaCompleta = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
			enunciadoPreparado.setString(5,formatoFechaCompleta.format(Calendar.getInstance().getTime()).toString());
			
			enunciadoPreparado.executeUpdate();}
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
			conexionBD = DriverManager.getConnection(JDBC_SQLITE_DB_SQL_DB);
			conexionBD.setAutoCommit(false);
			LOGGER.info("Opened numerosAmigos successfully");
			
			String sentenciaSQL = "INSERT INTO NumerosAmigos (numeroTelefonico,fechaRegistro,numeroAmigo1,numeroAmigo2,numeroAmigo3,numeroAmigo4)" + "values(?,?,?,?,?,?)";


			try(PreparedStatement enunciadoPreparado = conexionBD.prepareStatement(sentenciaSQL);){
			
			enunciadoPreparado.setInt(1,numeroTelefonico);
			SimpleDateFormat formatoFechaCompleta = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
			enunciadoPreparado.setString(2,formatoFechaCompleta.format(Calendar.getInstance().getTime()).toString());
			enunciadoPreparado.setInt(3,numerosAmigos.get(0));
			enunciadoPreparado.setInt(4,numerosAmigos.get(1));
			enunciadoPreparado.setInt(5,numerosAmigos.get(2));
			enunciadoPreparado.setInt(6,numerosAmigos.get(3));
			
			enunciadoPreparado.executeUpdate();}
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
		try {
			ArrayList<ClienteModelo> clientesRecuperados = new ArrayList<ClienteModelo>();
			Class.forName(ORG_SQLITE_JDBC);
			conexionBD = DriverManager.getConnection(JDBC_SQLITE_DB_SQL_DB);
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
			return null;
		}
		
	}

	@Override
	public ArrayList<ClienteModelo> mostrarTablaClientesConNumerosAmigos() {
		try {
			ArrayList<ClienteModelo> numerosAmigos = new ArrayList<ClienteModelo>();
			ArrayList<Integer> numeros = new ArrayList<Integer>();
			Class.forName(ORG_SQLITE_JDBC);
			conexionBD = DriverManager.getConnection(JDBC_SQLITE_DB_SQL_DB);
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
			return null;
		}
		
	}
}
