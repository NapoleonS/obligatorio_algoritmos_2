package uy.edu.ort.aed2.obligatorio;

public class Lista<T> {
	Nodo cabeza;

	public Lista(T cabeza) {
		this.cabeza = new Nodo(cabeza);
	}

	public Lista() {
	}

	class Nodo {
		T dato;
		Nodo next;

		Nodo(T d) {
			dato = d;
		}
	}

	public void addInicio(T dato) {
		Nodo nuevo = new Nodo(dato);
		nuevo.next = cabeza;
		cabeza = nuevo;
	}

	public Object removeInicio() {
		if (cabeza == null) {
			return null;
		}
		Nodo aux = cabeza;
		cabeza = cabeza.next;
		return aux.dato;
	}

	public Object removeFinal() {
		if (cabeza == null) {
			return null;
		}
		Nodo aux = cabeza;
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
		Nodo new_node = new Nodo(dato);
		new_node.next = null;
		if (this.cabeza == null) {
			this.cabeza = new_node;
			return;
		}

		Nodo last = this.cabeza;
		while (last.next != null) {
			last = last.next;
		}

		last.next = new_node;

	}

	public T search(Object dato) {
		Nodo current = this.cabeza;
		while (current != null) {
			if (current.dato.equals(dato)) {
				return current.dato;
			}
			current = current.next;
		}
		return null;
	}

	public boolean remove(T dato) {
		Nodo last = this.cabeza;
		Nodo prev = null;
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
}