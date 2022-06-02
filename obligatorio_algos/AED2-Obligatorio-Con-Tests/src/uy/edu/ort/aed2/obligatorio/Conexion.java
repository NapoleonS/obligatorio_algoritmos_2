package uy.edu.ort.aed2.obligatorio;

import uy.edu.ort.aed2.obligatorio.Lista.NodoLista;

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

	public double costoMinimoEnDolares() {
		if (vuelos.cabeza == null) {
			return -1;
		}
		double costoMinimo = ((Vuelo) vuelos.cabeza.dato).getCostoEnDolares();
		NodoLista current = vuelos.cabeza;
		while (current != null) {
			if (((Vuelo) current.dato).getCostoEnDolares() < costoMinimo) {
				costoMinimo = ((Vuelo) current.dato).getCostoEnDolares();
			}
			current = current.next;
		}
		return costoMinimo;
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