package uy.edu.ort.aed2.obligatorio;

public class Vuelo {
	private String origen;
	private String destino;
	private String codigo;
	private double combustible;
	private double minutos;
	private double costoEnDolares;

	public Vuelo(String origen, String destino, String codigo, double combustible, double minutos,
			double costoEnDolares) {
		this.origen = origen;
		this.destino = destino;
		this.codigo = codigo;
		this.combustible = combustible;
		this.minutos = minutos;
		this.costoEnDolares = costoEnDolares;
	}

	public String getOrigen() {
		return origen;
	}

	public String getDesino() {
		return destino;
	}

	public String getCodigo() {
		return codigo;
	}

	public double getCombustible() {
		return combustible;
	}

	public double getMinutos() {
		return minutos;
	}

	public double getCostoEnDolares() {
		return costoEnDolares;
	}

	public void setCombustible(double combustible) {
		this.combustible = combustible;
	}

	public void setMinutos(double minutos) {
		this.minutos = minutos;
	}

	public void setCostoEnDolares(double costoEnDolares) {
		this.costoEnDolares = costoEnDolares;
	}

}
