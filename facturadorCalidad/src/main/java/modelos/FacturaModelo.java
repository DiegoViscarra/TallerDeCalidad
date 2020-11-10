package modelos;

public class FacturaModelo {
	private Integer numeroTelefonico;
	private String mes;
	private double montoMes;
	
	public FacturaModelo(Integer numeroTelefonico, String mes, double montoMes){
		this.numeroTelefonico = numeroTelefonico;
		this.mes = mes;
		this.montoMes = montoMes;
	}
	
	public Integer getNumeroTelefonico() {
		return numeroTelefonico;
	}
	
	public String mes() {
		return mes;
	}
	
	public double montoMes() {
		return montoMes;
	}
}
