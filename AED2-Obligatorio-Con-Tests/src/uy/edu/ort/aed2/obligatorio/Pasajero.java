package uy.edu.ort.aed2.obligatorio;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Pasajero {
	public String cedula;
	public String nombre;
	public String telefono;
	public Sistema.Categoria categoria;

	public Pasajero(String cedula, String nombre, String telefono, Sistema.Categoria categoria) {
		this.cedula = cedula;
		this.nombre = nombre;
		this.telefono = telefono;
		this.categoria = categoria;
	}

	static boolean verificarCedula(String cedula) {
		Pattern pattern = Pattern.compile("^([1-9][.][0-9]|[1-9])[0-9]{2}[.][0-9]{3}[-][0-9]$");
		Matcher matcher = pattern.matcher(cedula);
		if (matcher.find()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean equals(Pasajero p) {
		return this.cedula.equals(p.cedula);
	}

	public String toString() {
		return this.cedula + ";" + this.nombre + ";" + this.telefono + ";" + this.categoria.getTexto();
	}

	public int hashCode() {
		return Integer.parseInt(this.cedula.replaceAll("[./-]", ""));
	}
}
