package repositorios;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import casosDeUso.IPersistenciaBDCDR;
import entidades.CDR;
import modelos.CDRModelo;


public class PersistenciaBDCDR implements IPersistenciaBDCDR {
	private static final String SQL_CLASS = "org.sqlite.JDBC";
	private static final String CONNETTION_DB = "jdbc:sqlite:dbSQL.db;user=user&password=password";
	Connection conexionBD = null;
	Statement enunciadoSQL = null;

	@Override
	public void crearTabla() {
		try {
			Class.forName(SQL_CLASS);
			conexionBD = DriverManager.getConnection(CONNETTION_DB);

			enunciadoSQL = conexionBD.createStatement();
			String sentenciaSQL = "CREATE TABLE IF NOT EXISTS CDR " +
					"( id\tINTEGER\tPRIMARY KEY\tNOT NULL," + 
					"numeroTelefonoOrigen\tINT\tNOT NULL," +
					" numeroTelefonoDestino\tINT\tNOT NULL, " + 
					" duracionLlamada\tCHAR(20)\tNOT NULL, " + 
					" fecha\tCHAR(20)\tNOT NULL,"+
					" hora\tCHAR(20)\tNOT NULL," +
					" costo\tDOUBLE\tNOT NULL," +
					"fechaTarificacion\tCHAR(20)\tNOT NULL,"+
					"horaTarificacion\tCHAR(20)\tNOT NULL)";
			enunciadoSQL.executeUpdate(sentenciaSQL);
			enunciadoSQL.close();
			conexionBD.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
	}

	@Override
	public void poblarTabla(CDR registro) {
		try {
			Class.forName(SQL_CLASS);
			conexionBD = DriverManager.getConnection(CONNETTION_DB);
			conexionBD.setAutoCommit(false);
			System.out.println("Opened CDR successfully");

			String sentenciaSQL = "INSERT INTO CDR (numeroTelefonoOrigen,numeroTelefonoDestino,duracionLlamada,fecha,hora,costo,fechaTarificacion,horaTarificacion)" + "values(?,?,?,?,?,?,?,?)";


			try(PreparedStatement enunciadoPreparado = conexionBD.prepareStatement(sentenciaSQL)){
				enunciadoPreparado.setInt(1,registro.getNumeroTelefonoOrigen());
				enunciadoPreparado.setInt(2,registro.getNumeroTelefonoDestino());
				enunciadoPreparado.setString(3,registro.getDuracionLLamada());
				enunciadoPreparado.setString(4,registro.getFecha());
				enunciadoPreparado.setString(5,registro.getHora());
				enunciadoPreparado.setDouble(6,registro.getCostoDeLlamada());
				SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
				enunciadoPreparado.setString(7,formatoFecha.format(Calendar.getInstance().getTime()).toString());
				SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
				enunciadoPreparado.setString(8,formatoHora.format(Calendar.getInstance().getTime()).toString());
				enunciadoPreparado.executeUpdate();
				conexionBD.commit();
				conexionBD.close();
				System.out.println("CDR closed successfully");
				enunciadoPreparado.close();
			}
		} catch ( Exception e ) {
			System.out.println("entra al errror");
			System.err.println( e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		System.out.println("CDR created successfully");
	}
	
	@Override
	public ArrayList<CDRModelo> mostrarTabla(String sentenciaSQL) {
		try {
			ArrayList<CDRModelo> registrosRecuperados = new ArrayList<CDRModelo>();
			Class.forName(SQL_CLASS);
			conexionBD = DriverManager.getConnection(CONNETTION_DB);
			conexionBD.setAutoCommit(false);
			System.out.println("Opened CDR successfully");

			enunciadoSQL = conexionBD.createStatement();
			try(ResultSet resultadoConsulta = enunciadoSQL.executeQuery(sentenciaSQL)){
				while ( resultadoConsulta.next() ) {
					int numeroTelefonoOrigen = resultadoConsulta.getInt("numeroTelefonoOrigen");
					int numeroTelefonoDestino = resultadoConsulta.getInt("numeroTelefonoDestino");
					String  duracionLlamada = resultadoConsulta.getString("duracionLlamada");
					String  fecha = resultadoConsulta.getString("fecha");
					String  hora = resultadoConsulta.getString("hora");
					double costo  = resultadoConsulta.getDouble("costo");
					String fechaTarificacion = resultadoConsulta.getString("fechaTarificacion");
					String horaTarificacion = resultadoConsulta.getString("horaTarificacion");
					int id = resultadoConsulta.getInt("id");
					
					CDRModelo modelo = new CDRModelo(id, numeroTelefonoOrigen,numeroTelefonoDestino,duracionLlamada,fecha,hora,fechaTarificacion,costo,horaTarificacion);
					
					registrosRecuperados.add(modelo);
				}
				resultadoConsulta.close();
				enunciadoSQL.close();
				conexionBD.close();
				System.out.println("selection done successfully");	
				return registrosRecuperados;
			}
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
			return null;
		}
		
	}
	
}
