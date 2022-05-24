package uy.edu.ort.aed2.obligatorio;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Pasajero {
	private String cedula;
	private String nombre;
	private String telefono;
	private Sistema.Categoria categoria;

	public Pasajero(String cedula, String nombre, String telefono, Sistema.Categoria categoria) {
		this.cedula = cedula;
		this.nombre = nombre;
		this.telefono = telefono;
		this.categoria = categoria;
	}

	static boolean verificarCedula(String cedula){
		Pattern pattern = Pattern.compile("^([1-9][.][0-9]|[1-9])[0-9]{2}[.][0-9]{3}[-][0-9]$");
		Matcher matcher = pattern.matcher(cedula);
		if(matcher.find()){
			return true;
		}else{
			return false;
		}
	}
}
