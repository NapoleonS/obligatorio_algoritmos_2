package uy.edu.ort.aed2.obligatorio;

import uy.edu.ort.aed2.obligatorio.Lista.Nodo;

public class Conexion {
	private double km;
	private boolean habilitada;

	private Lista<Vuelo> vuelos;

	public Conexion(double km, boolean habilitada) {
		this.km = km;
		this.habilitada = habilitada;
		this.vuelos = new Lista<Vuelo>();
	}

	public Conexion(double km) {
		this(km, false);
	}

	public double getKm() {
		return km;
	}

	public boolean habilitada() {
		return habilitada;
	}

	public void setHabilitada(boolean habilitada) {
		this.habilitada = habilitada;
	}

	public void agregarVuelo(Vuelo vuelo) {
		vuelos.add(vuelo);
	}

	public Lista<Vuelo> getVuelos() {
		return vuelos;
	}

	public Vuelo buscarVuelo(String codigo) {
		if (vuelos.isEmpty()) {
			return null;
		}
		var current = vuelos.cabeza;
		while (current != null) {
			if (current.dato.getCodigo().equals(codigo)) {
				return current.dato;
			}
			current = current.next;
		}
		return null;
	}

}