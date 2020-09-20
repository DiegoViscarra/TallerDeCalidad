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
	private static IPersistencia persistencia;
	public ControladorClientes(IPersistencia persistencia) {
		this.persistencia = persistencia;
		get("/clientes", (request, response) -> 
        {
        	Map<String, Object> modelo = new HashMap<>();
        	modelo.put("clientes", devolverClientesDeBD());
        	modelo.put("amigos", devolverClientesConNumerosAmigosDeBD());
        	return new VelocityTemplateEngine().render(new ModelAndView(modelo, "velocity/clientes/clientesRegistrados.vm"));
        });
	}
	
	public static ArrayList<ClienteModelo> devolverClientesDeBD(){
    	return persistencia.mostrarDeBDClientes();
    }
	
	public static ArrayList<ClienteModelo> devolverClientesConNumerosAmigosDeBD(){
    	return persistencia.mostrarDeBDClientesAmigos();
    }
    
}
