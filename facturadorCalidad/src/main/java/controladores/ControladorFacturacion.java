package controladores;

import static spark.Spark.get;

import java.util.ArrayList;

import com.google.gson.Gson;

import casosDeUso.IPersistencia;
import modelos.CDRModelo;
import modelos.FacturaModelo;

public class ControladorFacturacion {
	private IPersistencia persistencia;
	public static Gson gson = new Gson();
	public ControladorFacturacion(IPersistencia persistencia){
		this.persistencia = persistencia;
		get("/costoLlamadaCliente/:numeroTelefonico/mes/:mes", (request, response) ->
        {
			Integer numeroTelefonico= Integer.parseInt(request.params(":numeroTelefonico"));
			String mes = request.params(":mes");
			return gson.toJson(devolverFacturaDeUnMesDeUnCliente(numeroTelefonico,mes, this.persistencia));
			
        });
	}
	
	public static FacturaModelo devolverFacturaDeUnMesDeUnCliente(Integer numeroTelefonico, String mes, IPersistencia persistencia) {
    	return persistencia.obtenerFacturaDeMesParaCliente(numeroTelefonico, mes);
    }
}
