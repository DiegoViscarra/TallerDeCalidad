package repositorios;

import java.io.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.javatuples.Pair;

import casosDeUso.IPersistenciaArchivos;
import entidades.CDR;
import io.vavr.Tuple2;

public class PersistenciaArchivos implements IPersistenciaArchivos {
	private static String rutaCarpeta = Paths.get(".").toAbsolutePath().normalize().toString()+"\\Serializaciones\\";
	@Override
	public void serializar(ArrayList<CDR> registrosTelefonicos) {
		try {
			String fechaDocumento = obtenerFechaNombreDocumento();
            File documento = new File(fechaDocumento + ".txt"); 
            String rutaActual = rutaCarpeta + documento.toString();
         
            FileOutputStream archivoDeSalida = new FileOutputStream((rutaActual));
			ObjectOutputStream objetoDeSalida = new ObjectOutputStream(archivoDeSalida);
			objetoDeSalida.writeObject(registrosTelefonicos);
			objetoDeSalida.close();
			archivoDeSalida.close();
		} catch (IOException ex) {
			System.out.println("Exception caught");
		}	
	}
	private String obtenerFechaNombreDocumento() {
		Date fecha = new Date();
		DateFormat formatoFechaHora = new SimpleDateFormat("HH-mm-ss dd-MM-yyyy");
		String fechaDocumento = formatoFechaHora.format(fecha).toString();
		fechaDocumento = fechaDocumento.replace(" ", "-");
		return fechaDocumento;
	}
	@Override
	public ArrayList<CDR> deserializar(String nombreArchivo) {
		try {
			File documento = new File(nombreArchivo); 
			String rutaActual = rutaCarpeta + documento.toString();
			FileInputStream archivoEntrada = new FileInputStream(rutaActual);
			ObjectInputStream objetoEntrada = new ObjectInputStream(archivoEntrada);
			
			ArrayList<CDR> lecturaRegistros = new ArrayList<CDR>();
			lecturaRegistros = (ArrayList<CDR>)objetoEntrada.readObject();
			objetoEntrada.close();
			return lecturaRegistros;
		} catch (IOException ex) {
			System.out.println("Exception caught");
		}
        catch(ClassNotFoundException ex) {
            ex.printStackTrace();
        }
		return null;
	}
	
	private ArrayList<String> listarDirectorio() {
		File directorio = new File(rutaCarpeta);
		String[] ficheros = directorio.list();
		ArrayList<String> listaFicheros = new ArrayList<String>();
		if (ficheros != null) {
		  for (int x=0;x<ficheros.length;x++) {
			  System.out.println(ficheros[x]);
			  listaFicheros.add(ficheros[x]);
		  }
		}
		return listaFicheros;
	}
	@Override
	public ArrayList<Pair<String,ArrayList<CDR>>> deserializarTodosLosArchivos() {
		ArrayList<Pair<String,ArrayList<CDR>>> archivosDeserializados = new ArrayList<Pair<String,ArrayList<CDR>>>();
		ArrayList<String>archivos = new ArrayList<String>();
		archivos = listarDirectorio();
		for(String nombre : archivos) {
			Pair<String, ArrayList<CDR>> par = new Pair<String, ArrayList<CDR>>(nombre, deserializar(nombre));
			archivosDeserializados.add(par);
		}
		return archivosDeserializados;
	}
}
