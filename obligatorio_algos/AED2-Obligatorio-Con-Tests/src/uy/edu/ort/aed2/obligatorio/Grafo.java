package uy.edu.ort.aed2.obligatorio;

public class Grafo {

	private class Arista {
		private boolean existe;
		private final int idxOrigen;
		private final int idxDestino;
		private Conexion conexion;

		public Arista(int idxOrigen, int idxDestino) {
			this.idxOrigen = idxOrigen;
			this.idxDestino = idxDestino;
		}

	}

	private final Aeropuerto[] vertices;
	private final Arista[][] aristas;
	private final int maxVertices;
	private int largo;

	public int getMaxVertices() {
		return maxVertices;
	}

	public Aeropuerto getVertice(int idx) {
		return vertices[idx];
	}

	public int getLargo() {
		return largo;
	}

	public Grafo(int maxVertices) {
		this.maxVertices = maxVertices;
		this.vertices = new Aeropuerto[maxVertices];
		this.aristas = new Arista[maxVertices][maxVertices];
		for (int i = 0; i < maxVertices; i++) {
			for (int j = 0; j < maxVertices; j++) {
				this.aristas[i][j] = new Arista(i, j);
			}

		}
	}

	public void agregarVertice(Aeropuerto a) throws FullException {
		if (maxVertices == largo) {
			throw new FullException();
		}
		this.vertices[largo] = a;
		this.largo++;
	}

	private int buscarIndice(Aeropuerto a) {
		for (int i = 0; i < largo; i++) {
			if (vertices[i].equals(a)) {
				return i;
			}
		}
		return -1;
	}

	public Aeropuerto buscarCodigo(String codigo) {
		Aeropuerto result = null;
		for (int i = 0; i < largo; i++) {
			String codigoAComparar = vertices[i].getCodigo();
			if (codigoAComparar.equals(codigo)) {
				result = vertices[i];
			}
		}
		return result;
	}

	public int buscarIndiceCodigo(String codigo) {
		int result = -1;
		for (int i = 0; i < largo; i++) {
			String codigoAComparar = vertices[i].getCodigo();
			if (codigoAComparar.equals(codigo)) {
				result = i;
			}
		}
		return result;
	}

	public Conexion buscarConexion(String origen, String destino) {
		int idxOrigen = buscarIndiceCodigo(origen);
		int idxDestino = buscarIndiceCodigo(destino);
		if (idxOrigen == -1 || idxDestino == -1) {
			return null;
		}
		return aristas[idxOrigen][idxDestino].conexion;
	}

	public Vuelo buscarVuelo(String origen, String destino, String codigo) {
		Conexion c = buscarConexion(origen, destino);
		if (c == null) {
			return null;
		} else {
			return c.buscarVuelo(codigo);
		}
	}

	public void agregarArista(Aeropuerto origen,
			Aeropuerto destino,
			Conexion c) {
		int idxOrigen = this.buscarIndice(origen);
		int idxDestino = this.buscarIndice(destino);

		this.aristas[idxOrigen][idxDestino].existe = true;
		this.aristas[idxOrigen][idxDestino].conexion = c;
	}

	public Lista<Aeropuerto> verticesAdyacentes(Aeropuerto origen) {
		Lista<Aeropuerto> adyacentes = new Lista<>();
		int idxOrigen = buscarIndice(origen);// fila
		for (int destino = 0; destino < maxVertices; destino++) {
			if (aristas[idxOrigen][destino].existe) {
				Arista a = aristas[idxOrigen][destino];

				adyacentes.addInicio(vertices[destino]);
			}
		}
		return adyacentes;
	}

	public Lista<Aeropuerto> verticesIncidentes(Aeropuerto destino) {
		Lista<Aeropuerto> incidentes = new Lista<>();
		int idxDestino = buscarIndice(destino);// columna
		for (int origen = 0; origen < maxVertices; origen++) {
			if (aristas[origen][idxDestino].existe) {
				incidentes.addInicio(vertices[origen]);
			}
		}
		return incidentes;
	}

	public void dfs(Aeropuerto origen, Visitor<Aeropuerto> visitador) {
		Lista<Integer> frontera = new Lista<Integer>(); // Stack
		int idxOrigen = buscarIndice(origen);
		boolean[] visitados = new boolean[maxVertices];
		frontera.add(idxOrigen);
		while (!frontera.isEmpty()) {
			int verticeAExplorar = (Integer) frontera.removeFinal();

			if (!visitados[verticeAExplorar]) {
				visitador.visitar(vertices[verticeAExplorar]);

				visitados[verticeAExplorar] = true;
				for (int destino = 0; destino < maxVertices; destino++) {
					if (aristas[verticeAExplorar][destino].existe) {
						frontera.add(destino);
					}
				}
			}

		}

	}

	public void bfs(Aeropuerto origen, Visitor<Aeropuerto> visitador) {
		Lista<Integer> frontera = new Lista<Integer>();// Queue
		int idxOrigen = buscarIndice(origen);
		boolean[] visitados = new boolean[maxVertices];
		frontera.add(idxOrigen);// push
		while (!frontera.isEmpty()) {
			Integer verticeAExplorar = (Integer) frontera.removeFinal();// pop

			if (!visitados[verticeAExplorar]) {
				visitador.visitar(vertices[verticeAExplorar]);

				visitados[verticeAExplorar] = true;
				// foreach de los adyacentes !
				for (int destino = 0; destino < maxVertices; destino++) {
					if (aristas[verticeAExplorar][destino].existe) {
						frontera.add(destino);
					}
				}
			}

		}

	}

	private boolean existe(int idxOrigen, int idxDestino) {
		return aristas[idxOrigen][idxDestino].existe;
	}

	public void dijkstra(Aeropuerto e, Aeropuerto d, Visitor<Aeropuerto> visitor) {

	}
}
