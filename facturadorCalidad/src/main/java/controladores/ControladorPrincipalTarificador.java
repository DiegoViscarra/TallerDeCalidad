package controladores;

import static spark.Spark.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;

import org.javatuples.Pair;

import com.google.gson.Gson;

import casosDeUso.*;
import entidades.CDR;
import io.vavr.Tuple2;
import modelos.CDRModelo;
import modelos.ClienteModelo;
import modelos.FacturaModelo;
import repositorios.*;
import spark.ModelAndView;
import spark.Request;
import spark.template.velocity.VelocityTemplateEngine;
import spark.utils.IOUtils;

public class ControladorPrincipalTarificador {

	private static IRepositorioCDR repositorioCDR = new RepositorioCDR();
	public static IPersistenciaBDClientes persistenciaClientes = new PersistenciaBDClientes();
	public static IPersistencia persistencia = new Persistencia(new PersistenciaBDCDR(), persistenciaClientes,
			new PersistenciaArchivos(), repositorioCDR);
	private static IRepositorioCliente repositorioCliente = new RepositorioCliente(persistencia);
	public static ITarificacion tarificacion = new Tarificacion(repositorioCliente);
	public static IRegistroClientes registroClientes = new RegistroClientes(repositorioCliente, persistenciaClientes);
	public static IRegistroCDR registroCDR = new RegistroCDR(repositorioCDR);

	public static void main(String[] args) {
		port(8080);
		inicializar();
	}

	public static void inicializar() {
		revisarDeBDClientesExistentes();
		new ControladorFacturacion(persistencia);
		new ControladorRegistroClientes(registroClientes);
		new ControladorClientes(persistencia);
		new ControladorCDRs(tarificacion, persistencia, registroCDR);
		new ControladorCargaCDRs(registroCDR);
	}

	public static ArrayList<ClienteModelo> devolverClientesDeBD() {
		return persistencia.mostrarDeBDClientes();
	}

	public static void revisarDeBDClientesExistentes() {
		if (devolverClientesDeBD().size() != 0)
			registroClientes.llenarListaClientes();
	}


}