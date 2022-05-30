package uy.edu.ort.aed2.obligatorio;

public class Conexion {
	private int km;
	private boolean habilitada;

	public Conexion(int km, boolean habilitada) {
		this.km = km;
		this.habilitada = habilitada;
	}

	public Conexion(int km) {
		this(km, false);
	}

	public int getKm() {
		return km;
	}

	public boolean habilitada() {
		return habilitada;
	}
}