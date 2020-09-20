package modelos;


public class CDRModelo {
	private int numeroTelefonoOrigen, numeroTelefonoDestino, id;
	private double costoDeLlamada; 
	private String duracionLlamada, fecha, hora, fechaTarificacion, horaTarificacion;
	
	public CDRModelo(int id, int numeroTelefonoOrigen, int numeroTelefonoDestino, String duracionLlamada, String fecha, String hora, String fechaTarificacion, double costoDeLlamada, String horaTarificacion){
		this.id = id;
		this.numeroTelefonoOrigen = numeroTelefonoOrigen;
		this.numeroTelefonoDestino = numeroTelefonoDestino;
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
		return duracionLlamada;
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
