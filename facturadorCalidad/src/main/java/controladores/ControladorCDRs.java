package controladores;

import static spark.Spark.get;
import static spark.Spark.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.javatuples.Pair;

import casosDeUso.IPersistencia;
import casosDeUso.IRegistroCDR;
import casosDeUso.ITarificacion;
import entidades.CDR;
import modelos.CDRModelo;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class ControladorCDRs {
	private static final String CONFIGURACION = "configuracion";
	private static final String CLAVEPERSISTENCIABASEDEDATOS = "BASEDEDATOS";
	private static final String CLAVEPERSISTENCIAARCHIVOS = "SERIALIZAR";
	private static String modoPersistencia = CLAVEPERSISTENCIAARCHIVOS;
	private int contadorPersistenciaBD = 0;
	public static ITarificacion tarificacion;
	public static IPersistencia persistencia;
	public static IRegistroCDR registroCDR;
	public ControladorCDRs(ITarificacion tarificacion, IPersistencia persistencia, IRegistroCDR registroCDR) {
		this.tarificacion = tarificacion;
		this.persistencia = persistencia;
		this.registroCDR = registroCDR;
		get("/registrosTarificados", (request, response) ->
        {
        	Map<String, Object> modelo = new HashMap<>();
        	modelo.put("modoPersistencia", modoPersistencia);
        	modelo.put("registrosTarificados", devolverRegistrosTarificados());
        	contadorPersistenciaBD = 0;
        	return new VelocityTemplateEngine().render(new ModelAndView(modelo, "velocity/registros/registrosDeCDRSTarificados.vm"));
        });
		get("/registrosRecuperados", (request, response) ->
        {
        	Map<String, Object> model = new HashMap<>();
        	if(modoPersistencia == CLAVEPERSISTENCIAARCHIVOS) {
            	model.put("registrosDeserializados", deserializarTodosLosArchivos());
            	return new VelocityTemplateEngine().render(new ModelAndView(model, "velocity/registros/registrosDeCDRSDeserializados.vm"));
        	}
        	else {
        		model.put("registrosRecuperados", devolverDeBDCDRModelo());
        		return new VelocityTemplateEngine().render(new ModelAndView(model, "velocity/registros/registrosDeCDRSRecuperados.vm"));        		
        	}
        });
		
		get("/configuracion", (request, response) ->
        {
        	Map<String, Object> model = new HashMap<>();
        	model.put(CONFIGURACION, true);
        	if(modoPersistencia == CLAVEPERSISTENCIABASEDEDATOS)
        		return new VelocityTemplateEngine().render(new ModelAndView(model, "velocity/configuracion/db.vm"));
        	else
        		return new VelocityTemplateEngine().render(new ModelAndView(model, "velocity/configuracion/archivo.vm"));
        });
        get("/configuracion/baseDeDatos", (request, response) ->
        {
        	modoPersistencia = CLAVEPERSISTENCIABASEDEDATOS;
        	Map<String, Object> model = new HashMap<>();
        	model.put(CONFIGURACION, true);
        	return new VelocityTemplateEngine().render(new ModelAndView(model, "velocity/configuracion/db.vm"));
        });
        get("/configuracion/archivo", (request, response) ->
        {
        	modoPersistencia = CLAVEPERSISTENCIAARCHIVOS;
        	Map<String, Object> model = new HashMap<>();
        	model.put(CONFIGURACION, true);
        	return new VelocityTemplateEngine().render(new ModelAndView(model, "velocity/configuracion/archivo.vm"));
        });
        
        get("/guardar", (request, response) ->
        {
        	guardarDeAcuerdoAModoPersistencia();
        	Map<String, Object> model = new HashMap<>();
        	model.put("modoPersistencia", modoPersistencia);
        	model.put("registrosTarificados", devolverRegistrosTarificados());
        	return new VelocityTemplateEngine().render(new ModelAndView(model, "velocity/registros/registrosDeCDRSTarificados.vm"));
        });
        
        post("/filtrar", (request, response) -> {
        	Map<String, Object> model = new HashMap<>();
        	model.put("registrosRecuperados", devolverCDRModeloFiltrado(request.queryParams("fecha").toString()));
        	return new VelocityTemplateEngine().render(new ModelAndView(model, "velocity/registros/registrosDeCDRSRecuperados.vm"));
        });
	}

	private void guardarDeAcuerdoAModoPersistencia() {
		if(modoPersistencia != null && modoPersistencia.equals(CLAVEPERSISTENCIABASEDEDATOS))
		{
			contadorPersistenciaBD++;
			if(contadorPersistenciaBD <=1)        		
				guardarEnBD();
		}
		if(modoPersistencia != null && modoPersistencia.equals(CLAVEPERSISTENCIAARCHIVOS))
			guardarEnArchivoDeTexto();
	}
	
	public static ArrayList<Pair<String,ArrayList<CDR>>> deserializarTodosLosArchivos() {
    	return persistencia.deserializarArchivos();
    }
	
	private static ArrayList<CDRModelo> devolverDeBDCDRModelo() {
    	return persistencia.mostrarDeBDCDRs();
    }
	
	public static void guardarEnBD() {
    	persistencia.persistirEnBDCdr(devolverRegistrosTarificados());
    }
    
    public static void guardarEnArchivoDeTexto() {
    	persistencia.persistirEnArchivo(devolverRegistrosTarificados());
    }
    
    private static ArrayList<CDR> devolverRegistrosTarificados() {
    	return tarificacion.tarificarRegistros(devolverRegistrosNoTarificados());
    }
    
    private static ArrayList<CDR> devolverRegistrosNoTarificados() {
    	return registroCDR.obtenerRegistrosNoTarificados();
    }
    
    private static ArrayList<CDRModelo> devolverCDRModeloFiltrado(String fecha) {
    	return persistencia.mostrarDeBDCDRsFiltradosPor(fecha);
    }
    
}
