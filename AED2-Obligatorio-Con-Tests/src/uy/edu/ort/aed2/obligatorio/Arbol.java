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

	public void listarOrdenado(){
		listarOrdenado(raiz);
	}

	private void listarOrdenado(Nodo nodo){
		if (nodo==null) return;
		listarOrdenado(nodo.left);
		System.out.println(nodo.dato + ";"); //duda: habra que hacerlo en sistemaImp con retorno string?
		listarOrdenado(nodo.right);

	}

	public void listarDescendente(){
		listarDescendente(raiz);
	}

	private void listarDescendente(Nodo nodo){
		if (nodo==null) return;
		listarDescendente(nodo.right);
		System.out.println(nodo.dato+";"); //nodo.toString()?
		listarDescendente(nodo.left);
	}

	public void inOrder (Nodo actual){
		if (actual == null){
			return;
		}
		if (actual.left != null){
			inOrder(actual.left);
		}
		System.out.println(actual.dato);

		if (actual.right != null){
			inOrder(actual.right);
		}

	}

	public void postOrder (Nodo actual){
		if (actual == null){
			return;
		}
		if (actual.left != null){
			inOrder(actual.left);
		}
		if (actual.right != null){
			inOrder(actual.right);
		}
		System.out.println(actual.dato);

	}

}
