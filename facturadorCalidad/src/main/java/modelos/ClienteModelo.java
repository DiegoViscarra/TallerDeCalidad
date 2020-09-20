package modelos;

import java.util.ArrayList;

public class ClienteModelo {
	private String nombre, ci;
	private int numeroTelefonico;
	private String tipoPlan,fechaRegistro;
	private ArrayList<Integer> numerosAmigos;
	
	
	public ClienteModelo(String nombre, String ci, int numeroTelefonico,String tipoPlan,String fechaRegistro){
		this.nombre = nombre;
		this.ci = ci;
		this.numeroTelefonico = numeroTelefonico;
		this.tipoPlan=tipoPlan;
		this.fechaRegistro=fechaRegistro;
	}
	public ClienteModelo(int numeroTelefonico,String fechaRegistro,ArrayList<Integer> numerosAmigos){
		this.numeroTelefonico = numeroTelefonico;
		this.fechaRegistro=fechaRegistro;
		this.numerosAmigos=numerosAmigos;
	}
	
	public int getNumeroTelefonico() {
		return numeroTelefonico;
	}
	public String getCi() {
		return ci;
	}
	public String getNombre() {
		return nombre;
	}
	
	public String getTipoPlan() {
		return tipoPlan;
	}
	public String getFechaRegistro() {
		return fechaRegistro;
	}
	
	public ArrayList<Integer> getNumerosAmigos() {
		return numerosAmigos;
	}
}
