package controladores;

import static spark.Spark.get;
import static spark.Spark.post;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;

import casosDeUso.IRegistroClientes;
import spark.ModelAndView;
import spark.Request;
import spark.template.velocity.VelocityTemplateEngine;
import spark.utils.IOUtils;

public class ControladorRegistroClientes {
	private static IRegistroClientes registroClientes;
	public ControladorRegistroClientes(IRegistroClientes registroClientes) {
		ControladorRegistroClientes.registroClientes = registroClientes;
		get("/cargarClientes", (request, response) -> 
        {
        	Map<String, Object> modelo = new HashMap<>();
        	modelo.put("clientes", devolverClientes());
        	return new VelocityTemplateEngine().render(new ModelAndView(modelo, "velocity/clientes/vistaPreviaDeClientesCargados.vm"));
        });
		
		post("/api/submitCliente", (request, response) -> 
        {
        	request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));
        	Part uploadedFile = null;      	
        	try
        	{
        		guardarClientesCargados(registroClientes, request);	
        	}
        	catch(IOException | ServletException e)
        	{
        		e.printStackTrace();
        	}
        	Map<String, Object> model = new HashMap<>();
        	model.put("clientesCargados", devolverClientes());
            return new VelocityTemplateEngine().render(new ModelAndView(model, "velocity/clientes/vistaPreviaDeClientesCargados.vm"));
        });
	}

	private void guardarClientesCargados(IRegistroClientes registroClientes, Request request)
			throws IOException, ServletException {
		Part archivoCargado;
		archivoCargado = request.raw().getPart("myFileClient");
		InputStream entradaFlujo = archivoCargado.getInputStream();
		StringWriter escritor = new StringWriter();
		IOUtils.copy(entradaFlujo, escritor);
		String clientesCargadosEnCadena = escritor.toString();
		registroClientes.guardarClientesCargados(clientesCargadosEnCadena);
	}
	
	private static Object devolverClientes() {
		   return registroClientes.devolverClientes();
		}

}
