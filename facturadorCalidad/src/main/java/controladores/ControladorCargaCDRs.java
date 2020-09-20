package controladores;

import static spark.Spark.get;
import static spark.Spark.post;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;

import casosDeUso.IRegistroCDR;
import entidades.CDR;
import spark.ModelAndView;
import spark.Request;
import spark.template.velocity.VelocityTemplateEngine;
import spark.utils.IOUtils;

public class ControladorCargaCDRs {
	public static IRegistroCDR registroCDR;
	public ControladorCargaCDRs(IRegistroCDR registroCDR) {
		this.registroCDR = registroCDR;
		get("/registrosCargados", (request, response) ->
        {
        	Map<String, Object> modelo = new HashMap<>();
        	modelo.put("registrosCargados", devolverRegistrosCargados());
        	return new VelocityTemplateEngine().render(new ModelAndView(modelo, "velocity/registros/vistaPreviaDeCDRSCargados.vm"));
        });
        
        post("/api/submit", (request, response) -> 
        {
        	request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));
        	Part archivoCargado = null;	      	
        	try
        	{
        		guardarCDRsCargados(registroCDR, request);	
        	}
        	catch(IOException | ServletException e)
        	{
        		e.printStackTrace();
        	}
        	Map<String, Object> modelo = new HashMap<>();
        	modelo.put("registrosCargados", devolverRegistrosCargados());
        	modelo.put("Title","CDRs Cargados");
            return new VelocityTemplateEngine().render(new ModelAndView(modelo, "velocity/registros/vistaPreviaDeCDRSCargados.vm"));

        });
	}



	private void guardarCDRsCargados(IRegistroCDR registroCDR, Request request) throws IOException, ServletException {
		Part archivoCargado;
		archivoCargado = request.raw().getPart("myFile");
		InputStream flujoDeEntrada = archivoCargado.getInputStream();
		StringWriter escritor = new StringWriter();
		IOUtils.copy(flujoDeEntrada, escritor);
		String cadenaCDRsCargados = escritor.toString();
		registroCDR.guardarTemporalmenteCDRs(cadenaCDRsCargados);
	}
	


	private static ArrayList<CDR> devolverRegistrosCargados() {
    	return registroCDR.obtenerRegistrosNoTarificados();
    }
}
