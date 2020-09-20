package entidades;

import java.io.Serializable;

import casosDeUso.IPlan;

public class CDR implements Serializable{
	private static final long serialVersionUID = 6250590643601878842L;
	private int numeroTelefonoOrigen, numeroTelefonoDestino;
	private double costoDeLlamada; 
	private String duracionLlamada, fecha, hora;
	
	public CDR(int numeroTelefonoOrigen, int numeroTelefonoDestino, String duracionLlamada, String fecha, String hora){
		this.numeroTelefonoOrigen = numeroTelefonoOrigen;
		this.numeroTelefonoDestino = numeroTelefonoDestino;
		this.duracionLlamada = duracionLlamada;
		this.fecha = fecha;
		this.hora = hora;
		this.costoDeLlamada = -1;
	}
	
	public void setCosto(double costoDeLlamada) {
		costoDeLlamada = Math.round(costoDeLlamada * 10000);
		costoDeLlamada = costoDeLlamada/10000;
		this.costoDeLlamada = costoDeLlamada;
	}
	
	public double getCostoDeLlamada() {
		return costoDeLlamada;
	}
	
	public int getNumeroTelefonoOrigen() {
		return numeroTelefonoOrigen;
	}
	
	public int getNumeroTelefonoDestino() {
		return numeroTelefonoDestino;
	}
	
	public double convertirMinutosADecimal() {
		double minutos = Double.parseDouble(duracionLlamada.split(":")[0]);
		double segundos = Double.parseDouble(duracionLlamada.split(":")[1])/60;
		return minutos + segundos;
	}
	
	public String getHora() {
		return hora;
	}
	
	public double calcularCostoDeLlamada(IPlan plan) {
		return plan.calcularCostoDeUnaLlamada(this);
	}
	
	public String getFecha() {
		return fecha;
	}
	public String getDuracionLLamada() {
		return duracionLlamada;
	}
}	
