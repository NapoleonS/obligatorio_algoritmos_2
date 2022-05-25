package uy.edu.ort.aed2.obligatorio;

public class Arbol<T> {
	public Nodo raiz;

	class Nodo {
		T dato;
		Nodo right, left;

		Nodo(T d) {
			dato = d;
			left = right = null;
		}
	}

	public Arbol() {
		raiz = null;
	}

	public Arbol(T raiz) {
		this.raiz = new Nodo(raiz);
	}

	private Nodo addRecursivo(Nodo current, T dato) {
		if (current == null) {
			return new Nodo(dato);
		}

		if (dato.hashCode() < current.dato.hashCode()) {
			current.left = addRecursivo(current.left, dato);
		} else if (dato.hashCode() > current.dato.hashCode()) {
			current.right = addRecursivo(current.right, dato);
		} else {
			return current;
		}
		return current;
	}

	public void add(T dato) {
		raiz = addRecursivo(raiz, dato);
	}

}
