package uy.edu.ort.aed2.obligatorio;

import uy.edu.ort.aed2.obligatorio.Arbol.Nodo;
import uy.edu.ort.aed2.obligatorio.Retorno.Resultado;

public class SistemaImp implements Sistema {

	int maxAeropuertos;
	Grafo grafo;
	Arbol<Pasajero> pasajeros;

	@Override
	public Retorno inicializarSistema(int maxAeropuertos) {
		if (maxAeropuertos <= 2) {
			return new Retorno(Retorno.Resultado.ERROR_1);
		} else {
			this.pasajeros = new Arbol<Pasajero>();
			this.grafo = new Grafo(maxAeropuertos);
			return new Retorno(Retorno.Resultado.OK);
		}
	}

	@Override
	public Retorno registrarPasajero(String cedula, String nombre, String telefono, Categoria categoria) {
		if (cedula == null || nombre == null || telefono == null || categoria == null || cedula == "" || nombre == ""
				|| telefono == "") {
			return new Retorno(Retorno.Resultado.ERROR_1);
		} else {
			if (!Pasajero.verificarCedula(cedula)) {
				return new Retorno(Retorno.Resultado.ERROR_2);
			} else if (buscarPasajero(cedula).resultado == Resultado.OK) {
				return new Retorno(Retorno.Resultado.ERROR_3);
			} else {
				pasajeros.add(new Pasajero(cedula, nombre, telefono, categoria));
				return new Retorno(Retorno.Resultado.OK);
			}
		}
	}

	@Override
	public Retorno buscarPasajero(String cedula) {
		if (!Pasajero.verificarCedula(cedula)) {
			return new Retorno(Retorno.Resultado.ERROR_1);
		}
		int contador = 0;
		var current = pasajeros.raiz;
		while (current != null) {
			contador++;
			if (current.dato.hashCode() > Integer.parseInt(cedula.replaceAll("[./-]", ""))) {
				current = current.left;
			} else if (current.dato.hashCode() < Integer.parseInt(cedula.replaceAll("[./-]", ""))) {
				current = current.right;
			} else {
				return new Retorno(Retorno.Resultado.OK, contador, current.dato.toString());
			}
		}

		return new Retorno(Retorno.Resultado.ERROR_2);

	}

	@Override
	public Retorno listarPasajerosAscendente() {
		return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno listarPasajerosDescendente() {
		return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno listarPasajerosPorCategoría(Categoria categoria) {
		return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno registrarAeropuerto(String codigo, String nombre) {
		try {
			if (codigo == null || nombre == null || codigo.isEmpty() || nombre.isEmpty()) {
				return new Retorno(Retorno.Resultado.ERROR_2);
			} else {
				if (grafo.buscarCodigo(codigo) != null) {
					return new Retorno(Retorno.Resultado.ERROR_3);
				} else {
					grafo.agregarVertice(new Aeropuerto(nombre, codigo));
					return new Retorno(Retorno.Resultado.OK);
				}
			}
		} catch (FullException e) {
			return new Retorno(Retorno.Resultado.ERROR_1);
		}
	}

	@Override
	public Retorno registrarConexion(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, double kilometros) {
		if (kilometros <= 0) {
			return new Retorno(Retorno.Resultado.ERROR_1);
		} else if (grafo.buscarCodigo(codigoAeropuertoOrigen) == null) {
			return new Retorno(Retorno.Resultado.ERROR_2);
		} else if (grafo.buscarCodigo(codigoAeropuertoDestino) == null) {
			return new Retorno(Retorno.Resultado.ERROR_3);
		} else if (grafo.buscarConexion(codigoAeropuertoOrigen, codigoAeropuertoDestino) != null) {
			return new Retorno(Retorno.Resultado.ERROR_4);
		} else {
			Conexion c = new Conexion(kilometros);
			Aeropuerto origen = grafo.buscarCodigo(codigoAeropuertoOrigen);
			Aeropuerto destino = grafo.buscarCodigo(codigoAeropuertoDestino);
			grafo.agregarArista(origen, destino, c);
		}
		return new Retorno(Retorno.Resultado.OK);
	}

	@Override
	public Retorno registrarVuelo(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, String codigoDeVuelo,
			double combustible, double minutos, double costoEnDolares) {
		Conexion c = grafo.buscarConexion(codigoAeropuertoOrigen, codigoAeropuertoDestino);
		if (combustible <= 0 || minutos <= 0 || costoEnDolares <= 0) {
			return new Retorno(Retorno.Resultado.ERROR_1);
		} else if (codigoAeropuertoDestino == null || codigoAeropuertoDestino.isEmpty() || codigoAeropuertoOrigen == null
				|| codigoAeropuertoOrigen.isEmpty()) {
			return new Retorno(Retorno.Resultado.ERROR_2);
		} else if (grafo.buscarCodigo(codigoAeropuertoOrigen) == null) {
			return new Retorno(Retorno.Resultado.ERROR_3);
		} else if (grafo.buscarCodigo(codigoAeropuertoDestino) == null) {
			return new Retorno(Retorno.Resultado.ERROR_4);
		} else if (c == null) {
			return new Retorno(Retorno.Resultado.ERROR_5);
		} else if (grafo.buscarVuelo(codigoAeropuertoOrigen, codigoAeropuertoDestino, codigoDeVuelo) != null) {
			return new Retorno(Retorno.Resultado.ERROR_6);
		} else {
			Vuelo v = new Vuelo(codigoAeropuertoOrigen, codigoAeropuertoDestino, codigoDeVuelo, combustible, minutos,
					costoEnDolares);
			c.agregarVuelo(v);
			return new Retorno(Retorno.Resultado.OK);
		}
	}

	@Override
	public Retorno actualizarVuelo(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, String codigoDeVuelo,
			double combustible, double minutos, double costoEnDolares) {
		Vuelo v = grafo.buscarVuelo(codigoAeropuertoOrigen, codigoAeropuertoDestino, codigoDeVuelo);
		if (combustible <= 0 || minutos <= 0 || costoEnDolares <= 0) {
			return new Retorno(Retorno.Resultado.ERROR_1);
		} else if (codigoAeropuertoDestino == null || codigoAeropuertoDestino.isEmpty()
				|| codigoAeropuertoOrigen == null || codigoAeropuertoOrigen.isEmpty()) {
			return new Retorno(Retorno.Resultado.ERROR_2);
		} else if (grafo.buscarCodigo(codigoAeropuertoOrigen) == null) {
			return new Retorno(Retorno.Resultado.ERROR_3);
		} else if (grafo.buscarCodigo(codigoAeropuertoDestino) == null) {
			return new Retorno(Retorno.Resultado.ERROR_4);
		} else if (grafo.buscarConexion(codigoAeropuertoOrigen, codigoAeropuertoDestino) == null) {
			return new Retorno(Retorno.Resultado.ERROR_5);
		} else if (v == null) {
			return new Retorno(Retorno.Resultado.ERROR_6);
		} else {
			v.setCombustible(combustible);
			v.setMinutos(minutos);
			v.setCostoEnDolares(costoEnDolares);
			return new Retorno(Retorno.Resultado.OK);
		}
	}

	@Override
	public Retorno listadoAeropuertosCantDeEscalas(String codigoAeropuertoDeOrigen, int cantidad) {
		return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno viajeCostoMinimoKilometros(String codigoAeropuertoOrigen, String codigoAeropuertoDestino) {
		return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno viajeCostoMinimoDolares(String codigoAeropuertoOrigen, String codigoAeropuertoDestino) {
		return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno consultaDisponibilidad(int[][] matriz, int cantidad, Clase unaClase) {
		return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
	}

}
