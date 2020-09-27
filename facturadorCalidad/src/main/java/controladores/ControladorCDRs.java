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
	private ITarificacion tarificacion = null;
	private IPersistencia persistencia = null;
	private IRegistroCDR registroCDR = null;
	public ControladorCDRs(ITarificacion tarificacion, IPersistencia persistencia, IRegistroCDR registroCDR) {
		this.tarificacion = tarificacion;
		this.persistencia = persistencia;
		this.registroCDR = registroCDR;
		get("/registrosTarificados", (request, response) ->
        {
        	Map<String, Object> modelo = new HashMap<>();
        	modelo.put("modoPersistencia", modoPersistencia);
        	modelo.put("registrosTarificados", devolverRegistrosTarificados(this.tarificacion, this.registroCDR));
        	contadorPersistenciaBD = 0;
        	return new VelocityTemplateEngine().render(new ModelAndView(modelo, "velocity/registros/registrosDeCDRSTarificados.vm"));
        });
		get("/registrosRecuperados", (request, response) ->
        {
        	Map<String, Object> model = new HashMap<>();
        	if(modoPersistencia == CLAVEPERSISTENCIAARCHIVOS) {
            	model.put("registrosDeserializados", deserializarTodosLosArchivos(this.persistencia));
            	return new VelocityTemplateEngine().render(new ModelAndView(model, "velocity/registros/registrosDeCDRSDeserializados.vm"));
        	}
        	else {
        		model.put("registrosRecuperados", devolverDeBDCDRModelo(this.persistencia));
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
        	model.put("registrosTarificados", devolverRegistrosTarificados(tarificacion, registroCDR));
        	return new VelocityTemplateEngine().render(new ModelAndView(model, "velocity/registros/registrosDeCDRSTarificados.vm"));
        });
        
        post("/filtrar", (request, response) -> {
        	Map<String, Object> model = new HashMap<>();
        	model.put("registrosRecuperados", devolverCDRModeloFiltrado(request.queryParams("fecha").toString(), persistencia));
        	return new VelocityTemplateEngine().render(new ModelAndView(model, "velocity/registros/registrosDeCDRSRecuperados.vm"));
        });
	}

	private void guardarDeAcuerdoAModoPersistencia() {
		if(modoPersistencia != null && modoPersistencia.equals(CLAVEPERSISTENCIABASEDEDATOS))
		{
			contadorPersistenciaBD++;
			if(contadorPersistenciaBD <=1)        		
				guardarEnBD(persistencia, tarificacion, registroCDR);
		}
		if(modoPersistencia != null && modoPersistencia.equals(CLAVEPERSISTENCIAARCHIVOS))
			guardarEnArchivoDeTexto(persistencia, tarificacion, registroCDR);
	}
	
	public static ArrayList<Pair<String,ArrayList<CDR>>> deserializarTodosLosArchivos(IPersistencia persistencia) {
    	return persistencia.deserializarArchivos();
    }
	
	private static ArrayList<CDRModelo> devolverDeBDCDRModelo(IPersistencia persistencia) {
    	return persistencia.mostrarDeBDCDRs();
    }
	
	public static void guardarEnBD(IPersistencia persistencia, ITarificacion tarificacion, IRegistroCDR registroCDR) {
		persistencia.persistirEnBDCdr(devolverRegistrosTarificados(tarificacion, registroCDR));
    }
    
    public static void guardarEnArchivoDeTexto(IPersistencia persistencia, ITarificacion tarificacion, IRegistroCDR registroCDR ) {
    	persistencia.persistirEnArchivo(devolverRegistrosTarificados(tarificacion, registroCDR));
    }
    
    private static ArrayList<CDR> devolverRegistrosTarificados(ITarificacion tarificacion, IRegistroCDR registroCDR) {
    	return tarificacion.tarificarRegistros(devolverRegistrosNoTarificados(registroCDR));
    }
    
    private static ArrayList<CDR> devolverRegistrosNoTarificados(IRegistroCDR registroCDR) {
    	return registroCDR.obtenerRegistrosNoTarificados();
    }
    
    private static ArrayList<CDRModelo> devolverCDRModeloFiltrado(String fecha, IPersistencia persistencia) {
    	return persistencia.mostrarDeBDCDRsFiltradosPor(fecha);
    }
    
}
