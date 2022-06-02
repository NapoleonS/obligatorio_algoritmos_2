package uy.edu.ort.aed2.obligatorio;

public class Arbol<T> {
	public Nodo raiz;

	private String pasajerosAscendente;
	private String pasajerosDescendente;

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

	public String listarOrdenado() {
		if (this.raiz == null) {
			pasajerosAscendente = "";
			return "";
		}
		pasajerosAscendente = "";
		listarOrdenado(raiz);
		StringBuffer sb = new StringBuffer(pasajerosAscendente);
		sb.deleteCharAt(pasajerosAscendente.length() - 1);
		return sb.toString();
	}

	private void listarOrdenado(Nodo nodo) {
		if (nodo == null) {
			if (this.pasajerosAscendente == null) {
				this.pasajerosAscendente = "";
				return;
			} else {
				return;
			}
		}
		listarOrdenado(nodo.left);
		this.pasajerosAscendente += ((Pasajero) nodo.dato).toString() + "|";
		listarOrdenado(nodo.right);
	}

	public String listarDescendente() {
		if (this.raiz == null) {
			pasajerosDescendente = "";
			return "";
		}
		pasajerosDescendente = "";
		listarDescendente(raiz);
		StringBuffer sb = new StringBuffer(pasajerosDescendente);
		sb.deleteCharAt(pasajerosDescendente.length() - 1);
		return sb.toString();
	}

	private void listarDescendente(Nodo nodo) {
		if (nodo == null)
			return;
		listarDescendente(nodo.right);
		this.pasajerosDescendente += nodo.dato.toString() + "|"; // nodo.toString()?
		listarDescendente(nodo.left);
	}

	public void inOrder(Nodo actual) {
		if (actual == null) {
			return;
		}
		if (actual.left != null) {
			inOrder(actual.left);
		}
		System.out.println(actual.dato);

		if (actual.right != null) {
			inOrder(actual.right);
		}

	}

	public void postOrder(Nodo actual) {
		if (actual == null) {
			return;
		}
		if (actual.left != null) {
			inOrder(actual.left);
		}
		if (actual.right != null) {
			inOrder(actual.right);
		}
		System.out.println(actual.dato);

	}

	public void add(T dato) {
		raiz = addRecursivo(raiz, dato);
	}

}
