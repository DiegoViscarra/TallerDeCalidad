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
	
	Integer getNumeroTelefonico() {
		return numeroTelefonico;
	}
	
	String mes() {
		return mes;
	}
	
	double montoMes() {
		return montoMes;
	}
}
