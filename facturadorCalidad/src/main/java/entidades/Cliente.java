package entidades;

import casosDeUso.IPlan;

public class Cliente {
	private String nombre, ci;
	private int numeroTelefonico;
	private String tipoPlan;
	IPlan plan;
	
	public Cliente(String nombre, String ci, int numeroTelefonico){
		this.nombre = nombre;
		this.ci = ci;
		this.numeroTelefonico = numeroTelefonico;
	}
	
	public void setNumeroTelefonico(int numeroTelefonico) {
		this.numeroTelefonico = numeroTelefonico;
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
	
	public IPlan getPlan() {
		return plan;
	}
	
	public void setPlan(IPlan plan) {
		this.plan = plan;
	}
	
	public void setTipoPlan(String tipoPlan) {
		this.tipoPlan = tipoPlan;
	}
	public String getTipoPlan() {
		return tipoPlan;
	}
}
