package uy.edu.ort.aed2.obligatorio;

import java.util.Objects;

public class Aeropuerto {
	private String nombre;
	private String codigo;

	public Aeropuerto(String nombre, String codigo) {
		this.nombre = nombre;
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCodigo() {
		return this.codigo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Aeropuerto esquina = (Aeropuerto) o;
		return Objects.equals(nombre, esquina.nombre);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public String toString() {
		return codigo + ";" + nombre;
	}
}
