package controladores;

import static spark.Spark.get;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import casosDeUso.IPersistencia;
import modelos.ClienteModelo;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class ControladorClientes {
	private IPersistencia persistencia;
	public ControladorClientes(IPersistencia persistencia) {
		this.persistencia = persistencia;
		get("/clientes", (request, response) -> 
        {
        	Map<String, Object> modelo = new HashMap<>();
        	modelo.put("clientes", devolverClientesDeBD(this.persistencia));
        	modelo.put("amigos", devolverClientesConNumerosAmigosDeBD(this.persistencia));
        	return new VelocityTemplateEngine().render(new ModelAndView(modelo, "velocity/clientes/clientesRegistrados.vm"));
        });
	}
	
	public static ArrayList<ClienteModelo> devolverClientesDeBD(IPersistencia persistencia){
    	return persistencia.mostrarDeBDClientes();
    }
	
	public static ArrayList<ClienteModelo> devolverClientesConNumerosAmigosDeBD(IPersistencia persistencia){
    	return persistencia.mostrarDeBDClientesAmigos();
    }
    
}
