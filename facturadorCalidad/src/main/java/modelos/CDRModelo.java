package modelos;


public class CDRModelo {
	private int numeroTelefonoOrigen, numeroTelefonoDestino, id;
	private double costoDeLlamada; 
	private String duracionLlamada, fecha, hora, fechaTarificacion, horaTarificacion;
	
	public CDRModelo(int id){
		this.id = id;
	}
	
	public void setDatosBasicosCDR(int numeroTelefonoOrigen, int numeroTelefonoDestino) {
		this.numeroTelefonoOrigen = numeroTelefonoOrigen;
		this.numeroTelefonoDestino = numeroTelefonoDestino;
	}
	
	public void setDatosAvanzadosCDR(String duracionLlamada, String fecha, String hora, String fechaTarificacion, double costoDeLlamada, String horaTarificacion) {
		this.duracionLlamada = duracionLlamada;
		this.fecha = fecha;
		this.hora = hora;
		this.costoDeLlamada = costoDeLlamada;
		this.fechaTarificacion = fechaTarificacion;
		this.horaTarificacion = horaTarificacion;
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
	
	
	public String getHora() {
		return hora;
	}
	
	public String getFecha() {
		return fecha;
	}
	public String getDuracionLLamada() {
		return this.duracionLlamada;
	}
	
	public int getId() {
		return id;
	}
	public String getFechaTarificacion() {
		return fechaTarificacion;
	}
	public String getHoraTarificacion() {
		return horaTarificacion;
	}
	
}
