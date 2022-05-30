package uy.edu.ort.aed2.obligatorio;

public class Lista<T> {
	NodoLista cabeza;

	public Lista(T cabeza) {
		this.cabeza = new NodoLista(cabeza);
	}

	public Lista() {
	}

	class NodoLista {
		T dato;
		NodoLista next;

		NodoLista(T d) {
			dato = d;
		}
	}

	public void addInicio(T dato) {
		NodoLista nuevo = new NodoLista(dato);
		nuevo.next = cabeza;
		cabeza = nuevo;
	}

	public Object removeInicio() {
		if (cabeza == null) {
			return null;
		}
		NodoLista aux = cabeza;
		cabeza = cabeza.next;
		return aux.dato;
	}

	public Object removeFinal() {
		if (cabeza == null) {
			return null;
		}
		NodoLista aux = cabeza;
		while (aux.next.next != null) {
			aux = aux.next;
		}
		aux.next = null;
		return aux.dato;
	}

	public boolean isEmpty() {
		return cabeza == null;
	}

	public void add(T dato) {
		NodoLista new_node = new NodoLista(dato);
		new_node.next = null;
		if (this.cabeza == null) {
			this.cabeza = new_node;
			return;
		}

		NodoLista last = this.cabeza;
		while (last.next != null) {
			last = last.next;
		}

		last.next = new_node;

	}

	public T search(Object dato) {
		NodoLista current = this.cabeza;
		while (current != null) {
			if (current.dato.equals(dato)) {
				return current.dato;
			}
			current = current.next;
		}
		return null;
	}

	public boolean remove(T dato) {
		NodoLista last = this.cabeza;
		NodoLista prev = null;
		while (last.next != null) {
			if (last.dato.equals(dato)) {
				prev.next = last.next;
				return true;
			}
			prev = last;
			last = last.next;
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		NodoLista current = this.cabeza;
		while (current != null) {
			sb.append(current.dato.toString());
			current = current.next;
		}
		return sb.toString();
	}
}